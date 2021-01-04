package org.ikinsure.contacts.model;

public class Person implements Owner {

    private String name;
    private String surname;
    private String birth;
    private String gender;

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
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public String info() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + birth + "\n" +
                "Gender: " + gender;
    }
}
