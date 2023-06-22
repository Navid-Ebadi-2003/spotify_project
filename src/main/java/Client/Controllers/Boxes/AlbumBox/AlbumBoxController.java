package Client.Controllers.Boxes.AlbumBox;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class AlbumBoxController {
    @FXML
    private ImageView albumPicture;

    @FXML
    private Label albumTitle;

    @FXML
    private HBox artistHBox;

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

    public HBox getArtistHBox() {
        return artistHBox;
    }

    public void setArtistHBox(HBox artistHBox) {
        this.artistHBox = artistHBox;
    }
}
