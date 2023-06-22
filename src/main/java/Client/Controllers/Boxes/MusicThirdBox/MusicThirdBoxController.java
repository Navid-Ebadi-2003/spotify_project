package Client.Controllers.Boxes.MusicThirdBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MusicThirdBoxController {
    @FXML
    private HBox artistsHbox;

    @FXML
    private Button downPlayTrack;

    @FXML
    private ImageView downloadPlayIcon;

    @FXML
    private ImageView trackPicture;

    @FXML
    private Label trackTitle;

    @FXML
    void downPlay(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public HBox getArtistsHbox() {
        return artistsHbox;
    }

    public void setArtistsHbox(HBox artistsHbox) {
        this.artistsHbox = artistsHbox;
    }

    public Button getDownPlayTrack() {
        return downPlayTrack;
    }

    public void setDownPlayTrack(Button downPlayTrack) {
        this.downPlayTrack = downPlayTrack;
    }

    public ImageView getDownloadPlayIcon() {
        return downloadPlayIcon;
    }

    public void setDownloadPlayIcon(ImageView downloadPlayIcon) {
        this.downloadPlayIcon = downloadPlayIcon;
    }

    public ImageView getTrackPicture() {
        return trackPicture;
    }

    public void setTrackPicture(ImageView trackPicture) {
        this.trackPicture = trackPicture;
    }

    public Label getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(Label trackTitle) {
        this.trackTitle = trackTitle;
    }
}
