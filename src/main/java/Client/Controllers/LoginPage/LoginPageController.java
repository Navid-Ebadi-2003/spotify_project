package Client.Controllers.LoginPage;

import Client.Controllers.MainPage.MainPageController;
import Client.Controllers.SignupPage.SignupPageController;
import Client.Controllers.WelcomePage.WelcomePageController;
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

public class LoginPageController {

    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signupPageButton;

    @FXML
    private TextField usernameField;

    @FXML
    void login(ActionEvent event) {
        // Taking input
        String username = usernameField.getText();
        String password = passwordField.getText();
        // Sending request to server
        requestObject.loginReq(username, password);
        // Receiving response from server
        String response = in.nextLine();
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        JsonObject responseBody = jsonResponse.getAsJsonObject("responseBody");
        boolean result = responseBody.get("result").getAsBoolean();
        if (result){
            Stage currentStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            FXMLLoader mainPageLoader = new FXMLLoader(LoginPageController.class.getResource("../MainPage/main-page.fxml"));
            try {
                Scene mainPageScene = new Scene(mainPageLoader.load());
                MainPageController loginPageController = mainPageLoader.getController();
//                loginPageController.setter(clientSocket);
                currentStage.setScene(mainPageScene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            //TODO
        }
    }

    @FXML
    void goSignupPage(ActionEvent event) {
        Stage currentStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader signupPageLoader = new FXMLLoader(LoginPageController.class.getResource("../SignupPage/signup-page.fxml"));
        try {
            Scene loginScene = new Scene(signupPageLoader.load());
            SignupPageController signupPageController = signupPageLoader.getController();
            signupPageController.setter(clientSocket);
            currentStage.setScene(loginScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
