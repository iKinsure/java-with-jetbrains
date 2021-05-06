package org.ikinsure.processor;

public class Decision {

    private final int id;
    private final String description;
    private final Executable executable;

    public Decision(int id, String description, Executable executable) {
        this.id = id;
        this.description = description;
        this.executable = executable;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Executable getExecutable() {
        return executable;
    }

}

