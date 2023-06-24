package Client.Controllers.Boxes.PlaylistSecondBox;

import Client.Controllers.InjectableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;


import java.util.UUID;

public class PlaylistSecondBoxController implements InjectableController {
    @FXML
    private Hyperlink creatorNameHyperLink;

    @FXML
    private ImageView playlistPicture;
    @FXML
    private HBox playlistHbox;

    @FXML
    private Hyperlink playlistTitleHyperLink;

    // These store id of creator and playlist
    private UUID creatorId;
    private UUID playlistId;

    @FXML
    void goCreatorPage(ActionEvent event) {

    }

    @FXML
    void goPlaylistPage(ActionEvent event) {

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

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }

    public UUID getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(UUID playlistId) {
        this.playlistId = playlistId;
    }
    @Override
    public void setControllerProfilePic(Image profilePic) {
        this.playlistPicture.setImage(profilePic);
    }

    @Override
    public Node getMainScene() {
        return this.playlistHbox;
    }


    public void setPlaylistHbox(HBox playlistHbox) {
        this.playlistHbox = playlistHbox;
    }
}
