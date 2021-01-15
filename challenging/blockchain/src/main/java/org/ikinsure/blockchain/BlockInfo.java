package org.ikinsure.blockchain;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Template for block
 */
public class BlockInfo implements Serializable {

    private final BigInteger id;
    private final BigInteger timestamp;
    private final String prevHash;

    public BlockInfo(BigInteger id, BigInteger timestamp, String prevHash) {
        this.id = id;
        this.timestamp = timestamp;
        this.prevHash = prevHash;
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
}
