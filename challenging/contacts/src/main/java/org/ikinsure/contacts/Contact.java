package org.ikinsure.contacts;

public class Contact {

    private final String name;
    private final String surname;
    private final String number;

    public Contact(String name, String surname, String number) {
        this.name = name;
        this.surname = surname;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return name + " " + surname + ", " + number;
    }

}
