package org.ikinsure.animals;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class DataFormatter {

    public String createAnimal(String data) {
        if (!(data.startsWith("a ") || data.startsWith("an "))) {
            data = isVowel(data.charAt(0)) ? "an " + data : "a " + data;
        }
        return data;
    }

    public String createFact(String data) {
        return capitalize(data);
    }

    public String createProp(String animal, String fact) {
        animal = animal.startsWith("an ") ? animal.substring(3) : animal.substring(2);
        return "The " + animal + " " + fact.substring(3) + ".";
    }

    public String createCon(String animal, String fact) {
        animal = animal.startsWith("an ") ? animal.substring(3) : animal.substring(2);
        return "The " + animal + " " + negateFact(fact).substring(3) + ".";
    }

    public String question(String fact) {
        if (fact.startsWith("It can ")) {
            return "Can it " + fact.substring(7) + "?";
        } else if (fact.startsWith("It has ")) {
            return "Has it " + fact.substring(7) + "?";
        } else if (fact.startsWith("It is ")) {
            return "Is it " + fact.substring(6) + "?";
        }
        throw new UnsupportedOperationException();
    }


    public String negateFact(String fact) {
        fact = fact.substring(3);
        if (fact.startsWith("can")) {
            return "It can't" + fact.substring(3);
        } else if (fact.startsWith("has")) {
            return "It doesn't have" + fact.substring(3);
        } else if (fact.startsWith("is")) {
            return "It isn't" + fact.substring(2);
        }
        throw new UnsupportedOperationException();
    }

    public String generateHello() {
        LocalTime time = LocalTime.now();
        if (time.isBefore(LocalTime.of(5, 0))) {
            return "Hi, Night Owl!";
        } else if (time.isBefore(LocalTime.of(12, 0))) {
            return "Good morning!";
        } else if (time.isBefore(LocalTime.of(18, 0))) {
            return "Good evening!";
        } else {
            return "Good afternoon!";
        }
    }

    public String randomQuote(Random random, List<String> data) {
        return data.get(random.nextInt(data.size()));
    }

    private boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    private String capitalize(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
