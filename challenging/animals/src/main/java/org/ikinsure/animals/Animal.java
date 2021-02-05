package org.ikinsure.animals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Animal {

    private final String name;
    private final boolean vowel;
    private final List<Fact> facts;

    public Animal(String name, boolean vowel) {
        this.name = name;
        this.vowel = vowel;
        this.facts = new ArrayList<>();
    }

    public void add(Fact fact) {
        facts.add(fact);
    }

    public String getDefined() {
        return "the " + name;
    }

    public String getUndefined() {
        return (vowel ? "an " : "a ") + name;
    }

    public String getProperties() {
        return facts.stream().map(f -> " - The " + name + " " +
                        (f.isNegated() ? f.getCategory().getNegVerb() : f.getCategory().getVerb()) + " " + f.getName() + ".")
                .collect(Collectors.joining("\n"));
//        StringBuilder builder = new StringBuilder();
//        for (var fact : facts) {
//            builder.append(" - The ")
//                    .append(name)
//                    .append(" ")
//                    .append(fact.isNegated() ? fact.getCategory().getNegVerb() : fact.getCategory().getVerb())
//                    .append(" ")
//                    .append(vowel ? "an " : "a ")
//                    .append(fact.getName()).append(" animal\n");
//        }
//        return builder.toString();
    }
}
