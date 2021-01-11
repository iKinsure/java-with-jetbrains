package org.ikinsure.jsondatabase.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;

/**
 * The receiver class
 */
public class DatabaseConnection {

    private final FileOperator operator;

    public DatabaseConnection(String file, Gson gson) {
        this.operator = new FileOperator(file, gson);
    }

    public synchronized Response get(Task task) {
        List<String> keys = task.getKeys();
        List<Record> list = operator.read();
        Record record = list.stream()
                .filter(r -> r.key.equals(keys.get(0)))
                .findFirst()
                .orElse(null);

        if (record == null) {
            return Response.NO_KEY;
        }

        JsonElement elem = record.value;
        for (int i = 1; i < keys.size(); i++) {
            if (elem.isJsonObject()) {
                elem = elem.getAsJsonObject().get(keys.get(i));
            } else if (elem.isJsonPrimitive()) {
                elem = elem.getAsJsonPrimitive();
            }
        }

        return Response.builder()
                .setResponse("OK")
                .setValue(elem)
                .build();
    }

    public synchronized Response set(Task task) {
        List<String> keys = task.getKeys();
        List<Record> list = operator.read();
        Record record = list.stream()
                .filter(r -> r.key.equals(keys.get(0)))
                .findFirst()
                .orElse(null);

        if (record == null) {
            record = new Record();
            record.key = keys.get(0);
            record.value = task.value;
            list.add(record);
        } else {


            if (keys.size() >= 2) {

                JsonElement elem = record.value;
                for (int i = 1; i < keys.size() - 1; i++) {
                    if (elem.isJsonObject()) {
                        elem = elem.getAsJsonObject().get(keys.get(i));
                    }
                }

                if (elem.isJsonObject()) {
                    if (task.value.isJsonPrimitive()) {
                        elem.getAsJsonObject().addProperty(keys.get(keys.size() - 1), task.value.getAsString());
                    } else if (task.value.isJsonObject()) {
                        System.out.println("?????????????????????????");
                    }
                }

            } else {
                record.value = task.value;
            }
        }
        operator.save(list);
        return Response.OK;
    }

    public synchronized Response remove(Task task) {
        List<String> keys = task.getKeys();
        List<Record> list = operator.read();
        Record record = list.stream()
                .filter(r -> r.key.equals(keys.get(0)))
                .findFirst()
                .orElse(null);

        if (record == null) {
            return Response.NO_KEY;
        }

        if (keys.size() >= 2) {
            JsonElement elem = record.value;
            for (int i = 1; i < keys.size() - 1; i++) {
                if (elem.isJsonObject()) {
                    elem = elem.getAsJsonObject().get(keys.get(i));
                }
            }
            elem.getAsJsonObject().remove(keys.get(keys.size() - 1));
        } else {
            list.remove(record);
        }

        operator.save(list);
        return Response.OK;
    }
}
