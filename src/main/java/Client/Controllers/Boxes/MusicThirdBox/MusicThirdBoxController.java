package Client.Controllers.Boxes.MusicThirdBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.UUID;

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
    private Hyperlink trackTitleHyperLink;
    // This stores id of the track
    private UUID trackId;
    // This stores artistName, artistId
    private HashMap<String, UUID> artists;

    @FXML
    void downPlay(ActionEvent event) {

    }

    @FXML
    void goToAlbumPage(ActionEvent event) {

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

    public Hyperlink getTrackTitleHyperLink() {
        return trackTitleHyperLink;
    }

    public void setTrackTitleHyperLink(Hyperlink trackTitleHyperLink) {
        this.trackTitleHyperLink = trackTitleHyperLink;
    }

    public UUID getTrackId() {
        return trackId;
    }

    public void setTrackId(UUID trackId) {
        this.trackId = trackId;
    }

    public HashMap<String, UUID> getArtists() {
        return artists;
    }

    public void setArtists(HashMap<String, UUID> artists) {
        this.artists = artists;
    }
}
