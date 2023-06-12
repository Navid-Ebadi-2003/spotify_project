package Client.Controllers.LoginPage;

import Shared.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        //TODO
    }

    @FXML
    void goSignupPage(ActionEvent event) {
        //TODO
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
