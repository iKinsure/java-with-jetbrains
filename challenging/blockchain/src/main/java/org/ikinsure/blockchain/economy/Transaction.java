package org.ikinsure.blockchain;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class Message {

    private final PublicKey publicKey;
    private final String name;
    private final String data;
    private final byte[] signature;

    public Message(User user, String data) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        this.publicKey = user.getPublicKey();
        this.name = user.getName();
        this.data = data;
        this.signature = MessageManager.sign(data, user.getPrivateKey());
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public byte[] getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return name + ": " + data;
    }
}
