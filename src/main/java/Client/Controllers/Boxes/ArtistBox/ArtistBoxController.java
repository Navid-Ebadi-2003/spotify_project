package Client.Controllers.Boxes.ArtistBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class ArtistBoxController {
    @FXML
    private HBox albumsHbox;

    @FXML
    private Text artistAboutText;

    @FXML
    private Label artistName;

    @FXML
    private ImageView artistPicture;

    @FXML
    private Button followButton;

    @FXML
    void toggleFollow(ActionEvent event) {

    }
}
