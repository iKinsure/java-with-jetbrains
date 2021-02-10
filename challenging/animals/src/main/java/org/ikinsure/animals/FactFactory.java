package org.ikinsure.animals;

public class FactFactory {

    public Fact createFact(String s) {
        Fact f = null;
        if (s.startsWith("it can ")) {
            f = new Ability(s.substring(7));
        } else if (s.startsWith("it has ")) {
            f = new Possession(s.substring(7));
        } else if (s.startsWith("it is ")) {
            f = new Linking(s.substring(6));
        }
        return f;
    }

    public Animal createAnimal(String s) {
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
