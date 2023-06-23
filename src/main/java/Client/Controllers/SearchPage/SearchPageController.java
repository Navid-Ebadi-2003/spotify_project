package Client.Controllers.SearchPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SearchPageController {
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
}
