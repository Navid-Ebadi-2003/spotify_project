package Client.Controllers.UserPage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class UserPageController {

    @FXML
    private HBox albumsHBox;

    @FXML
    private HBox artistsHBox;

    @FXML
    private HBox musicsHBox;

    @FXML
    private HBox playlistsHBox;

    @FXML
    private HBox usersHBox;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Button followButton;

    @FXML
    private Label username;
}
