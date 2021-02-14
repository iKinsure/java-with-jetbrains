package org.ikinsure.animals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.ikinsure.animals.view.MenuController;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final Properties properties = new Properties();

    public static void main(String[] args) throws IOException {

        // load properties from file
        properties.loadFromXML(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("application.xml"));
        properties.setProperty("lang", Locale.getDefault().getLanguage());

        // read properties from args
        List<String> arguments = Arrays.asList(args);
        int index = arguments.indexOf("-type");
        if (index != -1) {
            properties.setProperty("type", arguments.get(index + 1).toLowerCase());
        }

        // start all
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        MenuController contr = new MenuController();
        Resource res= new Resource(sc, rand);
        App app = new App(sc, res, contr);
        app.run();
    }

    static void saver(BinaryTree tree) throws IOException {
        createMapper().writerWithDefaultPrettyPrinter().writeValue(getFile(), tree);
    }

    static BinaryTree loader() throws IOException {
        return createMapper().readValue(getFile(), BinaryTree.class);
    }

    static File getFile() {
        String name = properties.getProperty("baseName");
        String lang = properties.getProperty("lang").equals("en") ? "" : "_" + properties.getProperty("lang");
        String type = "." + properties.getProperty("type");
        return new File(name + lang + type);
    }

    static ObjectMapper createMapper() {
        if ("xml".equals(properties.get("type"))) {
            return new XmlMapper();
        } else if ("yaml".equals(properties.get("type"))) {
            return new YAMLMapper();
        }
        return new JsonMapper();
    }

}
