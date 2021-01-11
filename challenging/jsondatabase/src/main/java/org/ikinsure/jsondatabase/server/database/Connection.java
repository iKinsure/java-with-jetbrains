package org.ikinsure.jsondatabase.server.database;

import org.ikinsure.jsondatabase.server.model.Response;
import org.ikinsure.jsondatabase.server.model.Task;

/**
 * Basic database operations
 */
public interface Connection {
    Response get(Task task);
    Response set(Task task);
    Response delete(Task task);
}
