package org.ikinsure.contacts.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Contact implements Contactable, Datable {

    private Contactable owner;
    private String number;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
    private HashMap<String, String> properties;

    public Contact(Contactable owner, LocalDateTime timeCreated, LocalDateTime timeUpdated) {
        this.owner = owner;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.properties = new HashMap<>();
    }

    public Contactable getOwner() {
        return owner;
    }

    public void setOwner(Contactable owner) {
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
    public String record() {
        return owner.record();
    }

    @Override
    public String info() {
        return owner.info() + "\n" +
                "Number: " + number + "\n" +
                "Time created: " + timeCreated.withSecond(0).withNano(0) + "\n" +
                "Time last edit: " + timeUpdated.withSecond(0).withNano(0);
    }

    @Override
    public void setFields(Scanner scanner) {
        owner.setFields(scanner);
        this.number = enter(scanner, "number", this::predicate);
    }

    private boolean predicate(String data) {
        String regex = "^\\+?([\\da-zA-Z]+[\\s-]?)?(\\([\\da-zA-Z]{2,}(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(data).matches();
    }

    @Override
    public void setProperty(String key, String value) {
        properties.replace(key, value);
    }

    @Override
    public String getProperty(String key) {
        return properties.get(key);
    }
}
