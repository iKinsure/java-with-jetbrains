package org.ikinsure.contacts.model;

import java.util.Scanner;

public class Organization implements Contactable {

    private String name;
    private String address;

    public Organization() {

    }

    public Organization(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String record() {
        return name;
    }

    @Override
    public String info() {
        return "Organization name: " + name + "\n" +
                "Address: " + address;
    }

    @Override
    public void setFields(Scanner scanner) {
        this.name = enter(scanner, "name");
        this.address = enter(scanner, "address");
    }
}
