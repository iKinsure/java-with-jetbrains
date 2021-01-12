package org.ikinsure.blockchain;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;

public final class Block {

    private final BigInteger id;
    private final BigInteger timestamp;
    private final String prevHash;
    private final String hash;
    private static BigInteger totalId = BigInteger.ONE;

    public Block(String prevHash) {
        this.prevHash = prevHash;
        this.id = totalId;
        this.timestamp = BigInteger.valueOf(new Date().getTime());
        this.hash = applySha256();
        totalId = totalId.add(BigInteger.ONE);
    }

    private String applySha256() {
        String data = prevHash + id + timestamp;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexes = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1)  hexes.append('0');
                hexes.append(hex);
            }
            return hexes.toString();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "Block:\n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timestamp + "\n" +
                "Hash of the previous block: \n" +
                prevHash + "\n" +
                "Hash of the block: \n" +
                hash;
    }
}
