package Shared;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Request {
    private Socket clientSocket;
    private PrintWriter out;

    //Constructor

    public Request(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            this.out = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Methods to get called by client to send different kinds of requests
    //TODO
}
