package org.ikinsure.advisor;

/**
 * Main app entry
 */
public class Main {
    public static void main(String[] args) {
        Config.matchArguments(args);
        new Client();
    }
}
