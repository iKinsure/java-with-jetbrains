package org.ikinsure.contacts.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * manages contacts, place here new group of contacts
 */
public class ContactManager implements Serializable {

    private final List<Entry> person = new ArrayList<>(List.of(
            new Entry("name", "Name", "[no data]"),
            new Entry("surname", "Surname", "[no data]"),
            new Entry("birth", "Birth date", "[no data]"),
            new Entry("gender", "Gender", "[no data]", new ArrayList<>(List.of("F", "M"))),
            new Entry("number", "Number", "[no number]", this::phonePredicate)));
    private final List<Entry> organization = new ArrayList<>(List.of(
            new Entry("name", "Organization name", "[no data]"),
            new Entry("address", "Address", "[no data]"),
            new Entry("number", "Number","[no number]", this::phonePredicate)));

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
