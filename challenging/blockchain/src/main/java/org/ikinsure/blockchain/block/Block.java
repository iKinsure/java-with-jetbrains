package org.ikinsure.blockchain.block;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Blockchain single block
 */
public final class Block implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BlockInfo info;
    private final String hash;
    private final String magic;

    Block(BlockInfo info, String hash, String magic) {
        this.info = info;
        this.hash = hash;
        this.magic = magic;
    }

    public BigInteger getId() {
        return info.getId();
    }

    public String getHash() {
        return hash;
    }

    public String getMagic() {
        return magic;
    }

    public BlockInfo getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "Id: " + info.getId() + "\n" +
                "Timestamp: " + info.getTimestamp() + "\n" +
                "Magic number: " + magic + "\n" +
                "Hash of the previous block: \n" + info.getPrevHash() + "\n" +
                "Hash of the block: \n" + hash + "\n" +
                "Block data: " + info.format();
    }
}
