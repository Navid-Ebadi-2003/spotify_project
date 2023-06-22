package Client.Controllers.HomePage;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class HomePageController {
    @FXML
    private AnchorPane homePageAnchorPane;

    @FXML
    private HBox likedMusicsHbox;

    @FXML
    private HBox suggestedMusicsHbox;
    /*
        setter and getters
     */

    public AnchorPane getHomePageAnchorPane() {
        return homePageAnchorPane;
    }

    public void setHomePageAnchorPane(AnchorPane homePageAnchorPane) {
        this.homePageAnchorPane = homePageAnchorPane;
    }

    public HBox getLikedMusicsHbox() {
        return likedMusicsHbox;
    }

    public void setLikedMusicsHbox(HBox likedMusicsHbox) {
        this.likedMusicsHbox = likedMusicsHbox;
    }

    public HBox getSuggestedMusicsHbox() {
        return suggestedMusicsHbox;
    }

    public void setSuggestedMusicsHbox(HBox suggestedMusicsHbox) {
        this.suggestedMusicsHbox = suggestedMusicsHbox;
    }
}
