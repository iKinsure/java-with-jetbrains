package com.ikinsure.hyperskill.hard.advisor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String token = null;
        String input = null;

        while (true) {
            input = scanner.nextLine().toLowerCase();
            if (token == null) {
                if (!input.equals("auth") && !input.equals("exit")) {
                    System.out.println("Please, provide access for application.");
                    continue;
                }
            }
            switch (input) {
                case "auth":
                    token = "https://accounts.spotify.com/authorize?client_id=6e70a3870fbe4d4bae4982467b032a2f&redirect_uri=http://localhost:8080&response_type=code";
                    System.out.println(token);
                    System.out.println("---SUCCESS---");
                    break;
                case "new":
                    System.out.println("---NEW RELEASES---\n" +
                            "Mountains [Sia, Diplo, Labrinth]\n" +
                            "Runaway [Lil Peep]\n" +
                            "The Greatest Show [Panic! At The Disco]\n" +
                            "All Out Life [Slipknot]");
                    break;
                case "featured":
                    System.out.println("---FEATURED---\n" +
                            "Mellow Morning\n" +
                            "Wake Up and Smell the Coffee\n" +
                            "Monday Motivation\n" +
                            "Songs to Sing in the Shower");
                    break;
                case "categories":
                    System.out.println("---CATEGORIES---\n" +
                            "Top Lists\n" +
                            "Pop\n" +
                            "Mood\n" +
                            "Latin");
                    break;
                case "playlists mood":
                    System.out.println("---MOOD PLAYLISTS---\n" +
                            "Walk Like A Badass  \n" +
                            "Rage Beats  \n" +
                            "Arab Mood Booster  \n" +
                            "Sunday Stroll");
                    break;
                default:
                    System.out.println("---GOODBYE!---");
                    return;
            }
        }
    }
}
