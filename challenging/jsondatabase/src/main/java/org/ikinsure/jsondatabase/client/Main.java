package org.ikinsure.jsondatabase.client;

import com.beust.jcommander.JCommander;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 34522;

    public static void main(String[] args) throws IOException {

        Task task = new Task();
        JCommander jc = JCommander.newBuilder()
                .addObject(task)
                .build();
        jc.parse(args);

        Socket socket = new Socket(ADDRESS, PORT);
        System.out.println("Client started!");
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out  = new DataOutputStream(socket.getOutputStream());

        String request = task.request();
        System.out.println("Sent: " + request);
        out.writeUTF(request);

        String response = in.readUTF();
        System.out.println("Received: " + response);
    }
}
