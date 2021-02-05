package org.ikinsure.animals;

public class AnimalFactory {

    public Animal parse(String s) {
        Animal a;
        if (s.startsWith("an ")) {
            a = new Animal(s.substring(3), true);
        } else if (s.startsWith("a ")) {
            a = new Animal(s.substring(2), false);
        } else {
            a = new Animal(s, isVowel(s.charAt(0)));
        }
        return a;
    }

    private boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
}
