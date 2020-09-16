package correcter;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        StringBuilder b = new StringBuilder(new Scanner(System.in).nextLine());
        for (int i = 2; i < b.length(); i += 3) {
            b.setCharAt(i - r.nextInt(3), (char) (r.nextInt(94) + 32));
        }
        System.out.println(b.toString());
    }
}
