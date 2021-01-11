package org.ikinsure.jsondatabase.server.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.ikinsure.jsondatabase.server.model.Record;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileOperator implements FileOperations<Record> {

    private final String filename;
    private final Gson gson;
    private final ReadWriteLock lock;

    public FileOperator(String filename, Gson gson) {
        this.filename = filename;
        this.gson = gson;
        this.lock = new ReentrantReadWriteLock();

        try {
            File file = new File(filename);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Record> read() {
        List<Record> list = null;
        Type type = new TypeToken<ArrayList<Record>>(){}.getType();
        lock.readLock().lock();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            list = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lock.readLock().unlock();
        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public void save(List<Record> list) {
        lock.writeLock().lock();
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename));) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lock.writeLock().unlock();
    }
}
