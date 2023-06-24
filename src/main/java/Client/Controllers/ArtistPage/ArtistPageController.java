package Client.Controllers.ArtistPage;

import Client.Controllers.MainPage.MainPageController;
import Shared.Request;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ArtistPageController {
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;

    @FXML
    private HBox albumsHBox;

    @FXML
    private Label aboutArtist;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Button followButton;

    @FXML
    private Label artistName;
    private MainPageController mainPageController;

    /*
        setter and getters
     */

    public HBox getAlbumsHBox() {
        return albumsHBox;
    }

    public void setAlbumsHBox(HBox albumsHBox) {
        this.albumsHBox = albumsHBox;
    }

    public Label getAboutArtist() {
        return aboutArtist;
    }

    public void setAboutArtist(Label aboutArtist) {
        this.aboutArtist = aboutArtist;
    }

    public ImageView getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ImageView profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Button getFollowButton() {
        return followButton;
    }

    public void setFollowButton(Button followButton) {
        this.followButton = followButton;
    }

    public Label getArtistName() {
        return artistName;
    }

    public void setArtistName(Label artistName) {
        this.artistName = artistName;
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
