package org.ikinsure.blockchain;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class Message {

    private final User user;
    private final String data;
    private final byte[] signature;

    public Message(User user, String data) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        this.user = user;
        this.data = data;
        this.signature = MessageManager.sign(data, user.getPrivateKey());
    }

    public User getUser() {
        return user;
    }

    public String getData() {
        return data;
    }

    public byte[] getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return user.getName() + ": " + data;
    }
}
