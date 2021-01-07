package org.ikinsure.contacts;

import org.ikinsure.contacts.model.Contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * list of contacts
 */
public class PhoneBook implements Serializable {

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
                .filter(c -> c.getPropertiesValues().toLowerCase().contains(str.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static String list(List<Contact> book) {
        AtomicInteger counter = new AtomicInteger(1);
        return book.stream()
                .map(c -> counter.getAndIncrement() + ". " + c.toString())
                .collect(Collectors.joining("\n"));
    }
}
