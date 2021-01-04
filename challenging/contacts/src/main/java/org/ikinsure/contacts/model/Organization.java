package org.ikinsure.contacts.model;

public class Organization implements Owner {

    private String name;
    private String address;

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
    public String toString() {
        return name;
    }

    @Override
    public String info() {
        return "Organization name: " + name + "\n" +
                "Address: " + address;
    }
}
