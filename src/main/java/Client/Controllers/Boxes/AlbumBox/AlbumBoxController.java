package Client.Controllers.Boxes.AlbumBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.UUID;

public class AlbumBoxController {
    @FXML
    private ImageView albumPicture;

    @FXML
    private Hyperlink albumTitleHyperLink;

    @FXML
    private HBox artistHBox;
    // This is for sending request if a user clicks on hyperlink
    private UUID albumId;
    // This stores artistName, artistId
    private HashMap<String, UUID> artists;

    @FXML
    public void goToAlbumPage(ActionEvent event) {

    }

    /*
        setter and getters
     */

    public ImageView getAlbumPicture() {
        return albumPicture;
    }

    public void setAlbumPicture(ImageView albumPicture) {
        this.albumPicture = albumPicture;
    }

    public Hyperlink getAlbumTitleHyperLink() {
        return albumTitleHyperLink;
    }

    public void setAlbumTitleHyperLink(Hyperlink albumTitleHyperLink) {
        this.albumTitleHyperLink = albumTitleHyperLink;
    }

    public HBox getArtistHBox() {
        return artistHBox;
    }

    public void setArtistHBox(HBox artistHBox) {
        this.artistHBox = artistHBox;
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
}
