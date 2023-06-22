package Client.Controllers.Boxes.ArtistBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class ArtistBoxController {
    @FXML
    private HBox albumsHbox;

    @FXML
    private Text artistAboutText;

    @FXML
    private Label artistName;

    @FXML
    private ImageView artistPicture;

    @FXML
    private Button followButton;

    @FXML
    void toggleFollow(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public HBox getAlbumsHbox() {
        return albumsHbox;
    }

    public void setAlbumsHbox(HBox albumsHbox) {
        this.albumsHbox = albumsHbox;
    }

    public Text getArtistAboutText() {
        return artistAboutText;
    }

    public void setArtistAboutText(Text artistAboutText) {
        this.artistAboutText = artistAboutText;
    }

    public Label getArtistName() {
        return artistName;
    }

    public void setArtistName(Label artistName) {
        this.artistName = artistName;
    }

    public ImageView getArtistPicture() {
        return artistPicture;
    }

    public void setArtistPicture(ImageView artistPicture) {
        this.artistPicture = artistPicture;
    }

    public Button getFollowButton() {
        return followButton;
    }

    public void setFollowButton(Button followButton) {
        this.followButton = followButton;
    }
}
