package Client.Controllers.Boxes.UserBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;

import java.util.UUID;

public class UserBoxController {
    @FXML
    private ImageView userPicture;

    @FXML
    private Hyperlink usernameHyperLink;
    // This stores id of the user
    private UUID userId;

    @FXML
    void goToUserPage(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public ImageView getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(ImageView userPicture) {
        this.userPicture = userPicture;
    }

    public Hyperlink getUsernameHyperLink() {
        return usernameHyperLink;
    }

    public void setUsernameHyperLink(Hyperlink usernameHyperLink) {
        this.usernameHyperLink = usernameHyperLink;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
