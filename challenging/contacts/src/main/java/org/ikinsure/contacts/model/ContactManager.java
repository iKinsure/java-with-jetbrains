package org.ikinsure.contacts.model;

import java.time.LocalDateTime;

public class ContactManager {

    public Contact createContact(String type) {
        Contactable owner = createOwner(type);
        return new Contact(owner, LocalDateTime.now(), LocalDateTime.now());
    }

    public Contactable createOwner(String type) {
        Contactable owner = null;
        if ("person".equals(type)) {
            owner = new Person();
        } else if ("organization".equals(type)) {
            owner = new Organization();

        }
        return owner;
    }
}
