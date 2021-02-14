package org.ikinsure.animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BinaryTree {

    Node root;

    public BinaryTree() {

    }

    @JsonIgnore
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

    @JsonIgnore
    public void replace(Node a, Node b) {
        replaceRecursive(a.data, root, b);
    }

    @JsonIgnore
    private int counterRecursive(Node node) {
        int counter = 1;
        if (node == null) {
            return 0;
        } else {
            counter += counterRecursive(node.right);
            counter += counterRecursive(node.left);
            return counter;
        }
    }

    @JsonIgnore
    public int counter() {
        return counterRecursive(root);
    }

    @JsonIgnore
    private void treeRecursive(Node node, Resource res, boolean left, int nested) {
        if (node == null) {
            return;
        }
        String v = res.message("tree.print.vertical").repeat(nested);
        String l = left ? res.message("tree.print.corner") : res.message("tree.print.branch");
        String data = node.isAnimal() ? node.data : res.format("question", node.data);
        res.printf("tree.print.printf", v, l, data);
        treeRecursive(node.right, res, false, nested + 1);
        treeRecursive(node.left, res, true, nested + 1);
    }

    @JsonIgnore
    public void tree(Resource res) {
        treeRecursive(root, res, true, 0);
    }

    @JsonIgnore
    private int depthRecursive(Node node) {
        if (node == null) {
            return -1;
        }
        int lDepth = depthRecursive(node.left);
        int rDepth = depthRecursive(node.right);
        if (lDepth > rDepth) {
            return lDepth + 1;
        } else {
            return rDepth + 1;
        }
    }

    @JsonIgnore
    public int depth() {
        return depthRecursive(root);
    }

    @JsonIgnore
    private int minDepthRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.isAnimal()) {
            return 1;
        }
        return 1 + Math.min(minDepthRecursive(node.right), minDepthRecursive(node.left));
    }

    @JsonIgnore
    public int minDepth() {
        return minDepthRecursive(root);
    }

    @JsonIgnore
    private Node searchRecursive(Node node, String data) {
        if (node == null) {
            return null;
        }
        if (node.isAnimal() && node.data.equals(data)) {
            return node;
        }
        Node n = searchRecursive(node.left, data);
        if (n == null) {
            n = searchRecursive(node.right, data);
        }
        return n;
    }

    @JsonIgnore
    private Node findParent(Node node, Node our) {
        if (node == null || node.left == null || node.right == null) {
            return null;
        }
        if (our.data.equals(node.left.data) || our.data.equals(node.right.data)) {
            return node;
        }
        Node n = findParent(node.left, our);
        if (n == null) {
            n = findParent(node.right, our);
        }
        return n;
    }

    @JsonIgnore
    public List<String> search(Resource res, String data) {
        Node node = searchRecursive(root, data);
        List<String> list = new ArrayList<>();
        if (node != null) {
            while (node != root) {
                Node parent = findParent(root, node);
                list.add( parent.right == node ? parent.data : res.format("negative", parent.data));
                node = parent;
            }
            Collections.reverse(list);
        }
        return list;
    }

    @JsonIgnore
    public int depthSumRecursive(Node node, int accum) {
        if (node == null) {
            return 0;
        }
        return accum + depthSumRecursive(node.left, accum + 1) + depthSumRecursive(node.right, accum + 1);
    }

    @JsonIgnore
    public void leavesRecursive(Node node, List<String> list) {
        if (node == null) {
            return;
        }
        if (node.isAnimal()) {
            list.add(node.data);
        }
        leavesRecursive(node.left, list);
        leavesRecursive(node.right, list);
    }

    @JsonIgnore
    public List<String> leaves() {
        List<String> list = new ArrayList<>();
        leavesRecursive(root, list);
        return list;
    }


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
