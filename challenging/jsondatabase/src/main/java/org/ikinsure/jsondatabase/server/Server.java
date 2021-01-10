package org.ikinsure.jsondatabase.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Invoker and factory class
 */
public class Server {

    private final int port;
    private final ExecutorService executor;
    private final DatabaseConnection connection;
    private volatile boolean running;

    public Server(int port, Gson gson, String filePath) {
        this.port = port;
        this.executor = Executors.newCachedThreadPool();
        this.connection = new DatabaseConnection(filePath, gson);
        this.running = true;
    }

    public void run() {
        try (ServerSocket server = new ServerSocket(port)) {
            while (running) {
                Socket socket = server.accept();
                executor.submit(() -> {
                    try (DataInputStream in = new DataInputStream(socket.getInputStream());
                         DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                        String command = in.readUTF();
                        System.out.println(Thread.currentThread().getName() + " : received: " + command);
                        Task task = new Gson().fromJson(command, Task.class);

                        OperationFactory factory = new OperationFactory(task, this, connection);
                        Response response = factory.createOperation().execute();

                        String textResponse = new GsonBuilder().create().toJson(response);
                        System.out.println(Thread.currentThread().getName() + " : sent: " + textResponse);
                        out.writeUTF(textResponse);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response close() {
        this.executor.shutdown();
        this.running = false;
        return Response.OK;
    }
}
