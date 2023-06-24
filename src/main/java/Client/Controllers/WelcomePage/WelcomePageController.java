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
    @FXML
    private Button loginPageButton;

    @FXML
    private Button signupPageButton;
    /*
        setter and getters
     */

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    public Button getLoginPageButton() {
        return loginPageButton;
    }

    public void setLoginPageButton(Button loginPageButton) {
        this.loginPageButton = loginPageButton;
    }

    public Button getSignupPageButton() {
        return signupPageButton;
    }

    public void setSignupPageButton(Button signupPageButton) {
        this.signupPageButton = signupPageButton;
    }

    public void goLoginPage(ActionEvent event) {
        // Taking current stage from the event
        Stage currentStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loginPageLoader = new FXMLLoader(WelcomePageController.class.getResource("../LoginPage/login-page.fxml"));
        try {
            // Loading loginPage into the scene
            Scene loginScene = new Scene(loginPageLoader.load());
            // Taking its controller
            LoginPageController loginPageController = loginPageLoader.getController();
            // Setting clientSocket for its controller
            loginPageController.setter(clientSocket);
            currentStage.setScene(loginScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goSignupPage(ActionEvent event) {
        // Taking current stage from the event
        Stage currentStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader signupPageLoader = new FXMLLoader(WelcomePageController.class.getResource("../SignupPage/signup-page.fxml"));
        try {
            // Loading singupPage into the scene
            Scene loginScene = new Scene(signupPageLoader.load());
            // Taking its controller
            SignupPageController signupPageController = signupPageLoader.getController();
            // Setting clientSocket for its controller
            signupPageController.setter(clientSocket);
            currentStage.setScene(loginScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This will get called whenever a instance of this object is created (or loading .fxml of its class)
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Connecting Client to the server
            this.clientSocket = new Socket("localhost", 8888);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
