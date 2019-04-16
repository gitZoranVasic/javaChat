package app;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class Client implements Observer {

    Socket socket;
    PrintWriter out;

    public Client() throws Exception {

        socket = new Socket("localhost", 8918);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        Scanner sc = new Scanner(System.in);

        PrinterThread printer = new PrinterThread(in);
        printer.addObserver(this);
        Thread thread = new Thread(printer);
        thread.start();


        while(true) {
            out.println(sc.nextLine());
        }


    }

    public static void main(String[] args) {

        try {
            new Client();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        try {
            out.println("EXIT");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
