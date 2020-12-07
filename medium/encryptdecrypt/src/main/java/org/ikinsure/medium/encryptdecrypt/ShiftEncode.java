package org.ikinsure.medium.encryptdecrypt;

public class ShiftEncode implements EncodeInterface {

    public static final int RANGE = 26;

    @Override
    public String encode(String t, int k) {
        k = (k >= 0) ? k % RANGE : RANGE + k % RANGE;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < t.length(); i++) {
            char sign = t.charAt(i);
            if (Character.isAlphabetic(sign)) {
                char startChar = Character.isLowerCase(sign) ? 'a' : 'A';
                int newPos = (sign - startChar + k) % RANGE;
                sign = (char) (startChar + newPos);
            }
            builder.append(sign);
        }
        return builder.toString();
    }
}
