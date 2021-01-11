package org.ikinsure.jsondatabase.server.model;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

/**
 * Database record
 */
public class Record {

    @Expose
    private final String key;
    @Expose
    private final JsonElement value;

    public Record(String key, JsonElement value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public JsonElement getValue() {
        return value;
    }
}
