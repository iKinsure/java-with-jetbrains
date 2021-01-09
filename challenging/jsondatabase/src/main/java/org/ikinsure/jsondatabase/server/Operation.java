package org.ikinsure.jsondatabase.server;

@FunctionalInterface
public interface Operation {
    Response execute();
}
