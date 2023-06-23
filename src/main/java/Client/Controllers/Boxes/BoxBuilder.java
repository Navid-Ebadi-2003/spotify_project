package Client.Controllers.Boxes;

import Client.Controllers.Boxes.MusicMainBox.MusicMainBoxController;
import Client.Controllers.Boxes.PlaylistSecondBox.PlaylistSecondBoxController;
import Client.Controllers.HomePage.HomePageController;
import Client.Controllers.InjectableController;
import Client.Controllers.MainPage.MainPageController;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class BoxBuilder {

    public static HashMap<HBox, InjectableController> buildPlaylistSecondBox(JsonObject jsonResults, String jsonArrayKey) throws IOException {
        // Parsing JsonObjects
        JsonArray playlistsJson = jsonResults.getAsJsonArray(jsonArrayKey);
        // This stores each HBox that we create for objects, and it's Controller
        HashMap<HBox, InjectableController> playlistBoxes = new HashMap<>();

        // Parsing JsonObject of each individual playlist
        for (JsonElement arrayItem : playlistsJson) {
            JsonObject playlistJson = (JsonObject) arrayItem;
            JsonObject creatorJson = (playlistJson).getAsJsonObject("creator");
            FXMLLoader playlistBoxLoader = new FXMLLoader(MainPageController.class.getResource("../Boxes/PlaylistSecondBox/playlist-second-box.fxml"));
            PlaylistSecondBoxController playlistSecondBoxController = playlistBoxLoader.getController();
            // Setting it's controller data
            playlistSecondBoxController.setCreatorNameHyperLink(new Hyperlink(creatorJson.get("username").getAsString()));
            playlistSecondBoxController.setPlaylistTitleHyperLink(new Hyperlink(playlistJson.get("title").getAsString()));
            playlistSecondBoxController.setCreatorId(UUID.fromString(creatorJson.get("userId").getAsString()));
            playlistSecondBoxController.setPlaylistId(UUID.fromString(playlistJson.get("playlistId").getAsString()));
            // Putting HBox and it's controller into Hashmap
            playlistBoxes.put(playlistBoxLoader.load(), playlistSecondBoxController);
        }
        return playlistBoxes;
    }

    public static HashMap<VBox, InjectableController> buildMusicMainBox(JsonObject jsonResults, String jsonArrayKey) throws IOException {
        // Parsing JsonObjects
        JsonArray musicsJson = jsonResults.getAsJsonArray(jsonArrayKey);
        // This stores each VBox that we create for objects, and it's Controller
        HashMap<VBox, InjectableController> musicBoxes = new HashMap<>();

        // Parsing JsonObject of each individual music
        for (JsonElement arrayItem : musicsJson) {
            JsonObject musicJson = (JsonObject) arrayItem;
            FXMLLoader suggestedMusicBoxLoader = new FXMLLoader(HomePageController.class.getResource("../Boxes/MusicMainBox/music-main-box.fxml"));
            MusicMainBoxController musicMainBoxController = suggestedMusicBoxLoader.getController();
            // Parsing artists of music
            JsonArray artistsJson = (musicJson).getAsJsonArray("artists");
            HashMap<String , UUID> artists = new HashMap<>();
            for (int i = 0 ; i < artistsJson.size(); i++) {
                String artistName = artistsJson.get(i).getAsJsonObject().get("artistName").getAsString();
                UUID artistID = UUID.fromString(artistsJson.get(i).getAsJsonObject().get("artistId").getAsString());
                artists.put(artistName, artistID);

                // Add artist hyperlink to it's Hbox
                musicMainBoxController.addHyperLink(new Hyperlink(artistName));
            }
            // Setters :
            musicMainBoxController.setArtists(artists);
            musicMainBoxController.setAlbumId(UUID.fromString(musicJson.get("albumId").getAsString()));
            musicMainBoxController.setTrackTitle(new Hyperlink(musicJson.get("title").getAsString()));

            musicBoxes.put(suggestedMusicBoxLoader.load(), musicMainBoxController);
        }
        return musicBoxs;
    }
}
