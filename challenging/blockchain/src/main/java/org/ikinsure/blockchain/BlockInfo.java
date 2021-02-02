package org.ikinsure.blockchain;

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
    private final List<Message> messages;

    public BlockInfo(BigInteger id, BigInteger timestamp, String prevHash, List<Message> messages) {
        this.id = id;
        this.timestamp = timestamp;
        this.prevHash = prevHash;
        this.messages = messages;
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

    public List<Message> getMessages() {
        return messages;
    }

    public String format() {
        return messages.stream().map(Message::toString).collect(Collectors.joining("\n"));
    }
}
