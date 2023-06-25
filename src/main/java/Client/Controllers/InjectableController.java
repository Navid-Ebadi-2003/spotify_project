package Client.Controllers;

import javafx.scene.Node;
import javafx.scene.image.Image;


public interface InjectableController {
    void setControllerProfilePic(Image profilePic);

    Node getMainScene();
}
