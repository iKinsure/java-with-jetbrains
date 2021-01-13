package org.ikinsure.blockchain;

import java.io.*;

public class SerialOperator<T extends Serializable> {

    private final String file;

    public SerialOperator(String file) {
        this.file = file;
    }

    @SuppressWarnings("unchecked")
    public T deserialize() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (T) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    public boolean serialize(T t) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(t);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
