package Client.Controllers.Boxes.PlaylistSecondBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;

public class PlaylistSecondBoxController {
    @FXML
    private Hyperlink creatorNameHyperLink;

    @FXML
    private Hyperlink playlistHyperLink;

    @FXML
    private ImageView playlistPicture;

    @FXML
    public void goCreatorPage(ActionEvent event) {

    }

    @FXML
    public void goPlaylistPage(ActionEvent event) {

    }

    public void setCreatorNameHyperLink(Hyperlink creatorNameHyperLink) {
        this.creatorNameHyperLink = creatorNameHyperLink;
    }

    public void setPlaylistHyperLink(Hyperlink playlistHyperLink) {
        this.playlistHyperLink = playlistHyperLink;
    }

    public void setPlaylistPicture(ImageView playlistPicture) {
        this.playlistPicture = playlistPicture;
    }

    public Hyperlink getCreatorNameHyperLink() {
        return creatorNameHyperLink;
    }

    public Hyperlink getPlaylistHyperLink() {
        return playlistHyperLink;
    }

    public ImageView getPlaylistPicture() {
        return playlistPicture;
    }
}
