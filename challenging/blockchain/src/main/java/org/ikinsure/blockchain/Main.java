package org.ikinsure.blockchain;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static volatile BlockInfo info;
    private static final List<String> TEMPLATE_MSG = new ArrayList<>(List.of(
            "it's not fair",
            "You always will be first because it is your blockchain!",
            "You always will be first because it is your blockchain!",
            "You're welcome :)",
            "Hey Tom, nice chat",
            "Hey, I'm first!",
            "That's nice msg",
            "Is anybody out there?",
            "im waiting!",
            "Where is my blockchain?",
            "It's not your blockchain..."
    ));

    public static void main(String[] args) throws NoSuchAlgorithmException {

        final int keySize = 30;
        User u1 = new User("Tom", keySize);
        User u2 = new User("Nick", keySize);
        User u3 = new User("Sarah", keySize);

        List<Block> blockchain = Collections.synchronizedList(new ArrayList<>());
        List<Message> messages = Collections.synchronizedList(new ArrayList<>());

        final int minersCount = 4;
        ExecutorService miners = Executors.newFixedThreadPool(minersCount);

        BlockManager manager = new BlockManager(0, 5);
        info = manager.createBlockInfo(null, "");

        for (int i = 0; i < minersCount; i++) {
            miners.submit(() -> {

                outer: while (blockchain.size() < 5) {

                    final int size = blockchain.size();
                    long start = System.nanoTime();

                    String magic;
                    String hash;
                    Random random = new Random();

                    do {
                        magic = String.valueOf(random.nextInt());
                        hash = manager.sha256(info, magic);
                        if (size != blockchain.size()) {
                            continue outer;
                        }
                    } while (!manager.validate(hash));

                    int end = (int) ((System.nanoTime() - start) / 1_000_000_000);

                    Block block = manager.createBlock(info, magic);
                    synchronized (Main.class) {
                        if (block != null && blockchain.size() == size) {

                            while (messages.isEmpty()) { }
                            info = manager.createBlockInfo(block, messages);
                            messages.clear();
                            blockchain.add(block);
                            System.out.println("\nBlock:");
                            System.out.println("Created by miner # " + Thread.currentThread().getId());
                            System.out.println(block);
                            System.out.println("Block was generating for " + end + " seconds");
                            manager.updateZeros(end, size + 10);
                        }
                    }
                }
            });
        }

        miners.shutdown();

        Random random = new Random();
        while (!miners.isTerminated()) {
            long time = random.nextInt(10) * 10L;
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            messages.add(TEMPLATE_MSG.get(random.nextInt(TEMPLATE_MSG.size())));
        }
    }
}
