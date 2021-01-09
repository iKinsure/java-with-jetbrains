package org.ikinsure.jsondatabase.server;

public class OperationFactory {

    private String type;
    private String key;
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
            operation = Response::empty;
        }
        return operation;
    }
}
