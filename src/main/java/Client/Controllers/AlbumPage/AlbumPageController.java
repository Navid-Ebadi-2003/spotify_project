package Client.Controllers.AlbumPage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class AlbumPageController{
    @FXML
    private ImageView albumPicture;

    @FXML
    private Label albumTitle;

    @FXML
    private ScrollPane bottomScrollPane;

    @FXML
    private VBox tracksVbox;

    /*
        setter and getters
     */

    public ImageView getAlbumPicture() {
        return albumPicture;
    }

    public void setAlbumPicture(ImageView albumPicture) {
        this.albumPicture = albumPicture;
    }

    public Label getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(Label albumTitle) {
        this.albumTitle = albumTitle;
    }

    public ScrollPane getBottomScrollPane() {
        return bottomScrollPane;
    }

    public void setBottomScrollPane(ScrollPane bottomScrollPane) {
        this.bottomScrollPane = bottomScrollPane;
    }

    public VBox getTracksVbox() {
        return tracksVbox;
    }

    public void setTracksVbox(VBox tracksVbox) {
        this.tracksVbox = tracksVbox;
    }
}
