package org.ikinsure.jsondatabase.server;

import com.google.gson.Gson;

import java.util.*;

/**
 * The receiver class
 */
public class DatabaseConnection {

    private final FileOperator operator;

    public DatabaseConnection(String file, Gson gson) {
        this.operator = new FileOperator(file, gson);
    }

    public synchronized Response get(String key) {
        List<Task> list = operator.read();
        Task task = list.stream().filter(t -> t.getKey().equals(key)).findFirst().orElse(null);
        return task == null ? Response.NO_KEY
                : Response.builder()
                .setResponse("OK")
                .setValue(task.getValue())
                .build();
    }

    public synchronized Response set(String key, String value) {
        List<Task> list = operator.read();
        Task task = list.stream().filter(t -> t.getKey().equals(key)).findFirst().orElse(null);
        if (task == null) {
            task = new Task();
            task.setKey(key);
            task.setValue(value);
            list.add(task);
        } else {
            task.setValue(value);
        }
        operator.save(list);
        return Response.OK;
    }

    public synchronized Response remove(String key) {
        List<Task> list = operator.read();
        Task task = list.stream().filter(t -> t.getKey().equals(key)).findFirst().orElse(null);
        if (task == null) {
            return Response.NO_KEY;
        }
        list.remove(task);
        operator.save(list);
        return Response.OK;
    }
}
