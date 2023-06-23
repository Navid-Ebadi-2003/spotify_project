package Client.Controllers.Boxes.PlaylistMainBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.UUID;

public class PlaylistMainBoxController {
    @FXML
    private Hyperlink creatorNameHyperLink;

    @FXML
    private ImageView playlistPicture;

    @FXML
    private Hyperlink playlistTitleHyperLink;
    // This stores id of the playlist
    private UUID playlistId;
    // This stores id of the creator
    private UUID creatorId;

    @FXML
    void goToCreatorPage(ActionEvent event) {

    }

    @FXML
    void goToPlaylistPage(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public Hyperlink getCreatorNameHyperLink() {
        return creatorNameHyperLink;
    }

    public void setCreatorNameHyperLink(Hyperlink creatorNameHyperLink) {
        this.creatorNameHyperLink = creatorNameHyperLink;
    }

    public ImageView getPlaylistPicture() {
        return playlistPicture;
    }

    public void setPlaylistPicture(ImageView playlistPicture) {
        this.playlistPicture = playlistPicture;
    }

    public Hyperlink getPlaylistTitleHyperLink() {
        return playlistTitleHyperLink;
    }

    public void setPlaylistTitleHyperLink(Hyperlink playlistTitleHyperLink) {
        this.playlistTitleHyperLink = playlistTitleHyperLink;
    }

    public UUID getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(UUID playlistId) {
        this.playlistId = playlistId;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }
}
