package Server;

import Server.Database.Query;
import Shared.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class Service implements Runnable {
    private Socket serverSocket;
    private Scanner in;
    private Response responseObject;

    //Constructor
    public Service(Socket serverSocket) {
        this.serverSocket = serverSocket;
        responseObject = new Response(serverSocket);
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
        //Listening until client sends request
        String request = in.nextLine();
        //Converting request to json
        JsonObject jsonRequest = new Gson().fromJson(request, JsonObject.class);

        if (jsonRequest.get("requestType").getAsString().equals("EXIT")) {
            //Terminate the program
            return;
        } else {
            //Execute desired request
            executeRequest(jsonRequest);
        }
    }

    public void executeRequest(JsonObject jsonRequest) {
        //Switch on requestTypes
        String requestType = jsonRequest.get("requestType").getAsString();

        switch (requestType) {
            case "signup request" -> {
                JsonObject requestBody = jsonRequest.getAsJsonObject("requestBody");
                UUID userId = UUID.randomUUID();
                String email = requestBody.get("email").getAsString();
                String username = requestBody.get("username").getAsString();
                String password = requestBody.get("password").getAsString();
                if (!Query.doesEmailExist(email) && !Query.doesUsernameExist(username)){
                    Query.signUpUser(userId, username, email, password);
                    // Consider removing userId from that response because we have to login again
                    responseObject.signupRes(true, userId);
                } else {
                    responseObject.signupRes(false, userId);
                }
            }
            case "login request" -> {
                //TODO
            }
            case "go home page request" -> {
                //TODO
            }
            case "search request" -> {
                //TODO
            }
            case "watch user page request" -> {
                //TODO
            }
            case "watch artist page request" -> {
                //TODO
            }
            case "watch music page request" -> {
                //TODO
            }
            case "watch album page request" -> {
                //TODO
            }
            case "watch playlist page request" -> {
                //TODO
            }
            case "follow request" -> {
                //TODO
            }
            case "like music request" -> {
                //TODO
            }
            case "download track request" -> {
                //TODO
            }
            case "add to playlist request" -> {
                //TODO
            }
            case "edit personal info request" -> {
                //TODO
            }
            case "create playlist request" -> {
                //TODO
            }
            case "download playlist request" -> {
                //TODO
            }
            case "watch liked tracks request" -> {
                //TODO
            }
        }
    }
}
