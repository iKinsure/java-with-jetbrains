package org.ikinsure.animals;

public class Animal implements Fact {

    private final String name;
    private final boolean vowel;

    public Animal(String name, boolean vowel) {
        this.name = name;
        this.vowel = vowel;
    }

    public String getDefined() {
        return "The " + name;
    }

    public String getUndefined() {
        return (vowel ? "an " : "a ") + name;
    }

    @Override
    public String question() {
        return "Is it " + getUndefined() + "?";
    }

    @Override
    public String sentence(Animal animal, boolean negated) {
        return null;
    }
}
