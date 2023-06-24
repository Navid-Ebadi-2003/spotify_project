package Client.Controllers.Boxes.ArtistBox;

import Client.Controllers.InjectableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.UUID;


public class ArtistBoxController implements InjectableController {
    @FXML
    private Hyperlink artistHyperLink;

    @FXML
    private ImageView artistPicture;
    private UUID artistId;

    private VBox artistBox;

    @FXML
    void goToArtistPage(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public Hyperlink getArtistHyperLink() {
        return artistHyperLink;
    }

    public void setArtistHyperLink(Hyperlink artistHyperLink) {
        this.artistHyperLink = artistHyperLink;
    }

    public ImageView getArtistPicture() {
        return artistPicture;
    }

    public void setArtistPicture(ImageView artistPicture) {
        this.artistPicture = artistPicture;
    }

    public UUID getArtistId() {
        return artistId;
    }

    public void setArtistId(UUID artistId) {
        this.artistId = artistId;
    }


    @Override
    public void setControllerProfilePic(Image artistPicture) {
        this.artistPicture.setImage(artistPicture);
    }

    @Override
    public Node getMainScene() {
        return this.artistBox;
    }
}
