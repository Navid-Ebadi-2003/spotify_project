package Client;

import Shared.Request;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URL;

public class ClientHandler extends Application {
    private Socket clientSocket;
    private Request requestObject;

    //Constructor

//    public ClientHandler(Socket clientSocket) {
//        this.clientSocket = clientSocket;
//        this.requestObject = new Request(clientSocket);
//    }

    public static void main(String[] args) {
        launch();
//        Socket clientSocket = null;
//        try {
//            //Connecting to server
//            clientSocket = new Socket("localhost", 8888);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        //Assigning handler
//        ClientHandler clientHandler = new ClientHandler(clientSocket);
//
//        //Running app for client
//        clientHandler.runApp();
    }

    public void runApp(){
        System.out.println("connected!");
        //1. Showing menu to user
        //2. Sending request using request object to the server
        //TODO
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Controllers/WelcomePage/welcome-page.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
