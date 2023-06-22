package Client.Controllers.PlaylistPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PlaylistController {
    @FXML
    private ScrollPane bottomScrollPane;

    @FXML
    private Button likeButton;

    @FXML
    private ImageView playlistPicture;

    @FXML
    private Label playlistTitle;

    @FXML
    private VBox tracksVbox;

    @FXML
    void like_unlike(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public ScrollPane getBottomScrollPane() {
        return bottomScrollPane;
    }

    public void setBottomScrollPane(ScrollPane bottomScrollPane) {
        this.bottomScrollPane = bottomScrollPane;
    }

    public Button getLikeButton() {
        return likeButton;
    }

    public void setLikeButton(Button likeButton) {
        this.likeButton = likeButton;
    }

    public ImageView getPlaylistPicture() {
        return playlistPicture;
    }

    public void setPlaylistPicture(ImageView playlistPicture) {
        this.playlistPicture = playlistPicture;
    }

    public Label getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistTitle(Label playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public VBox getTracksVbox() {
        return tracksVbox;
    }

    public void setTracksVbox(VBox tracksVbox) {
        this.tracksVbox = tracksVbox;
    }
}
