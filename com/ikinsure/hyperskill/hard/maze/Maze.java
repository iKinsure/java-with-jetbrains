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
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                array[i][j] = Block.WALL.id;
            }
        }
    }

    public void randomize() {

        // initialize
        int h = ((height + 1) / 2) - 1;
        int w = ((width + 1) / 2) - 1;
        boolean isEven = width % 2 == 0;
        int size = h * w;
        Graph graph = new Graph(size + 2, 2 * size - h - w + 2);
        Random random = new Random();
        int edgeIterator = 0;

        // create horizontal edges
        for (int i = 0; i < graph.vertices - 2; i++) {
            if ((i + 1) % w != 0) {
                graph.edge[edgeIterator].source = i;
                graph.edge[edgeIterator].destination = i + 1;
                graph.edge[edgeIterator].weight = random.nextInt(100);
                edgeIterator++;
            }
        }

        // create vertical edges
        for (int i = 0; i < graph.vertices - w - 2; i++) {
            graph.edge[edgeIterator].source = i;
            graph.edge[edgeIterator].destination = i + w;
            graph.edge[edgeIterator].weight = random.nextInt(100);
            edgeIterator++;
        }

        // starting and ending points (two last)
        graph.edge[graph.edge.length - 2].source = size;
        graph.edge[graph.edge.length - 2].destination = random.nextInt(h) * w;
        graph.edge[graph.edge.length - 2].weight = 1;
        graph.edge[graph.edge.length - 1].source = size + 1;
        graph.edge[graph.edge.length - 1].destination = (random.nextInt(h) * w) + w - 1;
        graph.edge[graph.edge.length - 1].weight = 1;

        // set passage where are edges of minimum spanning tree
        for (Edge e : graph.kruskal(true)) {
            if (e.source == size) {
                array[(e.destination / w) * 2 + 1][0] = Block.PASSAGE.id;
            } else if (e.source == size + 1) {
                int iPos = (e.destination / w) * 2 + 1;
                array[iPos][width - 1] = Block.PASSAGE.id;
                if (isEven) {
                    array[iPos][width - 2] = Block.PASSAGE.id;
                }
            } else {
                int iPos = (e.source / w) + (e.destination / w) + 1;
                int jPos = (e.source % w) + (e.destination % w) + 1;
                if (iPos < height && jPos < width) {
                    array[iPos][jPos] = Block.PASSAGE.id;
                }
            }
        }

        // set passage on vertices
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int iPos = i * 2 + 1;
                int jPos = j * 2 + 1;
                if (iPos < height && jPos < width) {
                    array[iPos][jPos] = Block.PASSAGE.id;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("o ".repeat(width + 1)).append("\n");
        for (int i = 0; i < height; i++) {
            builder.append("o");
            for (int j = 0; j < width; j++) {
                builder.append(Block.findTextureById(array[i][j]));
            }
            builder.append("o\n");
        }
        builder.append("o ".repeat(width + 1)).append("\n");
        return builder.toString();
    }
}
