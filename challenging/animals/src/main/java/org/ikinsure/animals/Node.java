package org.ikinsure.animals;

public class Node {

    int key;
    Fact fact;
    Node left;
    Node right;

    static int counter = 0;

    public Node(Fact fact) {
        this.key = counter++;
        this.fact = fact;
    }

}
