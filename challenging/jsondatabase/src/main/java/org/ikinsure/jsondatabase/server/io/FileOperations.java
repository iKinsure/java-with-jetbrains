package org.ikinsure.jsondatabase.server.io;

import java.util.List;

/**
 * Basic file operations
 */
public interface FileOperations<T> {
    List<T> read();
    void save(List<T> list);
}
