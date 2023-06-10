package Client.Controllers.LoginPage;

import Shared.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginPageController implements Initializable {

    private Socket clientSocket;
    private Request requestObject;
    private Scanner in;

    @FXML
    private Button loginPageButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signupPageButton;

    @FXML
    private TextField usernameField;

    @FXML
    void goLoginPage(ActionEvent event) {
        //TODO
    }

    @FXML
    void goSignupPage(ActionEvent event) {
        //TODO
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
