package org.ikinsure.animals;

public class Linking implements Fact {

    private final String fact;

    public Linking(String fact) {
        this.fact = fact;
    }

    @Override
    public String question() {
        return "Is it " + fact + "?";
    }

    @Override
    public String sentence(Animal animal, boolean negated) {
        String str1 = animal.getDefined() + " is " + fact + ".";
        String str2 = animal.getDefined() + " isn't " + fact + ".";
        return negated ? str2 : str1;
    }
}
