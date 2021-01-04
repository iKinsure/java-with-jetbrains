package org.ikinsure.contacts.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Contact {

    private Owner owner;
    private String number;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;

    public Contact(Owner owner, String number, LocalDateTime timeCreated, LocalDateTime timeUpdated) {
        this.owner = owner;
        this.number = number;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(LocalDateTime timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    @Override
    public String toString() {
        return owner.toString();
    }

    public String info() {
        return owner.info() + "\n" +
                "Number: " + number + "\n" +
                "Time created: " + timeCreated.withSecond(0).withNano(0) + "\n" +
                "Time last edit: " + timeUpdated.withSecond(0).withNano(0);
    }
}
