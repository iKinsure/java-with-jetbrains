package org.ikinsure.jsondatabase.server.model;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for tasks
 */
public class Task {

    @Expose
    private final String type;
    @Expose
    private final JsonElement key;
    @Expose
    private final JsonElement value;

    public Task(String type, JsonElement key, JsonElement value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public JsonElement getKey() {
        return key;
    }

    public JsonElement getValue() {
        return value;
    }

    public List<String> getKeys() {
        List<String> list = new ArrayList<>();
        if (key.isJsonArray()) {
            for (JsonElement e : key.getAsJsonArray()) {
                if (e.isJsonPrimitive()) {
                    list.add(e.getAsString());
                }
            }
        } else if (key.isJsonPrimitive()) {
            list.add(key.getAsString());
        }
        return list;
    }
}
