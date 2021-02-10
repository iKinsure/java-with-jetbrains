package org.ikinsure.animals;

public class Ability implements Fact {

    private final String fact;

    public Ability(String fact) {
        this.fact = fact;
    }

    @Override
    public String question() {
        return "Can it " + fact + "?";
    }

    @Override
    public String sentence(Animal animal, boolean negated) {
        String str1 = animal.getDefined() + " can " + fact + ".";
        String str2 = animal.getDefined() + " can't " + fact + ".";
        return negated ? str2 : str1;
    }
}
