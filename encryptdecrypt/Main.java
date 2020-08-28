package encryptdecrypt;

public class Main {

    public static final String TEXT = "we found a treasure!";
    public static final char MIN_CHAR = 'a'; // 97
    public static final char MAX_CHAR = 'z'; // 122

    public static void main(String[] args) {

        for (int i = 0; i < TEXT.length(); i++) {
            char sign = TEXT.charAt(i);
            System.out.print(isCharInRange(sign) ? (char) (MAX_CHAR + MIN_CHAR - sign) : sign);
        }
    }

    private static boolean isCharInRange(char sign) {
        return (sign >= MIN_CHAR && sign <= MAX_CHAR);
    }
}
