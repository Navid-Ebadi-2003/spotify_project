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

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Controllers/WelcomePage/welcome-page.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
