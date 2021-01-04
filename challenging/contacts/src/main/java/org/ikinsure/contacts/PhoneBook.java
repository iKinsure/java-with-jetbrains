package org.ikinsure.contacts;

import java.util.ArrayList;
import java.util.List;

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
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        for (var contact : book) {
            builder.append(counter).append(". ").append(contact).append("\n");
            counter++;
        }
        return builder.toString();
    }
}
