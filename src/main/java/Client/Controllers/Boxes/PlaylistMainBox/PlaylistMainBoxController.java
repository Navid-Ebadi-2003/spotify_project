package Client.Controllers.Boxes.PlaylistMainBox;

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

public class PlaylistMainBoxController implements InjectableController {
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    private MainPageController mainPageController;

    @FXML
    private Hyperlink creatorNameHyperLink;

    @FXML
    private ImageView playlistPicture;

    @FXML
    private Hyperlink playlistTitleHyperLink;
    // This stores id of the playlist
    private UUID playlistId;
    // This stores id of the creator
    private UUID creatorId;

    @FXML
    private VBox playlistMainVBox;

    @FXML
    void goToCreatorPage(ActionEvent event) {

    }

    @FXML
    void goToPlaylistPage(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public Hyperlink getCreatorNameHyperLink() {
        return creatorNameHyperLink;
    }

    public void setCreatorNameHyperLink(Hyperlink creatorNameHyperLink) {
        this.creatorNameHyperLink = creatorNameHyperLink;
    }

    public ImageView getPlaylistPicture() {
        return playlistPicture;
    }

    public void setPlaylistPicture(ImageView playlistPicture) {
        this.playlistPicture = playlistPicture;
    }

    public Hyperlink getPlaylistTitleHyperLink() {
        return playlistTitleHyperLink;
    }

    public void setPlaylistTitleHyperLink(Hyperlink playlistTitleHyperLink) {
        this.playlistTitleHyperLink = playlistTitleHyperLink;
    }

    public UUID getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(UUID playlistId) {
        this.playlistId = playlistId;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public void setControllerProfilePic(Image profilePic) {
        this.playlistPicture.setImage(profilePic);
    }

    @Override
    public Node getMainScene() {
        return this.playlistMainVBox;
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
