package encryptdecrypt;

public class ShiftEncode implements EncodeInterface {

    @Override
    public String encode(String t, int k) {
        k = (k >= 0) ? k % 26 : 26 - (-k % 26);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < t.length(); i++) {
            char sign = t.charAt(i);
            if (isInRange(Character.toLowerCase(sign))) {
                if (Character.isLowerCase(sign)) {
                    int origPos = sign - 'a';
                    int newPos = (origPos + k) % 26;
                    sign = (char) ('a' + newPos);
                } else {
                    int origPos = sign - 'A';
                    int newPos = (origPos + k) % 26;
                    sign = (char) ('A' + newPos);
                }
            }
            builder.append(sign);
        }
        return builder.toString();
    }

    private boolean isInRange(char sign) {
        if (sign > 'z' || sign < 'a') {
            return false;
        }
        return true;
    }
}
