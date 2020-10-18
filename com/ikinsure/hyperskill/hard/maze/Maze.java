package com.ikinsure.hyperskill.hard.maze;

public class Maze {

    private final int[][] array;

    public Maze(int size) {
        this.array = new int[size][size];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                builder.append(Block.findTextureById(array[i][j]));
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
