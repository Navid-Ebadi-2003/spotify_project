package Client.Controllers.PlaylistPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PlaylistController {
    @FXML
    private ScrollPane bottomScrollPane;

    @FXML
    private Button likeButton;

    @FXML
    private ImageView playlistPicture;

    @FXML
    private Label playlistTitle;

    @FXML
    private VBox tracksVbox;

    @FXML
    void like_unlike(ActionEvent event) {

    }
}
