package Client.Controllers.Boxes.MusicMainBox;

import Client.Controllers.InjectableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.UUID;

public class MusicMainBoxController implements InjectableController {

    @FXML
    private HBox artistsHbox;

    @FXML
    private ImageView trackPicture;

    @FXML
    private Hyperlink trackTitle;
    // This stores the albumId of the track
    @FXML
    private VBox musicMainVbox;
    private UUID albumId;
    // This stores artistName, artistId
    private HashMap<String, UUID> artists;

    @FXML
    void goToAlbumPage(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public HBox getArtistsHbox() {
        return artistsHbox;
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

    public UUID getAlbumId() {
        return albumId;
    }

    public void setAlbumId(UUID albumId) {
        this.albumId = albumId;
    }

    public HashMap<String, UUID> getArtists() {
        return artists;
    }

    public void setArtists(HashMap<String, UUID> artists) {
        this.artists = artists;
    }
    public void addHyperLink(Hyperlink hyperlink) {
        this.artistsHbox.getChildren().add(hyperlink);
    }

    @Override
    public void setControllerProfilePic(Image profilePic) {
        this.trackPicture.setImage(profilePic);
    }

    public void setArtistsHbox(HBox artistsHbox) {
        this.artistsHbox = artistsHbox;
    }

    public VBox getMusicMainVbox() {
        return musicMainVbox;
    }

    public void setMusicMainVbox(VBox musicMainVbox) {
        this.musicMainVbox = musicMainVbox;
    }
}
