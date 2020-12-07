package org.ikinsure.encryptdecrypt;

public class EncodeStrategy {

    private EncodeInterface method;

    public void setMethod(EncodeInterface method) {
        this.method = method;
    }

    public String encode(String text, int key) {
        return this.method.encode(text, key);
    }
}
