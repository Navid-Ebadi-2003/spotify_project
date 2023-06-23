package Client.Controllers.HomePage;

import Client.Controllers.Boxes.MusicMainBox.MusicMainBoxController;
import Client.Controllers.Boxes.PlaylistSecondBox.PlaylistSecondBoxController;
import Client.Controllers.MainPage.MainPageController;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


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
