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
        Response response = new Response();
        String value = data.get(key);
        if (value == null) {
            response.setReason("No such key");
            response.setResponse("ERROR");
        } else {
            response.setValue(value);
            response.setResponse("OK");
        }
        return response;
    }

    public Response set(String key, String value) {
        Response response = new Response();
        data.put(key, value);
        response.setResponse("OK");
        return response;
    }

    public Response remove(String key) {
        Response response = new Response();
        if (data.remove(key) == null) {
            response.setReason("No such key");
            response.setResponse("ERROR");
        } else {
            response.setResponse("OK");
        }
        return response;
    }
}
