package org.ikinsure.animals;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BinaryTree {

    Node root;

    public BinaryTree() {

    }
    public BinaryTree(Node root) {
        this.root = root;
    }

    public void replace(Node a, Node b) {
        replaceRecursive(a.data, root, b);
    }

    private void replaceRecursive(String data, Node root, Node node) {
        if (root == null) {
            return;
        }

        if (root.data.equals(data)) {
            root.data = node.data;
            root.right = node.right;
            root.left = node.left;
            return;
        }

        replaceRecursive(data, root.right, node);
        replaceRecursive(data, root.left, node);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
