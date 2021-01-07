package org.ikinsure.contacts.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * single contact in phone
 */
public class Contact implements Settable, Printable {

    private final List<Entry> properties; // all fields
    private final LocalDateTime timeCreated;
    private LocalDateTime timeUpdated; // last update
    private final int[] indexes; // indexes of the most important properties in toString()

    Contact(List<Entry> properties, LocalDateTime timeCreated, LocalDateTime timeUpdated, int... indexes) {
        this.properties = properties;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.indexes = indexes;
    }

    public void updateTime() {
        this.timeUpdated = LocalDateTime.now();
    }

    public Entry findEntryByKey(String key) {
        return properties.stream().filter(p -> p.key.equals(key)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String getPropertiesKeys() {
       return properties.stream().map(p -> p.key).collect(Collectors.joining(", "));
    }

    @Override
    public String getPropertiesValues() {
        return properties.stream().map(p -> p.value).collect(Collectors.joining(" "));
    }

    @Override
    public String getProperties() {
        return properties.stream().map(p -> p.printKey + ": " + p.value).collect(Collectors.joining("\n")) + "\n" +
                "Time created: " + timeCreated.withSecond(0).withNano(0) + "\n" +
                "Time last edit: " + timeUpdated.withSecond(0).withNano(0);
    }

    @Override
    public void setValue(Scanner scanner) {
        properties.forEach(p -> p.setValue(scanner));
    }

    @Override
    public String toString() {
        return IntStream.of(indexes).mapToObj(i -> properties.get(i).value).collect(Collectors.joining(" "));
    }
}
