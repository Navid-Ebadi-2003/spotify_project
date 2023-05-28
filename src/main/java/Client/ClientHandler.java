package Client;

import Shared.Request;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket clientSocket;
    private Request requestObject;

    //Constructor

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.requestObject = new Request(clientSocket);
    }

    public static void main(String[] args) {
        Socket clientSocket = null;
        try {
            //Connecting to server
            clientSocket = new Socket("localhost", 8888);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Assigning handler
        ClientHandler clientHandler = new ClientHandler(clientSocket);

        //Running app for client
        clientHandler.runApp();
    }

    public void runApp(){
        //1. Showing menu to user
        //2. Sending request using request object to the server
        //TODO
    }
}
