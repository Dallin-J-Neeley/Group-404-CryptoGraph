import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket; // declares server - honestly this complexity could be avoided.
    }

    public void startServer() {
        try {
            while(!serverSocket.isClosed()) { //while server is alive
                Socket socket =  serverSocket.accept(); // accept any incoming clients on socket declared earlier in main and in pulic Server method
                System.out.println("Anew client has connected!"); // indicate new clients
                ClientHandler clientHandler = new ClientHandler(socket); // initialize clientHandler class with accepted client in the right server

                Thread thread = new Thread(clientHandler); // starts the clientHandler class to run in the background
                thread.start(); // start
                // repeat to accept new clients over and over until server closes

            }
        }catch (IOException e) { //error handling
                
        }
    }

    public void closeServerSocket() { //closes server socket and catches errors
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4321); // sets up socket
        Server server = new Server(serverSocket); //passes in socket to class
        server.startServer(); //starts thread to recieve clients
    }
}
