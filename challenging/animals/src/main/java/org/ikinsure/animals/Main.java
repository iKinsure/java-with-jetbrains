package org.ikinsure.animals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

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

        // read properties from args
        List<String> arguments = Arrays.asList(args);
        int index = arguments.indexOf("-type");
        if (index != -1) {
            properties.setProperty("type", arguments.get(index + 1).toLowerCase());
        }

        // start all
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        Resource res= new Resource(sc, rand);
        App app = new App(sc, res);
        app.run();
    }

    static void saver(BinaryTree tree) throws IOException {
        File file = new File(properties.getProperty("baseName") + "." + properties.getProperty("type"));
        createMapper().writerWithDefaultPrettyPrinter().writeValue(file, tree);
    }

    static BinaryTree loader() throws IOException {
        File file = new File(properties.getProperty("baseName") + "." + properties.getProperty("type"));
        return createMapper().readValue(file, BinaryTree.class);
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
