package org.ikinsure.jsondatabase.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 34522;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(ADDRESS, PORT);
        System.out.println("Client started!");
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out  = new DataOutputStream(socket.getOutputStream());
        String msg = "Give me a record # 12";
        System.out.println("Sent: " + msg);
        out.writeUTF(msg);
        msg = in.readUTF();
        System.out.println("Received: " + msg);
    }
}
