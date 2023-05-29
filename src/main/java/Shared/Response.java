package Shared;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Response {
    private Socket serverSocket;
    private PrintWriter out;

    //Constructor

    public Response(Socket serverSocket) {
        this.serverSocket = serverSocket;
        try {
            this.out = new PrintWriter(serverSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Methods to get called by service provider to send different kinds of responses
    //TODO
}
