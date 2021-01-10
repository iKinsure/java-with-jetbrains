package org.ikinsure.jsondatabase.server;

public class OperationFactory {

    private final Task task;
    private final Server server;
    private final DatabaseConnection connection;

    public OperationFactory(Task task, Server server, DatabaseConnection connection) {
        this.task = task;
        this.server = server;
        this.connection = connection;
    }

    public Operation createOperation() {
        switch (task.getType()) {
            case "get":
                return () -> connection.get(task.getKey());
            case "set":
                return () -> connection.set(task.getKey(), task.getValue());
            case "delete":
                return () -> connection.remove(task.getKey());
            case "exit":
                return server::close;
            default:
                return () -> Response.EMPTY;
        }
    }
}