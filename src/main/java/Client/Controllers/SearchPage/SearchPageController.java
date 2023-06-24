package Client.Controllers.SearchPage;

import Shared.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SearchPageController {
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    @FXML
    private HBox albumsHBox;

    @FXML
    private HBox artistsHBox;

    @FXML
    private HBox musicsHBox;

    @FXML
    private HBox playlistsHBox;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private HBox usersHBox;

    @FXML
    void searchText(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public HBox getAlbumsHBox() {
        return albumsHBox;
    }

    public void setAlbumsHBox(HBox albumsHBox) {
        this.albumsHBox = albumsHBox;
    }

    public HBox getArtistsHBox() {
        return artistsHBox;
    }

    public void setArtistsHBox(HBox artistsHBox) {
        this.artistsHBox = artistsHBox;
    }

    public HBox getMusicsHBox() {
        return musicsHBox;
    }

    public void setMusicsHBox(HBox musicsHBox) {
        this.musicsHBox = musicsHBox;
    }

    public HBox getPlaylistsHBox() {
        return playlistsHBox;
    }

    public void setPlaylistsHBox(HBox playlistsHBox) {
        this.playlistsHBox = playlistsHBox;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public void setSearchField(TextField searchField) {
        this.searchField = searchField;
    }

    public HBox getUsersHBox() {
        return usersHBox;
    }

    public void setUsersHBox(HBox usersHBox) {
        this.usersHBox = usersHBox;
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
