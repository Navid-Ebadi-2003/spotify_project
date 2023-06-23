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

    public ArrayList<HBox> buildSuggestedMusicBox(JsonObject jsonResults) throws IOException {
        JsonArray suggestedMusicsJson = jsonResults.getAsJsonArray("randomMusicsResult");
        ArrayList<HBox> musicBoxs = new ArrayList<>();
        for (JsonElement arrayItem : suggestedMusicsJson) {
            JsonObject musicJson = (JsonObject) arrayItem;

            FXMLLoader suggestedMusicBoxLoader = new FXMLLoader(HomePageController.class.getResource("../Boxes/MusicMainBox/music-main-box.fxml"));
            MusicMainBoxController musicMainBoxController = suggestedMusicBoxLoader.getController();

            //get artist information
            JsonArray artistsJson = (musicJson).getAsJsonArray("artists");
            HashMap<String , UUID> artists = new HashMap<>();
            for(int i = 0 ; i<artistsJson.size();i++){
                String artisName = artistsJson.get(i).getAsJsonObject().get("artistName").getAsString();
                UUID artistID = UUID.fromString(artistsJson.get(i).getAsJsonObject().get("artistId").getAsString());
                artists.put( artisName, artistID);

                //add artist hyperlink to it's Hbox
                musicMainBoxController.addHyperLink(new Hyperlink(artisName));
            }
            //setters :
            musicMainBoxController.setArtists(artists);
            musicMainBoxController.setAlbumId(UUID.fromString(musicJson.get("albumId").getAsString()));
            musicMainBoxController.setTrackTitle(new Hyperlink(musicJson.get("title").getAsString()));

            //musicMainBoxController.setTrackPicture();  after download task

            musicBoxs.add(suggestedMusicBoxLoader.load());
        }
        return musicBoxs;
    }


    public ArrayList<HBox> buildLikedMusicBox(JsonObject jsonResults) throws IOException {
        JsonArray likedMusicsJson = jsonResults.getAsJsonArray("likedMusicsResult");
        ArrayList<HBox> musicBoxs = new ArrayList<>();
        for (JsonElement arrayItem : likedMusicsJson) {
            JsonObject musicJson = (JsonObject) arrayItem;

            FXMLLoader likedMusicBoxLoader = new FXMLLoader(HomePageController.class.getResource("../Boxes/MusicMainBox/music-main-box.fxml"));
            MusicMainBoxController musicMainBoxController = likedMusicBoxLoader.getController();

            //get artist information
            JsonArray artistsJson = (musicJson).getAsJsonArray("artists");
            HashMap<String , UUID> artists = new HashMap<>();
            for(int i = 0 ; i<artistsJson.size();i++){
                String artisName = artistsJson.get(i).getAsJsonObject().get("artistName").getAsString();
                UUID artistID = UUID.fromString(artistsJson.get(i).getAsJsonObject().get("artistId").getAsString());
                artists.put( artisName, artistID);

                //add artist hyperlink to it's Hbox
                musicMainBoxController.addHyperLink(new Hyperlink(artisName));
            }
            //setters :
            musicMainBoxController.setArtists(artists);
            musicMainBoxController.setAlbumId(UUID.fromString(musicJson.get("albumId").getAsString()));
            musicMainBoxController.setTrackTitle(new Hyperlink(musicJson.get("title").getAsString()));

            //musicMainBoxController.setTrackPicture();  after download task

            musicBoxs.add(likedMusicBoxLoader.load());
        }
        return musicBoxs;
    }

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
