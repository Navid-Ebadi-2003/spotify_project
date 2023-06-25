package Client.Controllers.Boxes.UserBox;

import Client.Controllers.InjectableController;
import Client.Controllers.MainPage.MainPageController;
import Shared.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class UserBoxController implements InjectableController {

    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    private MainPageController mainPageController;

    @FXML
    private ImageView userPicture;

    @FXML
    private Hyperlink usernameHyperLink;
    // This stores id of the user
    private UUID userId;

    @FXML
    private VBox userBox;

    @FXML
    void goToUserPage(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public ImageView getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(ImageView userPicture) {
        this.userPicture = userPicture;
    }

    public Hyperlink getUsernameHyperLink() {
        return usernameHyperLink;
    }

    public void setUsernameHyperLink(Hyperlink usernameHyperLink) {
        this.usernameHyperLink = usernameHyperLink;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public void setControllerProfilePic(Image profilePic) {
        this.userPicture.setImage(profilePic);
    }

    @Override
    public Node getMainScene() {
        return this.userBox;
    }

    public void setter(Socket clientSocket, MainPageController mainPageController) {
        this.mainPageController = mainPageController;
        this.clientSocket = clientSocket;
        this.requestObject = new Request(this.clientSocket);
        try {
            this.in = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
