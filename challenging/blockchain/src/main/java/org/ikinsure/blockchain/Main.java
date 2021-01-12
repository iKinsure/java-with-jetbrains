package org.ikinsure.blockchain;

import org.ikinsure.blockchain.logic.Block;
import org.ikinsure.blockchain.logic.BlockManager;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static final String FILE = "blockchain.obj";

    public static void main(String[] args) {

        Block block = null;
//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
//            block = (Block) in.readObject();
//        } catch (ClassNotFoundException | IOException e) {
//            block = null;
//        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter how many zeros the hash must start with: ");

        BlockManager manager = new BlockManager(scanner.nextInt(), block);

        Block b1 = manager.createBlock();
        Block b2 = manager.createBlock();
        Block b3 = manager.createBlock();
        Block b4 = manager.createBlock();
        Block b5 = manager.createBlock();

//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
//            out.writeObject(b5);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
