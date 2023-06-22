package Client.Controllers.UserPage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class UserPageController {

    @FXML
    private HBox albumsHBox;

    @FXML
    private HBox artistsHBox;

    @FXML
    private HBox musicsHBox;

    @FXML
    private HBox playlistsHBox;

    @FXML
    private HBox usersHBox;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Button followButton;

    @FXML
    private Label username;
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

    public HBox getUsersHBox() {
        return usersHBox;
    }

    public void setUsersHBox(HBox usersHBox) {
        this.usersHBox = usersHBox;
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

    public Label getUsername() {
        return username;
    }

    public void setUsername(Label username) {
        this.username = username;
    }
}
