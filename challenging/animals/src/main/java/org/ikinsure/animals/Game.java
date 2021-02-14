package org.ikinsure.animals;

public class Game {

    private final BinaryTree tree;
    private final Resource res;

    public Game(BinaryTree tree, Resource res) {
        this.tree = tree;
        this.res = res;
    }

    public void start() {
        showStart();
        Node node = tree.root;
        while (true) {
            boolean animal = node.isAnimal();
            System.out.println(res.format(animal ? "guessAnimal" : "question", node.data));
            boolean yes = res.inputYesOrNo();
            if (yes && animal) {
                res.printlnRandom("game.win");
                res.printlnRandom("game.thanks");
                res.printlnRandom("game.again");
                if (res.inputYesOrNo()) {
                    node = tree.root;
                    showStart();
                } else {
                    break;
                }
            } else if (yes) {
                node = node.right;
            } else if (animal) {
                Node result = createStatement(node);
                tree.replace(node, result);
                res.printlnRandom("game.again");
                if (res.inputYesOrNo()) {
                    node = tree.root;
                    showStart();
                } else {
                    break;
                }
            } else {
                node = node.left;
            }
        }
    }

    private void showStart() {
        res.printRandom("animal.nice");
        res.println("animal.learnedMuch");
        res.println("game.letsPlay");
        res.println("game.think");
        res.println("game.enter");
        res.in();
    }

    private Node createStatement(Node previous) {
        Node result;
        res.println("game.giveUp");

        String a1 = previous.data;
        String a2 = res.createAnimal();
        String statement = res.createStatement(a1, a2);

        res.println("game.isCorrect", a2);
        if (res.inputYesOrNo()) {
            result = new Node(statement);
            result.right = new Node(a2);
            result.left = new Node(a1);
        } else {
            result = new Node(statement);
            result.right = new Node(a1);
            result.left = new Node(a2);
        }
        showLearned(result);
        return result;
    }

    private void showLearned(Node node) {
        String prop = res.format("definite", node.right.data) + res.format("animalFact", node.data);
        String con = res.format("definite", node.left.data) + res.format("animalFact", res.format("negative", node.data));
        res.println("game.learned");
        System.out.println(" - " + prop);
        System.out.println(" - " + con);
        res.println("game.distinguish");
        System.out.println(" - " + res.format("question", node.data));
        res.printRandom("animal.nice");
        res.println("animal.learnedMuch");
    }


}
