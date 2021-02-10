package org.ikinsure.animals;

public class Possession implements Fact {

    private final String fact;

    public Possession(String fact) {
        this.fact = fact;
    }

    @Override
    public String question() {
        return "Has it " + fact + "?";
    }

    @Override
    public String sentence(Animal animal, boolean negated) {
        String str1 = animal.getDefined() + " does " + fact + ".";
        String str2 = animal.getDefined() + " doesn't have " + fact + ".";
        return negated ? str2 : str1;
    }
}
