package org.ikinsure.blockchain;

import org.ikinsure.blockchain.block.BlockInfo;
import org.ikinsure.blockchain.economy.Transaction;
import org.ikinsure.blockchain.economy.TransactionManager;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static String sha256(BlockInfo info, String magic) {
        return sha256(info.getPrevHash() +
                        magic + info.getId() +
                        info.getTimestamp() +
                        info.getTransactions().stream().map(Transaction::getData).collect(Collectors.joining())
        );
    }

    public static String sha256(String data) {
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
            e.printStackTrace();
            return null;
        }
    }

    public static boolean validate(String hash, int zeros) {
        return hash.startsWith("0".repeat(zeros));
    }

    public static boolean verify(List<Transaction> transactions) {
        for (var transaction : transactions) {
            try {
                if (!TransactionManager.verify(transaction)) {
                    return false;
                }
            } catch (SignatureException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
