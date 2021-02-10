package org.ikinsure.animals;

public class BinaryTree {

    private Node root;

    public Node search(int key) {
        return searchRecursive(root, key);
    }

    public void remove(int key) {
        removeRecursive(root, key);
    }

    public void insert(int key, String fact) {
        root = insertRecursive(root, null, key, fact);
    }

    public void replace(Node a, Node b) {
        if (a.parent == null) {
            root = b;
        } else if (a == a.parent.left) {
            a.parent.left = b;
        } else {
            a.parent.right = b;
        }
        if (b != null) {
            b.parent = a.parent;
        }
    }

    private Node searchRecursive(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
        return searchRecursive(key < node.key ? node.left : node.right, key);
    }

    private Node insertRecursive(Node node, Node parent, int key, String fact) {
        if (node == null) {
            node = new Node(key, fact, parent);
        }
        else {
            if (key < node.key) {
                node.left = insertRecursive(node.left, node, key, fact);
            } else {
                node.right = insertRecursive(node.right, node, key, fact);
            }
        }
        return node;
    }

    private void removeRecursive(Node node, int key) {
        if (node == null) {
            return;
        }
        if (key < node.key) {
            removeRecursive(node.left, key);
        } else if (key > node.key) {
            removeRecursive(node.right, key);
        } else if (node.left != null && node.right != null) {
            Node m = node.right;
            while (m.left != null) {
                m = m.left;
            }
            node.key = m.key;
            node.fact = m.fact;
            replace(m, m.right);
        } else if (node.left != null) {
            replace(node, node.left);
        } else if (node.right != null) {
            replace(node, node.right);
        } else {
            replace(node, null);
        }
    }

}
