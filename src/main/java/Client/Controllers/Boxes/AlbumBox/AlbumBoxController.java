package Client.Controllers.Boxes.AlbumBox;

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

public class AlbumBoxController {
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    private MainPageController mainPageController;

    @FXML
    private ImageView albumPicture;

    @FXML
    private Hyperlink albumTitleHyperLink;

    @FXML
    private HBox artistsHbox;
    // This is for sending request if a user clicks on hyperlink
    private UUID albumId;
    // This stores artistName, artistId
    private HashMap<String, UUID> artists;

    @FXML
    public void goToAlbumPage(ActionEvent event) {

    }

    /*
        setter and getters
     */

    public ImageView getAlbumPicture() {
        return albumPicture;
    }

    public void setAlbumPicture(ImageView albumPicture) {
        this.albumPicture = albumPicture;
    }

    public Hyperlink getAlbumTitleHyperLink() {
        return albumTitleHyperLink;
    }

    public void setAlbumTitleHyperLink(String albumTitle) {
        this.albumTitleHyperLink.setText(albumTitle);
    }

    public HBox getArtistsHbox() {
        return artistsHbox;
    }

    public void setArtistsHbox(HBox artistsHbox) {
        this.artistsHbox = artistsHbox;
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
}
