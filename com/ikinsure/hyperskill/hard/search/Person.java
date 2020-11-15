package com.ikinsure.hyperskill.hard.search;

public class Person implements Mappable {

    private final String firstName;
    private final String lastName;
    private final String email;

    protected Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    protected static Person parsePerson(String parser) {
        String[] data = parser.split("\\s+");
        return new Person(
                data.length >= 1 ? data[0] : "",
                data.length >= 2 ? data[1] : "",
                data.length >= 3 ? data[2] : "");
    }

    protected String getFirstName() {
        return firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    protected String getEmail() {
        return email;
    }

    @Override
    public String[] fields() {
        return new String[]{firstName, lastName, email};
    }

    @Override
    public String toString() {
        return firstName + (lastName.isEmpty() ? "" : " ") + lastName + (email.isEmpty() ? "" : " ")  + email;
    }
}
