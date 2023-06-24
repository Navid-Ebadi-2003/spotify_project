package Client.Controllers.Boxes.MusicThirdBox;

import Client.Controllers.InjectableController;
import Client.Controllers.MainPage.MainPageController;
import Shared.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class MusicThirdBoxController implements InjectableController {
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    private MainPageController mainPageController;

    @FXML
    private HBox artistsHbox;

    @FXML
    private Button downPlayTrack;

    @FXML
    private ImageView downloadPlayIcon;

    @FXML
    private ImageView trackPicture;

    @FXML
    private Hyperlink trackTitleHyperLink;
    @FXML
    private HBox musicMainHbox;
    // This stores id of the album
    private UUID albumId;
    // This stores artistName, artistId
    private HashMap<String, UUID> artists;

    @FXML
    void downPlay(ActionEvent event) {

    }

    @FXML
    void goToAlbumPage(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public HBox getArtistsHbox() {
        return artistsHbox;
    }

    public void setArtistsHbox(HBox artistsHbox) {
        this.artistsHbox = artistsHbox;
    }

    public Button getDownPlayTrack() {
        return downPlayTrack;
    }

    public void setDownPlayTrack(Button downPlayTrack) {
        this.downPlayTrack = downPlayTrack;
    }

    public ImageView getDownloadPlayIcon() {
        return downloadPlayIcon;
    }

    public void setDownloadPlayIcon(ImageView downloadPlayIcon) {
        this.downloadPlayIcon = downloadPlayIcon;
    }

    public ImageView getTrackPicture() {
        return trackPicture;
    }

    public void setTrackPicture(ImageView trackPicture) {
        this.trackPicture = trackPicture;
    }

    public Hyperlink getTrackTitleHyperLink() {
        return trackTitleHyperLink;
    }

    public void setTrackTitleHyperLink(Hyperlink trackTitleHyperLink) {
        this.trackTitleHyperLink = trackTitleHyperLink;
    }

    public UUID getAlbumId() {
        return albumId;
    }

    public void setAlbumId(UUID albumId) {
        this.albumId = albumId;
    }

    public HashMap<String, UUID> getArtists() {
        return artists;
    }

    public void setArtists(HashMap<String, UUID> artists) {
        this.artists = artists;
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

    @Override
    public void setControllerProfilePic(Image profilePic) {
        this.trackPicture.setImage(profilePic);
    }

    @Override
    public Node getMainScene() {
        return this.musicMainHbox;
    }
}
