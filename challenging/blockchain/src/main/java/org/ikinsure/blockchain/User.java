package org.ikinsure.blockchain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class User {

    private final String name;
    private final String privateKey;
    private final String publicKey;

    public User(String name, int size) throws NoSuchAlgorithmException {
        this.name = name;
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(size);
        KeyPair pair = generator.generateKeyPair();
        this.privateKey = new String(pair.getPrivate().getEncoded());
        this.publicKey = new String(pair.getPublic().getEncoded());
    }

    public String getName() {
        return name;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
