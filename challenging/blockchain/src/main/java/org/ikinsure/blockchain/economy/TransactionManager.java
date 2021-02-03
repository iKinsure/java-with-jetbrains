package org.ikinsure.blockchain;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.codec.binary.Base64;


public class MessageManager {

    private final Cipher cipher;

    public MessageManager() throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.cipher = Cipher.getInstance("RSA");
    }

    public String encrypt(String msg, PrivateKey key) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.encodeBase64String(cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8)));
    }

    public String decrypt(String msg, PublicKey key) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.decodeBase64(msg)), StandardCharsets.UTF_8);
    }

    public static PrivateKey getPrivateKey(String key) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(
                Base64.encodeBase64(key.getBytes(StandardCharsets.UTF_8)));
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }

    public static PublicKey getPublicKey(String key) throws InvalidKeySpecException, NoSuchAlgorithmException {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(
                Base64.encodeBase64(key.getBytes(StandardCharsets.UTF_8)));
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    public static byte[] sign(String msg, PrivateKey key) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        Signature rsa = Signature.getInstance("SHA1withRSA");
        rsa.initSign(key);
        rsa.update(msg.getBytes());
        return rsa.sign();
    }

    public static boolean verify(Message message) throws SignatureException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        Signature rsa = Signature.getInstance("SHA1withRSA");
        rsa.initVerify(message.getPublicKey());
        rsa.update(message.getData().getBytes());
        return rsa.verify(message.getSignature());
    }

}
