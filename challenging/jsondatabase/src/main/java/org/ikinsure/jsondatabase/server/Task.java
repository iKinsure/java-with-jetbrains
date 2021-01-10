package org.ikinsure.jsondatabase.server;

import com.google.gson.annotations.Expose;

/**
 * Class for tasks
 */
public class Task {

    @Expose
    private String type;
    @Expose
    private String key;
    @Expose
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
