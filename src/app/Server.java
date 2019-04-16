package app;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() throws Exception {

        ServerSocket server_socket = new ServerSocket(8918);
        int br = 1;
        System.out.println("Server pokrenut");

        do {
            Socket socket = server_socket.accept();
            ServerThread server_thread = new ServerThread(socket, br++);
            Thread thread = new Thread(server_thread);
            thread.start();
        } while (true);
    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
