package Client.Controllers.ArtistPage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ArtistPageController {

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
}
