package hard.minesweeper;

import java.util.Random;

public class Main {

    public static Random random =  new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            StringBuilder builder = new StringBuilder(".........");
            builder.setCharAt(random.nextInt(9), 'X');
            System.out.println(builder);
        }
    }
}
