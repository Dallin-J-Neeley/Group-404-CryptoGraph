import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>(); //array of class ClientHandler
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket) { //gets socket from Server class
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine(); // gets username to distinguish between clients and prevent messages being sent to the sender
            clientHandlers.add(this); // add this client class to the array list

            broadcastMessage("Server: " + clientUsername + " has entered the chat"); // send messages to other clients
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter); //close everything in event of an error
        }
    }

    @Override
    public void run() { //thread
        String msgFromClient;

        while (socket.isConnected()) { // while socket is alive
            try {
                msgFromClient = bufferedReader.readLine(); // get message from user
                broadcastMessage(msgFromClient); // send message to everyone else

            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);  // close everything in event of an error
                break; // break while loop
            }
        }
    }

    public void broadcastMessage(String msgToSend) {
        for (ClientHandler clientHandler : clientHandlers) { // cycle through clients
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) { // if clientname does not equal sender-name then display message to client
                    clientHandler.bufferedWriter.write(msgToSend); // write to buffer
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush(); // send buffer to client even though it is not full
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter); // close everything in event of an error
            }
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this); // remove client from array
        broadcastMessage("SERVER: " + clientUsername + " has left the chat!"); // tell everyone that a client has exited the group

    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
        try {
            if (bufferedReader != null) { // close buffer
                bufferedReader.close();
            }
            if (bufferedWriter != null) { // close buffer
                bufferedWriter.close();
            }
            if (socket != null) { // close socket which closes both buffers if they are not already closed so the above lines are probably useless. 
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace(); // error handling
        }
    }
    
}
