package org.ikinsure.jsondatabase.server;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for tasks
 */
public class Task {

    @Expose
    String type;
    @Expose
    JsonElement key;
    @Expose
    JsonElement value;

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
