package org.ikinsure.animals;

public class Node {

    int key;
    String fact;
    Node left;
    Node right;
    Node parent;

    public Node(int key, String fact, Node parent) {
        this.key = key;
        this.fact = fact;
        this.parent = parent;
    }
}
