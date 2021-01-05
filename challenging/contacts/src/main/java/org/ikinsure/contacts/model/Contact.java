package org.ikinsure.contacts.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Contact implements Settable {

    private final List<Entry> properties;
    private final LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
    private final int[] indexes;

    Contact(List<Entry> properties, LocalDateTime timeCreated, LocalDateTime timeUpdated, int... indexes) {
        this.properties = properties;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.indexes = indexes;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(LocalDateTime timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public Entry findEntryByKey(String key) {
        return properties.stream().filter(p -> p.key.equals(key)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public String getPropertiesKeysAsString() {
       return properties.stream().map(p -> p.key).collect(Collectors.joining(", "));
    }

    @Override
    public void setValue(Scanner scanner) {
        properties.forEach(p -> p.setValue(scanner));
    }

    public String getInfo() {
        return properties.stream().map(p -> upper(p.key) + ": " + p.value).collect(Collectors.joining("\n")) + "\n" +
                "Time created: " + timeCreated.withSecond(0).withNano(0) + "\n" +
                "Time last edit: " + timeUpdated.withSecond(0).withNano(0);
    }

    private String upper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @Override
    public String toString() {
        return IntStream.of(indexes).mapToObj(i -> properties.get(i).value).collect(Collectors.joining(" "));
    }
}
