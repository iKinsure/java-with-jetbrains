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
        int h = (height + 2) / 2;
        int w = (width + 2) / 2;
        int size = h * w;
        Graph graph = new Graph(size, 2 * size - h - w);
        Random random = new Random();

        int edgeIterator = 0;
        for (int i = 0; i < graph.vertices; i++) { // create horizontal edges
            if ((i + 1) % w != 0) {
                graph.edge[edgeIterator].source = i;
                graph.edge[edgeIterator].destination = i + 1;
                graph.edge[edgeIterator].weight = random.nextInt(100);
                edgeIterator++;
            }
        }
        for (int i = 0; i < graph.vertices - w; i++) { // create vertical edges
            graph.edge[edgeIterator].source = i;
            graph.edge[edgeIterator].destination = i + w;
            graph.edge[edgeIterator].weight = random.nextInt(100);
            edgeIterator++;
        }
        Edge[] result = graph.kruskal(true); // calculate minimum spanning tree
        for (Edge e : result) { // set passage where are edges
            int jPosSrc = e.source % w;
            int iPosSrc = e.source / w;
            int jPosDest = e.destination % w;
            int iPosDest = e.destination / w;
            array[iPosSrc + iPosDest][jPosSrc + jPosDest] = Block.PASSAGE.id;
        }
        for (int i = 0; i < h; i++) { // set passage on vertices
            for (int j = 0; j < w; j++) {
                array[i * 2][j * 2] = Block.PASSAGE.id;
            }
        }
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
