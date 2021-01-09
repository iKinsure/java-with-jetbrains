package org.ikinsure.jsondatabase.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {

    private static final String[] db = new String[1000];
    private static final int PORT = 34522;

    public static void main(String[] args) throws IOException {
        Arrays.fill(db, "");
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server started!");
        while (true) {
            Socket socket = server.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String command = in.readUTF();
            System.out.println("Received: " + command);
            if ("exit".equals(command)) {
                out.writeUTF("OK");
                server.close();
                break;
            }
            String response = execute(command);
            System.out.println("Sent: " + response);
            out.writeUTF(response);
        }
    }

    private static String execute(String command) {
        String[] params = command.split("\\s+", 3);
        if ("get".equals(params[0])) {
            int index = getIndex(params[1]);
            if (checkIndex(index) && !db[index].isEmpty()) {
                return db[index];
            }
        } else if ("set".equals(params[0])) {
            int index = getIndex(params[1]);
            if (checkIndex(index)) {
                db[index] = params[2];
                return "OK";
            }
        } else if ("delete".equals(params[0])) {
            int index = getIndex(params[1]);
            if (checkIndex(index)) {
                db[index] = "";
                return "OK";
            }
        }
        return "ERROR";
    }

    private static boolean checkIndex(int index) {
        return index < db.length && index >= 0;
    }

    private static int getIndex(String number) {
        return Integer.parseInt(number) - 1;
    }
}
