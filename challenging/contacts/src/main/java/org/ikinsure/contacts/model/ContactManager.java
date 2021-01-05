package org.ikinsure.contacts.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class ContactManager {


    private final List<Entry> person = new ArrayList<>(List.of(
            new Entry("name", "[no data]"),
            new Entry("surname", "[no data]"),
            new Entry("birth", "[no data]"),
            new Entry("gender", "[no data]", new ArrayList<>(List.of("F", "M"))),
            new Entry("number", "[no number]", this::phonePredicate)));
    private final List<Entry> organization = new ArrayList<>(List.of(
            new Entry("name", "[no data]"),
            new Entry("address", "[no data]"),
            new Entry("number", "[no number]", this::phonePredicate)));



    public Contact createContact(String type) {
        if ("person".equals(type)) {
            return new Contact(copy(person), LocalDateTime.now(), LocalDateTime.now(), 0, 1);
        } else if ("organization".equals(type)) {
            return new Contact(copy(organization), LocalDateTime.now(), LocalDateTime.now(), 0);
        }
        throw new IllegalArgumentException("there is no " + type + " type");
    }

    private boolean phonePredicate(String data) {
        String regex = "^\\+?([\\da-zA-Z]+[\\s-]?)?(\\([\\da-zA-Z]{2,}(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$";
        return Pattern.compile(regex).matcher(data).matches();
    }

    private List<Entry> copy(List<Entry> data) {
        List<Entry> res = new ArrayList<>(data.size());
        for (Entry entry : data) {
            res.add(new Entry(entry));
        }
        return res;
    }
}
