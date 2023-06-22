package Client.Controllers.Boxes.MusicMainBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MusicMainBoxController {

    @FXML
    private HBox artistHBox;

    @FXML
    private ImageView trackPicture;

    @FXML
    private Hyperlink trackTitle;

    @FXML
    void goToAlbumPage(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public HBox getArtistHBox() {
        return artistHBox;
    }

    public void setArtistHBox(HBox artistHBox) {
        this.artistHBox = artistHBox;
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
