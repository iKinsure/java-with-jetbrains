package org.ikinsure.jsondatabase.server;

import com.google.gson.annotations.Expose;

/**
 * Operation factory class
 */
public class OperationFactory {

    @Expose
    private String type;
    @Expose
    private String key;
    @Expose
    private String value;

    public Operation getOperation(Database database, Server server) {
        Operation operation;
        if ("set".equals(type)) {
            operation = () -> database.set(key, value);
        } else if ("get".equals(type)) {
            operation = () -> database.get(key);
        } else if ("delete".equals(type)) {
            operation = () -> database.remove(key);
        } else if ("exit".equals(type)) {
            operation = server::close;
        } else {
            operation = () -> Response.EMPTY;
        }
        return operation;
    }
}
