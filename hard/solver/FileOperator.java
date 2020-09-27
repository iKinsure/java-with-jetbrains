package hard.solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class FileOperator {

    /**
     * Reads a data
     * @param filename name of file
     * @return String with all data
     * @throws IOException when interrupted or no file
     */
    public static String readFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    /**
     * Saves the given String to file
     * @param filename where to save
     * @param data String with all data
     * @throws IOException when file not found or interrupted
     */
    public static void saveToFile(String filename, String data) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(data);
        }
    }
}
