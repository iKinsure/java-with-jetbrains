package com.ikinsure.hyperskill.hard.flashcards;

public class Card {

    private final String term;
    private final String definition;

    public Card(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }
}
