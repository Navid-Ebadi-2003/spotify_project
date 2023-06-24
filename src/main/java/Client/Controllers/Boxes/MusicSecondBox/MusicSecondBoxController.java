package Client.Controllers.Boxes.MusicSecondBox;

import Client.Controllers.MainPage.MainPageController;
import Shared.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class MusicSecondBoxController {
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    private MainPageController mainPageController;

    @FXML
    private HBox artistsHbox;

    @FXML
    private ImageView trackPicture;

    @FXML
    private Hyperlink trackTitleHyperLink;
    // This stores artistName, artistId
    private HashMap<String, UUID> artists;
    // This stores id of the album
    private UUID albumId;

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

    public HashMap<String, UUID> getArtists() {
        return artists;
    }

    public void setArtists(HashMap<String, UUID> artists) {
        this.artists = artists;
    }

    public UUID getAlbumId() {
        return albumId;
    }

    public void setAlbumId(UUID albumId) {
        this.albumId = albumId;
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
