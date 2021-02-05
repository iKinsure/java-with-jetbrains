package org.ikinsure.animals;

public class Fact {

    private final String name;
    private final FactCategory category;
    private final boolean negated;

    public Fact(String name, FactCategory category, boolean negated) {
        this.name = name;
        this.category = category;
        this.negated = negated;
    }

    public String getName() {
        return name;
    }

    public FactCategory getCategory() {
        return category;
    }

    public boolean isNegated() {
        return negated;
    }
}
