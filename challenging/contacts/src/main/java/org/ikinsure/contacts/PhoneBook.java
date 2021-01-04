package org.ikinsure.contacts;

import org.ikinsure.contacts.model.Contact;

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
        for (int i = 0; i < book.size(); i++) {
            builder.append(i + 1).append(". ").append(book.get(i));
            if (i != book.size() - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
