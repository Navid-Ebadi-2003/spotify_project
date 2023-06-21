package Client.Controllers.SignupPage;

import Client.Controllers.LoginPage.LoginPageController;
import Shared.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SignupPageController {

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
            LoginPageController loginPageController = loginPageLoader.getController();
            loginPageController.setter(clientSocket);
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
        JsonObject responseBody = jsonResponse.getAsJsonObject("responseBody");
        boolean result = responseBody.get("result").getAsBoolean();
        if (result){
            Stage currentStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            FXMLLoader loginPageLoader = new FXMLLoader(SignupPageController.class.getResource("../LoginPage/login-page.fxml"));
            try {
                Scene loginScene = new Scene(loginPageLoader.load());
                LoginPageController loginPageController = loginPageLoader.getController();
                loginPageController.setter(clientSocket);
                currentStage.setScene(loginScene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            //TODO
        }
    }


    public void setter(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.requestObject = new Request(this.clientSocket);
        try {
            this.in = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
