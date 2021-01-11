package org.ikinsure.jsondatabase.server;

import org.ikinsure.jsondatabase.server.model.Response;

@FunctionalInterface
public interface Operation {
    Response execute();
}
