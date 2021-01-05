package org.ikinsure.contacts;

import org.ikinsure.contacts.model.Contact;
import org.ikinsure.contacts.model.Printable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PhoneBook implements Printable {

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

    public void edit(int index, Contact contact) {
        book.set(index, contact);
    }

    public void add(Contact contact) {
        book.add(contact);
    }

    public Contact get(int index) {
        return book.get(index);
    }


    @Override
    public String record() {
        AtomicInteger counter = new AtomicInteger(1);
        return book.stream()
                .map(c -> counter.getAndIncrement() + ". " + c.record())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String info() {
        return null;
    }
}
