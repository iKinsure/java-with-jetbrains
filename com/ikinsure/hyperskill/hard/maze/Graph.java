package com.ikinsure.hyperskill.hard.maze;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Generic class represents weighted graph
 * @param <T> vertex class
 * @param <U> weight class
 */
public class Graph<T, U> {

    private final Map<T, LinkedHashMap<T, U>> adj;

    public Graph() {
        adj = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adj.putIfAbsent(vertex, new LinkedHashMap<>());
    }

    public void removeVertex(T vertex) {
        adj.values().forEach(e -> e.remove(vertex));
        adj.remove(vertex);
    }

    public void addEdge(T v1, T v2, U weight) {
        adj.get(v1).put(v2, weight);
        adj.get(v2).put(v1, weight);
    }

    public void removeEdge(T v1, T v2) {
        LinkedHashMap<T, U> ev1 = adj.get(v1);
        LinkedHashMap<T, U> ev2 = adj.get(v2);
        if (ev1 != null) {
            ev1.remove(v2);
        }
        if (ev2 != null) {
            ev2.remove(v1);
        }
    }

    public LinkedHashMap<T, U> getAdjVertices(T v) {
        return adj.get(v);
    }
}
