package org.ikinsure.jsondatabase.server.database;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.ikinsure.jsondatabase.server.model.Record;
import org.ikinsure.jsondatabase.server.model.Response;
import org.ikinsure.jsondatabase.server.model.Task;
import org.ikinsure.jsondatabase.server.io.FileOperator;

import java.util.*;

/**
 * The receiver class
 */
public class DatabaseConnection implements Connection {

    private final FileOperator operator;

    public DatabaseConnection(String file, Gson gson) {
        this.operator = new FileOperator(file, gson);
    }

    @Override
    public synchronized Response get(Task task) {
        List<String> keys = task.getKeys();
        List<Record> list = operator.read();
        Record record = list.stream()
                .filter(r -> r.getKey().equals(keys.get(0)))
                .findFirst()
                .orElse(null);

        if (record == null) {
            return Response.NO_KEY;
        }

        JsonElement elem = record.getValue();
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

    @Override
    public synchronized Response set(Task task) {
        List<String> keys = task.getKeys();
        List<Record> list = operator.read();
        Record record = list.stream()
                .filter(r -> r.getKey().equals(keys.get(0)))
                .findFirst()
                .orElse(null);

        if (record == null) {
            list.add(new Record(keys.get(0), task.getValue()));
        } else if (keys.size() >= 2) {
            JsonElement elem = record.getValue();
            for (int i = 1; i < keys.size() - 1; i++) {
                if (elem.isJsonObject()) {
                    elem = elem.getAsJsonObject().get(keys.get(i));
                }
            }
            if (elem.isJsonObject()) {
                if (task.getValue().isJsonPrimitive()) {
                    elem.getAsJsonObject().addProperty(keys.get(keys.size() - 1), task.getValue().getAsString());
                } else if (task.getValue().isJsonObject()) {
                    System.out.println("?????????????????????????");
                }
            }
        } else {
            int index = list.indexOf(record);
            list.set(index, new Record(keys.get(0), task.getValue()));
        }
        operator.save(list);
        return Response.OK;
    }

    @Override
    public synchronized Response delete(Task task) {
        List<String> keys = task.getKeys();
        List<Record> list = operator.read();
        Record record = list.stream()
                .filter(r -> r.getKey().equals(keys.get(0)))
                .findFirst()
                .orElse(null);

        if (record == null) {
            return Response.NO_KEY;
        }

        if (keys.size() >= 2) {
            JsonElement elem = record.getValue();
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
