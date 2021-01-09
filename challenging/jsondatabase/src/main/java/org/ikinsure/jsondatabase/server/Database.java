package org.ikinsure.jsondatabase.server;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The receiver class
 */
public class Database {

    private final Map<String, String> data;

    public Database() {
        this.data = new LinkedHashMap<>();
    }

    public Response get(String key) {
        String value = data.get(key);
        return value == null ? Response.NO_KEY
                : Response.builder()
                .setResponse("OK")
                .setValue(value)
                .build();
    }

    public Response set(String key, String value) {
        data.put(key, value);
        return Response.OK;
    }

    public Response remove(String key) {
        return data.remove(key) == null ? Response.NO_KEY : Response.OK;
    }
}
