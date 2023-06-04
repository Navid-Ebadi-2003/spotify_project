package Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class WelcomeController {
    @FXML
    private Button loginButton, signupButton;
    //TODO
    @FXML
    public void loginPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-page.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    public void signupPage(ActionEvent event){
        //TODO
    }
}
