package Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Service implements Runnable {
    private Socket serverSocket;
    private Scanner in;

    //Constructor
    public Service(Socket serverSocket) {
        this.serverSocket = serverSocket;
        try {
            this.in = new Scanner(serverSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            try {
                while (true) {
                    //Give service to client
                    doService();
                }
            } finally {
                //Close the Scanner after finishing work
                in.close();
            }
        } finally {
            //Write in LOG FILE that client has been disconnected
            //TODO
            System.out.println("CLIENT WITH IP: " + serverSocket.getRemoteSocketAddress() + " HAS BEEN DISCONNECTED");
        }
    }

    public void doService() {
        while (true){
            //Listening until client sends request
            String request = in.nextLine();
            //Converting request to json
            JsonObject jsonRequest = new Gson().fromJson(request, JsonObject.class);

            if (jsonRequest.get("requestType").getAsString().equals("EXIT")){
                //Terminate the program
                return;
            }
            else {
                //Execute desired request
                executeRequest(jsonRequest);
            }
        }
    }

    public void executeRequest(JsonObject jsonRequest){
        //Switch on requestTypes
        //TODO
    }
}
