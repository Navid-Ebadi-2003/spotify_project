package Client.Controllers.SignupPage;

import Client.Controllers.WelcomePage.WelcomePageController;
import Shared.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SignupPageController implements Initializable {

    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    @FXML
    private TextField emailField;

    @FXML
    private Button loginPageButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private TextField usernameField;

    @FXML
    void goLoginPage(ActionEvent event) {
        Stage currentStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loginPageLoader = new FXMLLoader(SignupPageController.class.getResource("../LoginPage/login-page.fxml"));
        try {
            Scene loginScene = new Scene(loginPageLoader.load());
            currentStage.setScene(loginScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void submit(ActionEvent event) {
        // Taking input
        String email = emailField.getText();
        String password = passwordField.getText();
        String username = usernameField.getText();
        // Sending request to server
        requestObject.signupReq(username, email, password);
        // Receiving response from server
        String response = in.nextLine();
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        boolean result = jsonResponse.get("result").getAsBoolean();
        if (result){
            ActionEvent e = new ActionEvent();
            goLoginPage(e);
        } else {
            //TODO
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Here we assume that clientSocket of this object is gonna get instantiated outside
        requestObject = new Request(clientSocket);
        try {
            in = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
