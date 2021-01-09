package org.ikinsure.jsondatabase.client;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 34522;

    public static void main(String[] args) throws IOException {
        Task task = new Task();
        JCommander.newBuilder()
                .addObject(task)
                .build()
                .parse(args);
        Gson gson = new GsonBuilder().create();

        Socket socket = new Socket(ADDRESS, PORT);
        System.out.println("Client started!");
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out  = new DataOutputStream(socket.getOutputStream());

        String request = gson.toJson(task);
        System.out.println("Sent: " + request);
        out.writeUTF(request);
        String response = in.readUTF();
        System.out.println("Received: " + response);
    }
}
