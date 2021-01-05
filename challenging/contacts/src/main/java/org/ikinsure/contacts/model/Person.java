package org.ikinsure.contacts.model;

import java.util.Map;
import java.util.Scanner;

public class Person implements Contactable {

    private String name;
    private String surname;
    private String birth;
    private String gender;


    public Person() {

    }

    public Person(String name, String surname, String birth, String gender) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String record() {
        return name + " " + surname;
    }

    @Override
    public String info() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + birth + "\n" +
                "Gender: " + gender;
    }

    @Override
    public void setFields(Scanner scanner) {
        this.name = enter(scanner, "name");
        this.surname = enter(scanner, "surname");
        this.birth = enter(scanner, "birth");
        this.gender = enter(scanner, "gender","M", "F");
    }

}
