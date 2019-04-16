package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;

public class PrinterThread extends Observable implements Runnable {

    BufferedReader in;
    Socket caller;

    public PrinterThread(BufferedReader in) {
        this.in = in;
    }


    @Override
    public void run() {
        String msg;

        while(true) {
            try {
                msg = in.readLine();
                System.out.flush();
                if(msg == null || msg.equals("EXIT")) {
                    this.setChanged();
                    this.notifyObservers();
                }


                System.out.println(msg);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
