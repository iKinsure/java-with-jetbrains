package org.ikinsure.blockchain;

public class Main {
    public static void main(String[] args) {

        Block[] blockchain = new Block[10];
        blockchain[0] = new Block("0");
        for (int i = 1; i < blockchain.length; i++) {
            blockchain[i] = new Block(blockchain[i - 1].getHash());
        }

        for (Block block : blockchain) {
            System.out.println(block + "\n");
        }

    }
}
