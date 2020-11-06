package com.ikinsure.hyperskill.hard.maze;

public class Maze {

    private final int[][] array;
    private final int height;
    private final int width;

    public Maze(int height, int width) {
        this.width = width;
        this.height = height;
        this.array = new int[height][width];
    }

    public void randomize() {
        
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                builder.append(Block.findTextureById(array[i][j]));
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
