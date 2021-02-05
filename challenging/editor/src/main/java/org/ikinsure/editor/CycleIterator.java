package org.ikinsure.editor;

import java.util.List;

public class CycleIterator<T> {

    private final List<T> list;
    private int pointer;

    public CycleIterator(List<T> list) {
        this.list = list;
        this.pointer = -1;
    }

    public T next() {
        if (list.isEmpty()) {
            return null;
        }
        pointer = pointer >= list.size() - 1 ? 0 : pointer + 1;
        return list.get(pointer);
    }

    public T prev() {
        if (list.isEmpty()) {
            return null;
        }
        pointer = pointer <= 0 ? list.size() - 1 : pointer - 1;
        return list.get(pointer);
    }

    public void reset() {
        this.pointer = -1;
    }
}
