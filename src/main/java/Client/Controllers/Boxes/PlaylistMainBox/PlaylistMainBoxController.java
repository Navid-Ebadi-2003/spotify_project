package Client.Controllers.Boxes.PlaylistMainBox;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PlaylistMainBoxController {
    @FXML
    private Label playlistCreatorName;

    @FXML
    private ImageView playlistPicture;

    @FXML
    private Label playlistTitle;
    /*
        setter and getters
     */

    public Label getPlaylistCreatorName() {
        return playlistCreatorName;
    }

    public void setPlaylistCreatorName(Label playlistCreatorName) {
        this.playlistCreatorName = playlistCreatorName;
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
}
