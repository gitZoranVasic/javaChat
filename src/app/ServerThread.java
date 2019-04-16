package app;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ServerThread implements Runnable, Observer {


    private Socket socket;
    private int redniBr;
    PrintWriter out;

    public ServerThread(Socket socket, int redniBrojKlijenta) {
        this.socket = socket;
        this.redniBr = redniBrojKlijenta;
    }

    @Override
    public void run() {

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            System.out.println("Konektovao se klijent sa adresom : " + socket.getInetAddress().getHostAddress() + " i rednim brojem " + redniBr);


            PrinterThread printer = new PrinterThread(input);
            printer.addObserver(this);
            Thread thread = new Thread(printer);
            thread.start();

            Scanner sc = new Scanner(System.in);
            String msg;

            while(true) {
                msg = sc.nextLine();
                out.println(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(Observable observable, Object o) {
        try {
            this.out.println("EXIT");
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
