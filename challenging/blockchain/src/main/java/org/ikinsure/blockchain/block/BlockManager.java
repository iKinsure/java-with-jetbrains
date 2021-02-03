package org.ikinsure.blockchain.block;

import org.ikinsure.blockchain.Miner;
import org.ikinsure.blockchain.Util;
import org.ikinsure.blockchain.economy.Transaction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BlockManager {

    private final List<Block> blockchain;
    private final List<Transaction> transactions;
    private final int averageTime;

    private int zeros;
    private BlockInfo info;
    private long startTime;
    private Block prevBlock;

    public BlockManager(int zeros, int averageTime) {
        this.zeros = zeros;
        this.averageTime = averageTime;
        this.blockchain = Collections.synchronizedList(new ArrayList<>());
        this.transactions = Collections.synchronizedList(new ArrayList<>());
        this.startTime = System.nanoTime();
        this.info = createBlockInfo(null);
    }

    /**
     * verify magic and adds to blockchain new block
     * @param magic for current BlockInfo
     * @return true if success
     */
    public synchronized boolean createBlock(String magic, Miner miner) {
        if (info != null) {
            String hash = Util.sha256(info, magic);
            if (Util.validate(hash, zeros) && Util.verify(info.getTransactions())) {

                prevBlock = new Block(info, hash, magic);
                blockchain.add(prevBlock);

                int time = (int) ((System.nanoTime() - startTime) / 1_000_000_000L);
                printInfo(prevBlock, time, miner);
                updateZeros(time);
                startTime = System.nanoTime();

                info = null;
                return true;
            }
        }
        return false;
    }


    /**
     * creates template for next block
     * @param block previous block
     * @return new template for block with transactions
     */
    private synchronized BlockInfo createBlockInfo(Block block) {
        BigInteger id = block == null ? BigInteger.ONE : block.getId().add(BigInteger.ONE);
        BigInteger timestamp = BigInteger.valueOf(new Date().getTime());
        String prevHash = block == null ? "0" : block.getHash();
        List<Transaction> copy = new ArrayList<>(transactions);
        transactions.clear();
        return new BlockInfo(id, timestamp, prevHash, copy);
    }

    private synchronized void printInfo(Block block, int time, Miner miner) {
        System.out.println("\nBlock:");
        System.out.println("Created by: " + miner.getUser().getName());
        System.out.println(miner.getUser().getName() + " gets 100 VC");
        System.out.println(block);
        System.out.println("Block was generating for " + time + " seconds");
    }

    /**
     * increase or decrease mining time
     */
    private synchronized void updateZeros(int time) {
        if (averageTime > time) {
            zeros++;
            System.out.println("N was increased to " + zeros);
        } else if (averageTime == time) {
            System.out.println("N stays the same");
        } else {
            this.zeros = zeros == 0 ? 0 : zeros - 1;
            System.out.println("N was decreased to " + zeros);
        }
    }

    public int getZeros() {
        return zeros;
    }

    public synchronized BlockInfo getInfo() {
        return info;
    }

    public synchronized void add(Transaction transaction) {
        transactions.add(transaction);
        if (info == null) {
            info = createBlockInfo(prevBlock);
        }
    }

    public int size() {
        return blockchain.size();
    }
}
