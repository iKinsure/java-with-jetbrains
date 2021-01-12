package org.ikinsure.blockchain.logic;

import java.io.Serializable;
import java.math.BigInteger;

public final class Block implements Serializable {

    private static final long serialVersionUID = 1L;
    private final BigInteger id;
    private final BigInteger timestamp;
    private final String prevHash;
    private final String hash;
    private final String magic;

    Block(BigInteger id, BigInteger timestamp, String prevHash, String hash, String magic) {
        this.id = id;
        this.timestamp = timestamp;
        this.prevHash = prevHash;
        this.hash = hash;
        this.magic = magic;
    }

    public String getHash() {
        return hash;
    }

    public BigInteger getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Block:\n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timestamp + "\n" +
                "Magic number: " + magic + "\n" +
                "Hash of the previous block: \n" +
                prevHash + "\n" +
                "Hash of the block: \n" +
                hash;
    }
}
