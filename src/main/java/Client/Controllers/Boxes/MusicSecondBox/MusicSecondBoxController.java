package Client.Controllers.Boxes.MusicSecondBox;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MusicSecondBoxController {
    @FXML
    private HBox artistsHbox;

    @FXML
    private ImageView trackPicture;

    @FXML
    private Hyperlink trackTitle;
    /*
        setter and getters
     */

    public HBox getArtistsHbox() {
        return artistsHbox;
    }

    public void setArtistsHbox(HBox artistsHbox) {
        this.artistsHbox = artistsHbox;
    }

    public ImageView getTrackPicture() {
        return trackPicture;
    }

    public void setTrackPicture(ImageView trackPicture) {
        this.trackPicture = trackPicture;
    }

    public Hyperlink getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(Hyperlink trackTitle) {
        this.trackTitle = trackTitle;
    }
}
