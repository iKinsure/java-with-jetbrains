package com.ikinsure.hyperskill.hard.maze;

import java.util.Random;

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
        int h = convertToGraphPos(height);
        int w = convertToGraphPos(width);
        int size = h * w;
        Graph graph = new Graph(size, 2 * size - h - w);
        Random random = new Random();
        for (int i = 0; i < graph.vertices - 1; i++) {
            graph.edge[i].source = i;
            graph.edge[i].destination = i + 1;
            graph.edge[i].weight = random.nextInt(100);
        }
        Edge[] result = graph.kruskal(true);
        for (int i = 0; i < result.length; i++) {
            int src = result[i].source;
            int dest = result[i].destination;
            int[] pos1 = convertTo2D(src);
            int[] pos2 = convertTo2D(dest);
            array[pos1[0]][pos1[1]] = Block.WALL.id;
            array[pos2[0]][pos2[1]] = Block.WALL.id;
        }
    }

    public int convertToMainPos(int x) {
        return x <= 0 ? 0 : x * 2 - 1;
    }

    public int convertToGraphPos(int x) {
        return (x + 1) / 2;
    }

    public int[] convertTo2D(int size) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i * height + j == size) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
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
