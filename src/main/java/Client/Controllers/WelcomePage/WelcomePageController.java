package Client.Controllers.WelcomePage;

import Client.ClientHandler;
import Client.Controllers.LoginPage.LoginPageController;
import Client.Controllers.SignupPage.SignupPageController;
import Shared.Request;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomePageController implements Initializable {

    private Socket clientSocket;
    private Request requestObject;
    @FXML
    private Button loginPageButton;

    @FXML
    private Button signupPageButton;

    public void goLoginPage(ActionEvent event) {
        Stage currentStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loginPageLoader = new FXMLLoader(WelcomePageController.class.getResource("../LoginPage/login-page.fxml"));
        try {
            Scene loginScene = new Scene(loginPageLoader.load());
            LoginPageController loginPageController = loginPageLoader.getController();
            loginPageController.setter(clientSocket);
            currentStage.setScene(loginScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goSignupPage(ActionEvent event) {
        Stage currentStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader signupPageLoader = new FXMLLoader(WelcomePageController.class.getResource("../SignupPage/signup-page.fxml"));
        try {
            Scene loginScene = new Scene(signupPageLoader.load());
            currentStage.setScene(loginScene);
            SignupPageController signupPageController = signupPageLoader.getController();
            System.out.println(this.clientSocket.getLocalAddress());
            signupPageController.clientSocket = this.clientSocket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Socket clientSocket = null;
        try {
            //Connecting to server
            clientSocket = new Socket("localhost", 8888);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Assigning handler
        this.clientSocket = clientSocket;
        this.requestObject = new Request(this.clientSocket);
    }
}
