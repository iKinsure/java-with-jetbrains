package org.ikinsure.jsondatabase.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Invoker class
 */
public class Server {

    private final int port;
    private final Database database;
    private boolean running;

    public Server(int port, Database database) {
        this.port = port;
        this.database = database;
        this.running = true;
    }

    public void run() {
        try (ServerSocket server = new ServerSocket(port)) {
            while (running) {
                try (Socket socket = server.accept();
                     DataInputStream in = new DataInputStream(socket.getInputStream());
                     DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                    String command = in.readUTF();
                    OperationFactory factory = new Gson().fromJson(command, OperationFactory.class);
                    Response response = factory.getOperation(database, this).execute();
                    String textResponse = new GsonBuilder().create().toJson(response);
                    out.writeUTF(textResponse);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response close() {
        this.running = false;
        return Response.OK;
    }
}
