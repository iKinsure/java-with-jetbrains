package org.ikinsure.animals;

import org.ikinsure.animals.view.Menu;
import org.ikinsure.animals.view.MenuController;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {

    private final Resource res;
    private final MenuController contr;
    private final BinaryTree tree;
    private final Menu main;

    public App(Scanner sc, Resource res, MenuController contr) {
        this.res = res;
        this.contr = contr;
        this.tree = load();
        this.main = new Menu.Builder()
                .setScanner(sc)
                .addTitle("\n" + res.message("menu.property.title") + "\n")
                .addItem(1, res.message("menu.entry.play"), this::play)
                .addItem(2, res.message("menu.entry.list"), this::list)
                .addItem(3, res.message("menu.entry.search"),this::search)
                .addItem(4, res.message("menu.entry.statistics"), this::statistics)
                .addItem(5, res.message("menu.entry.print"), this::tree)
                .addItem(6, res.message("menu.entry.delete"),this::delete)
                .addItem(0, res.message("menu.property.exit"), this::exit)
                .addError(this::error)
                .build();
    }

    private void play() {
        new Game(tree, res).start();
    }

    private void list() {
        res.println("tree.list.animals");
        tree.leaves().stream().sorted().forEach(a ->
                res.printf("tree.list.printf",
                res.format("animalName", a),
                tree.search(res, a).size()));
    }

    private void search() {
        String animal = res.createAnimal();
        res.println("tree.search.facts", res.format("animalName", animal));
        List<String> list = tree.search(res, animal);
        if (list.isEmpty()) {
            res.println("tree.search.noFacts", animal);
        } else {
            list.forEach(a -> res.printf("tree.search.printf", a));
        }
    }

    private void delete() {
        res.println("tree.delete.root");
        res.println("tree.delete.successful");
        res.println("tree.delete.fail");
    }

    private void statistics() {

        int total = tree.counter();
        int animals = tree.leaves().size();
        int statements = total - animals;
        double average = (tree.depthSumRecursive(tree.root, 0) - 1) * 1.0 / animals;

        res.println("tree.stats.title");
        res.println("tree.stats.root", tree.root.data);
        res.println("tree.stats.nodes", total);
        res.println("tree.stats.animals", animals);
        res.println("tree.stats.statements", statements);
        res.println("tree.stats.height", tree.depth());
        res.println("tree.stats.minimum", tree.minDepth());
        res.println("tree.stats.average", average);
    }

    private void tree() {
       tree.tree(res);
    }

    private void exit() {
        try {
            Main.saver(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.printlnRandom("farewell");
        contr.exitAll();
    }

    private void error() {
        res.println("menu.property.error", 6);
    }

    private BinaryTree load() {
        BinaryTree tree;
        try {
            tree =  Main.loader();
        } catch (IOException e) {
            e.printStackTrace();
            res.println("animal.wantLearn");
            res.println("animal.askFavorite");
            String animal = res.createAnimal();
            tree = new BinaryTree();
            tree.root = new Node(animal);
        }
        res.println("welcome");
        return tree;
    }

    public void run() {
        res.printHello();
        contr.run(main);
    }

}
