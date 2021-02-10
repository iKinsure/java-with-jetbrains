package org.ikinsure.animals;

public class BinaryTree {

    Node root;

    public void replace(Node a, Node b) {
        replaceRecursive(a.key, root, b);
    }

    private void replaceRecursive(int key, Node root, Node node) {
        if (root == null) {
            return;
        }

        if (root.key == key) {
            root.fact = node.fact;
            root.right = node.right;
            root.left = node.left;
            return;
        }

        replaceRecursive(key, root.right, node);
        replaceRecursive(key, root.left, node);
    }


}
