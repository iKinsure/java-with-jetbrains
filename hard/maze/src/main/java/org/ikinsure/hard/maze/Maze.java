package org.ikinsure.hard.maze;

import java.io.Serializable;
import java.util.*;

public class Maze implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int[][] array;
    private final int height;
    private final int width;

    public Maze(int height, int width) {
        this.width = width;
        this.height = height;
        this.array = new int[height][width];
        for (int i = 0; i < this.height; i++) {
            Arrays.fill(array[i], Block.WALL.id);
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

        // set passage where edges are part of minimum spanning tree
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

    public Maze solve() {

        // copy maze and calculate size
        Maze result = new Maze(this.height, this.width);
        int size = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result.array[i][j] = this.array[i][j];
                if (this.array[i][j] == Block.PASSAGE.id) {
                    size++;
                }
            }
        }

        // looking for start and finish of graph
        int startPos = -1;
        int endPos = -1;
        for (int i = 0; i < height; i++) {
            if (array[i][0] == Block.PASSAGE.id) {
                startPos = i;
            }
            if (array[i][width - 1] == Block.PASSAGE.id) {
                endPos = i;
            }
        }

        // creating graph
        Graph graph = new Graph(size, 2 * size);
        int edgeCounter = 0;
        int edgeIndex = 0;
        for (int i = 0; i < height - 1; i++) {
            for (int j = 0; j < width - 1; j++) {
                if (array[i][j] == Block.PASSAGE.id) {

                    if (array[i][j + 1] == Block.PASSAGE.id) {

                        int srcPos = graph.findPos(i, j);
                        if (srcPos == -1) {
                            graph.edge[edgeCounter].source = edgeIndex;
                            graph.edge[edgeCounter].src = new Edge.Pos(i, j);
                            edgeIndex++;
                        } else {
                            graph.edge[edgeCounter].source = srcPos;
                            graph.edge[edgeCounter].src = new Edge.Pos(i, j);
                        }

                        int dstPos = graph.findPos(i, j + 1);
                        if (dstPos == -1) {
                            graph.edge[edgeCounter].destination = edgeIndex;
                            graph.edge[edgeCounter].dst = new Edge.Pos(i, j + 1);
                            edgeIndex++;
                        } else {
                            graph.edge[edgeCounter].destination = dstPos;
                            graph.edge[edgeCounter].dst = new Edge.Pos(i, j + 1);
                        }

                        graph.edge[edgeCounter].weight = 1;
                        edgeCounter++;
                    }
                    if (array[i + 1][j] == Block.PASSAGE.id) {

                        int srcPos = graph.findPos(i, j);
                        if (srcPos == -1) {
                            graph.edge[edgeCounter].source = edgeIndex;
                            graph.edge[edgeCounter].src = new Edge.Pos(i, j);
                            edgeIndex++;
                        } else {
                            graph.edge[edgeCounter].source = srcPos;
                            graph.edge[edgeCounter].src = new Edge.Pos(i, j);
                        }

                        int dstPos = graph.findPos(i + 1, j);
                        if (dstPos == -1) {
                            graph.edge[edgeCounter].destination = edgeIndex;
                            graph.edge[edgeCounter].dst = new Edge.Pos(i + 1, j);
                            edgeIndex++;
                        } else {
                            graph.edge[edgeCounter].destination = dstPos;
                            graph.edge[edgeCounter].dst = new Edge.Pos(i + 1, j);
                        }

                        graph.edge[edgeCounter].weight = 1;
                        edgeCounter++;
                    }
                }
            }
        }

        // remove empty edges and add them to list
        ArrayList<Edge> way = new ArrayList<>();
        for (int i = 0; i < graph.edge.length; i++) {
            if (graph.edge[i].destination != 0 || graph.edge[i].source != 0) {
                way.add(graph.edge[i]);
            }
        }

        // mark start and end
        boolean isClear = false;
        int start = graph.findPos(startPos, 0);
        int end = graph.findPos(endPos, width - 1);
        if (start == -1 || end == -1) {
            throw new IllegalStateException("Bad maze format");
        }

        // remove dead ends
        while (!isClear) {
            isClear = true;
            for (int i = 0; i < way.size(); i++) {
                if ((way.get(i).source != start && way.get(i).destination != start)
                        && (way.get(i).source != end && way.get(i).destination != end)) {
                    int srcCounter = 0;
                    int dstCounter = 0;
                    int src = way.get(i).source;
                    int dst = way.get(i).destination;
                    for (Edge e : way) {
                        if (src == e.source || src == e.destination) {
                            srcCounter++;
                        }
                        if (dst == e.source || dst == e.destination) {
                            dstCounter++;
                        }
                    }
                    if (srcCounter <= 1 || dstCounter <= 1) {
                        way.remove(way.get(i));
                        isClear = false;
                        break;
                    }
                }
            }
        }

        // adding path
        for (Edge e : way) {
            Edge.Pos pos1 = e.src;
            Edge.Pos pos2 = e.dst;
            result.array[pos1.i][pos1.j] = Block.PATH.id;
            result.array[pos2.i][pos2.j] = Block.PATH.id;
        }
        return result;
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
