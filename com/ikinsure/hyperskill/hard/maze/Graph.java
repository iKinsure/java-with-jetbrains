package com.ikinsure.hyperskill.hard.maze;

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

    public Edge[] kruskal(boolean minimize)
    {
        Edge[] result = new Edge[vertices];
        for (int i = 0; i < vertices; ++i) {
            result[i] = new Edge();
        }

        Arrays.sort(edge, minimize
                ? Comparator.comparingInt(e -> e.weight)
                : (e1, e2) -> e2.weight - e1.weight);

        Subset[] subsets = new Subset[vertices];
        for (int i = 0; i < vertices; ++i) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        int k = 0;
        int ee = 0;
        while (ee < vertices - 1) {
            Edge next_edge = edge[k];
            k++;
            int x = find(subsets, next_edge.source);
            int y = find(subsets, next_edge.destination);
            if (x != y) {
                result[ee] = next_edge;
                ee++;
                union(subsets, x, y);
            }
        }
        return result;
    }

    public static String buildGraph(Edge[] edge) {
        StringBuilder builder = new StringBuilder("Edges constructed in graph: \n");
        int weightCounter = 0;
        for (Edge e : edge) {
            builder.append(e.source).append(" -> ")
                    .append(e.destination).append(" = ")
                    .append(e.weight).append("\n");
            weightCounter += e.weight;
        }
        builder.append("Cost to build: ").append(weightCounter).append("\n");
        return builder.toString();
    }
}
