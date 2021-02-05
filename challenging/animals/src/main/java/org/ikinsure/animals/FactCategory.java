package org.ikinsure.animals;

public enum FactCategory {

    ABILITY("can", "can't", "can"),
    LINKING("is", "isn't", "is"),
    POSSESS("has", "doesn't have", "does");

    private final String verb;
    private final String negVerb;
    private final String question;
    FactCategory(String verb, String negVerb, String question) {
        this.verb = verb;
        this.negVerb = negVerb;
        this.question = question;
    }

    public String getVerb() {
        return verb;
    }

    public String getNegVerb() {
        return negVerb;
    }

    public String getQuestion() {
        return question;
    }
}
