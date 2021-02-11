package org.ikinsure.animals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static final String FILENAME = "animals";
    static String DATA_TYPE = "json";

    public static void main(String[] args) {

        List<String> arguments = Arrays.asList(args);
        int index = arguments.indexOf("-type");
        if (index != -1) {
            DATA_TYPE = arguments.get(index + 1).toLowerCase();
        }

        Scanner scanner = new Scanner(System.in);
        App application = new App(scanner);
        application.run();

    }

    static String input(Scanner scanner) {
        String word = scanner.nextLine().strip().toLowerCase();
        if (word.endsWith(".") || word.endsWith("!") || word.endsWith("?")) {
            word = word.substring(0, word.length() - 1);
        }
        return word;
    }

    static void saver(BinaryTree tree) throws IOException {
        ObjectMapper mapper;
        if (DATA_TYPE.equals("xml")) {
            mapper = new XmlMapper();
        } else if (DATA_TYPE.equals("yaml")) {
            mapper = new YAMLMapper();
        } else {
            mapper = new JsonMapper();
        }
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(FILENAME + "." + DATA_TYPE), tree);
    }

     static BinaryTree loader() throws IOException {
        ObjectMapper mapper;
        if ("xml".equals(DATA_TYPE)) {
            mapper = new XmlMapper();
        } else if ("yaml".equals(DATA_TYPE)) {
            mapper = new YAMLMapper();
        } else {
            mapper = new JsonMapper();
        }
        return mapper.readValue(new File(FILENAME + "." + DATA_TYPE), BinaryTree.class);
    }

}
