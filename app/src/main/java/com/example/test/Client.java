package com.example.test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    Socket s = null; // stores my socket for the duration of the task :)

    public Socket sockets() throws IOException {
        new Thread(new Runnable() { //new thread
            @Override
            public void run() {
                try {
                    s =  new Socket("10.0.2.2", 4321); //starts the socket and STORES IT!! YAY
                } catch (IOException e) {
                    e.printStackTrace(); //error handling
                }
            }}).start(); //start thread
        return s; //return for no reason because it returns null.
    }

    public void send(String input) {
        new Thread(new Runnable() {// new thread
            @Override
            public void run() { //send messages to server to go to all clients
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(s.getOutputStream());
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                    bufferedWriter.write(input); //Send input
                    bufferedWriter.newLine(); //adds a newline
                    bufferedWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace(); //error handling
                }
            }}).start(); //start thread
    }
}
