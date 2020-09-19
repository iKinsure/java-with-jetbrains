package hard.budget;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        StringBuilder data = new StringBuilder();
        double sum = 0.0;
        while (SCANNER.hasNextLine()) {
            String s = SCANNER.nextLine();
            sum += Double.parseDouble(s.substring(s.indexOf("$") + 1));
            data.append(s).append("\n");
        }
        System.out.println(data.toString() + "\n");
        System.out.println("Total: $" + sum);
    }
}
