package org.ikinsure.animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BinaryTree {

    Node root;

    public BinaryTree() {

    }

    @JsonIgnore
    public void replace(Node a, Node b) {
        replaceRecursive(a.data, root, b);
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
    public void treeRecursive(Node node, List<String> list, DataFormatter formatter, boolean left, int nested) {
        if (node == null) {
            return;
        }

        String pre = " ".repeat(nested) + (left ? "└" : "├") + " ";
        if (node.isAnimal()) {
            list.add(pre + node.data);
        } else {
            list.add(pre + formatter.question(node.data));
        }

        treeRecursive(node.right, list, formatter, false, nested + 1);
        treeRecursive(node.left, list, formatter, true, nested + 1);

    }

    @JsonIgnore
    public String tree(DataFormatter formatter) {
        List<String> list = new ArrayList<>();
        treeRecursive(root, list, formatter, true, 0);
        return String.join("\n", list);
    }

    @JsonIgnore
    public int numberOfNodes(DataFormatter formatter) {
        List<String> list = new ArrayList<>();
        treeRecursive(root, list, formatter, true, 0);
        return list.size();
    }

    @JsonIgnore
    public int depth(Node node) {
        if (node == null) {
            return -1;
        } else {
            int lDepth = depth(node.left);
            int rDepth = depth(node.right);

            if (lDepth > rDepth) {
                return lDepth + 1;
            } else {
                return rDepth + 1;
            }
        }
    }

    @JsonIgnore
    public int minDepth(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.isAnimal()) {
            return 1;
        }

        return 1 + Math.min(minDepth(node.right), minDepth(node.left));
    }

    @JsonIgnore
    public Node searchRecursive(Node node, String data) {
        if (node == null) {
            return null;
        }
        if (node.isAnimal() && node.data.equals(data)) {
            return node;
        }
        Node n = searchRecursive(node.left, data);
        if (n == null) {
            return searchRecursive(node.right, data);
        } else {
            return n;
        }
    }

    @JsonIgnore
    public Node findParent(Node node, Node our) {
        if (node == null || node.left == null || node.right == null) {
            return null;
        }

        if (our.data.equals(node.left.data) || our.data.equals(node.right.data)) {
            return node;
        }

        Node n = findParent(node.left, our);
        if (n == null) {
            return findParent(node.right, our);
        } else {
            return n;
        }
    }


    @JsonIgnore
    public String search(DataFormatter formatter, String data) {
        Node node = searchRecursive(root, data);
        if (node != null) {
            List<String> list = new ArrayList<>();

            Node parent;
            while (node != root) {
                parent = findParent(root, node);
                list.add( parent.right == node ? parent.data : formatter.negateFact(parent.data));
                node = parent;
            }

            Collections.reverse(list);
            return list.stream().map(n -> " - " + n + ".").collect(Collectors.joining("\n"));
        }
        return "No facts about the " + data + ".";
    }

    @JsonIgnore
    public int averageDepth(Node node, int accum) {
        if (node == null) {
            return 0;
        }
        return accum + averageDepth(node.left, accum + 1) + averageDepth(node.right, accum + 1);
    }

    @JsonIgnore
    public int numberOfAnimals() {
        List<String> list = new ArrayList<>();
        leavesRecursive(root, list);
        return list.size();
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
    public String list() {
        List<String> list = new ArrayList<>();
        leavesRecursive(root, list);
        return list.stream().map(s -> " - " + s).collect(Collectors.joining("\n"));
    }


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
