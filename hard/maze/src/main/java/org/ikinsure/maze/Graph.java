package org.ikinsure.maze;

import java.util.Arrays;
import java.util.Comparator;

public class Graph {

    int vertices;
    int edges;
    Edge[] edge;

    public Graph(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.edge = new Edge[edges];
        for (int i = 0; i < edges; ++i) {
            this.edge[i] = new Edge();
        }
    }

    public int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }


    public void union(Subset[] subsets, int x, int y) {
        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);
        if (subsets[xRoot].rank < subsets[yRoot].rank) {
            subsets[xRoot].parent = yRoot;
        } else if (subsets[xRoot].rank > subsets[yRoot].rank) {
            subsets[yRoot].parent = xRoot;
        } else {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }
    }

    public Edge[] kruskal(boolean minimize) {

        // creating an array for result
        Edge[] result = new Edge[vertices];
        for (int i = 0; i < vertices; ++i) {
            result[i] = new Edge();
        }

        // sorting by weight
        Arrays.sort(edge, minimize
                ? Comparator.comparingInt(e -> e.weight)
                : (e1, e2) -> e2.weight - e1.weight);

        // creating a subset array
        Subset[] subsets = new Subset[vertices];
        for (int i = 0; i < vertices; ++i) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        // calculating
        int edgeCounter = 0;
        int resultCounter = 0;
        while (resultCounter < vertices - 1) {
            Edge actualEdge = edge[edgeCounter];
            edgeCounter++;
            int x = find(subsets, actualEdge.source);
            int y = find(subsets, actualEdge.destination);
            if (x != y) {
                result[resultCounter] = actualEdge;
                resultCounter++;
                union(subsets, x, y);
            }
        }
        return result;
    }

    public int findPos(int i, int j) {
        for (Edge e : edge) {
            if (e.src != null) {
                if (e.src.j == j && e.src.i == i) {
                    return e.source;
                }
            }
            if (e.dst != null) {
                if (e.dst.j == j && e.dst.i == i) {
                    return e.destination;
                }
            }
        }
        return -1;
    }

    public static String buildGraph(Edge[] edge) {
        StringBuilder builder = new StringBuilder("Edges constructed in graph: \n");
        int weightCounter = 0;
        for (Edge e : edge) {
            weightCounter += e.weight;
            builder.append(e.source).append(" -> ")
                    .append(e.destination).append(" = ")
                    .append(e.weight).append("\n");
        }
        builder.append("Cost to build: ").append(weightCounter).append("\n");
        return builder.toString();
    }
}
