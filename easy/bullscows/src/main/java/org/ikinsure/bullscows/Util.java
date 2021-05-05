package org.ikinsure.bullscows;

public class Util {

    /**
     * build a score string
     */
    public static String grade(int bulls, int cows) {
        String s;
        if (bulls >= 1 && cows >= 1) {
            s = bulls + " " + (bulls == 1 ? "bull" : "bulls") +
                    " and " + cows + " " + (cows == 1 ? "cow" : "cows");
        } else if (bulls >= 1) {
            s = bulls + " " + (bulls == 1 ? "bull" : "bulls");
        } else if (cows >= 1) {
            s = cows + " " + (cows == 1 ? "cow" : "cows");
        } else {
            s = "None";
        }
        return "Grade: " + s + ".";
    }

}
