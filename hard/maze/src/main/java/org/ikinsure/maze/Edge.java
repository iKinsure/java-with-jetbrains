package org.ikinsure.maze;

public class Edge {
    static class Pos {
        int i;
        int j;
        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    int source;
    int destination;
    int weight;
    Pos src;
    Pos dst;
}
