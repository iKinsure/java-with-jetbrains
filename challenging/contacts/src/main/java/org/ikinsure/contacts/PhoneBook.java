package org.ikinsure.contacts;

import org.ikinsure.contacts.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneBook {

    private final List<Contact> book;

    public PhoneBook() {
        this.book = new ArrayList<>();
    }

    public int size() {
        return book.size();
    }

    public void remove(int index) {
        book.remove(index);
    }

    public void add(Contact contact) {
        book.add(contact);
    }

    public Contact get(int index) {
        return book.get(index);
    }

    public List<Contact> getBook() {
        return book;
    }

    public List<Contact> search(String str) {
        return book.stream()
                .filter(c -> c.getPropertiesValuesAsString().toLowerCase().contains(str.toLowerCase()))
                .collect(Collectors.toList());
    }
}
