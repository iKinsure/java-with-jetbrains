package org.ikinsure.animals;

public interface Fact {
    String question();
    String sentence(Animal animal, boolean negated);
}
