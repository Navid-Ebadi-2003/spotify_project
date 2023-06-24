package Client.Controllers.Boxes.MusicMainBox;

import Client.Controllers.AlbumPage.AlbumPageController;
import Client.Controllers.InjectableController;
import Client.Controllers.MainPage.MainPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
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
    private MainPageController mainPageController;

    @FXML
    void goToAlbumPage(ActionEvent event) throws IOException {
        FXMLLoader albumPageLoader = new FXMLLoader(MusicMainBoxController.class.getResource("../../AlbumPage/album-page.fxml"));
        albumPageLoader.load();
        Scene currentScene = (((Node)event.getSource())).getScene();
        AlbumPageController albumPageController = albumPageLoader.getController();
        currentScene = albumPageController.getMainScene().getScene();


    }



    public void setter(Socket clientSocket, MainPageController mainPageController) {
        this.mainPageController = mainPageController;
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

    public void setTrackTitle(String trackTitle) {
        this.trackTitle.setText(trackTitle);
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

    @Override
    public Node getMainScene() {
        return this.musicMainVbox;
    }

    public void setArtistsHbox(HBox artistsHbox) {
        this.artistsHbox = artistsHbox;
    }

    public void setMusicMainVbox(VBox musicMainVbox) {
        this.musicMainVbox = musicMainVbox;
    }
}
