package org.ikinsure.blockchain.block;

import org.ikinsure.blockchain.economy.Transaction;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Template for block
 */
public class BlockInfo implements Serializable {

    private final BigInteger id;
    private final BigInteger timestamp;
    private final String prevHash;
    private final List<Transaction> transactions;

    BlockInfo(BigInteger id, BigInteger timestamp, String prevHash, List<Transaction> transactions) {
        this.id = id;
        this.timestamp = timestamp;
        this.prevHash = prevHash;
        this.transactions = transactions;
    }

    public BigInteger getId() {
        return id;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String format() {
        if (transactions.isEmpty()) {
            return "no transactions";
        }
        return "\n" + transactions.stream().map(Transaction::getData).collect(Collectors.joining("\n"));
    }
}
