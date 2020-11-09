package com.ikinsure.hyperskill.hard.maze;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please, enter the size of a maze");
        Maze maze = new Maze(SCANNER.nextInt(), SCANNER.nextInt());
        maze.randomize();
        System.out.println(maze);
    }
}
