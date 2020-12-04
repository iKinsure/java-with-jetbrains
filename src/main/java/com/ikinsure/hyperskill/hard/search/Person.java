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

    @Override
    public String[] fields() {
        return new String[]{firstName.toLowerCase(), lastName.toLowerCase(), email.toLowerCase()};
    }

    @Override
    public String toString() {
        return firstName + (lastName.isEmpty() ? "" : " ") + lastName + (email.isEmpty() ? "" : " ")  + email;
    }
}
