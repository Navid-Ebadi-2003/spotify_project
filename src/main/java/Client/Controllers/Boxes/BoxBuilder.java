package Client.Controllers.Boxes;

import Client.Controllers.Boxes.ArtistBox.ArtistBoxController;
import Client.Controllers.Boxes.MusicMainBox.MusicMainBoxController;
import Client.Controllers.Boxes.MusicThirdBox.MusicThirdBoxController;
import Client.Controllers.Boxes.PlaylistMainBox.PlaylistMainBoxController;
import Client.Controllers.Boxes.PlaylistSecondBox.PlaylistSecondBoxController;
import Client.Controllers.Boxes.UserBox.UserBoxController;
import Client.Controllers.HomePage.HomePageController;
import Client.Controllers.InjectableController;
import Client.Controllers.MainPage.MainPageController;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class BoxBuilder {

    public static ArrayList<InjectableController> buildPlaylistSecondBox(JsonObject jsonResults, String jsonArrayKey, MainPageController mainPageController, Socket clientSocket) throws IOException {
        // Parsing JsonObjects
        JsonArray playlistsJson = jsonResults.getAsJsonArray(jsonArrayKey);
        // This stores each HBox that we create for objects, and it's Controller
        ArrayList<InjectableController> playlistControllers = new ArrayList<>();

        // Parsing JsonObject of each individual playlist
        for (JsonElement arrayItem : playlistsJson) {
            JsonObject playlistJson = (JsonObject) arrayItem;
            JsonObject creatorJson = playlistJson.getAsJsonObject("creator");
            FXMLLoader playlistBoxLoader = new FXMLLoader(MainPageController.class.getResource("../Boxes/PlaylistSecondBox/playlist-second-box.fxml"));
            playlistBoxLoader.load();
            PlaylistSecondBoxController playlistSecondBoxController = playlistBoxLoader.getController();
            // Setting it's controller data
            playlistSecondBoxController.setCreatorNameHyperLink(creatorJson.get("username").getAsString());
            playlistSecondBoxController.setPlaylistTitleHyperLink(playlistJson.get("title").getAsString());
            playlistSecondBoxController.setCreatorId(UUID.fromString(creatorJson.get("userId").getAsString()));
            playlistSecondBoxController.setPlaylistId(UUID.fromString(playlistJson.get("playlistId").getAsString()));
            playlistSecondBoxController.setter(clientSocket, mainPageController);
            // Putting HBox and it's controller into Hashmap
            playlistControllers.add(playlistSecondBoxController);
        }
        return playlistControllers;
    }

    public static ArrayList<InjectableController> buildMusicMainBox(JsonObject jsonResults, String jsonArrayKey, MainPageController mainPageController, Socket clientSocket) throws IOException {
        // Parsing JsonObjects
        JsonArray musicsJson = jsonResults.getAsJsonArray(jsonArrayKey);
        // This stores each VBox that we create for objects, and it's Controller
        ArrayList<InjectableController> musicControllers = new ArrayList<>();

        // Parsing JsonObject of each individual music
        for (JsonElement arrayItem : musicsJson) {
            JsonObject musicJson = (JsonObject) arrayItem;
            FXMLLoader musicMainBoxLoader = new FXMLLoader(HomePageController.class.getResource("../Boxes/MusicMainBox/music-main-box.fxml"));
            musicMainBoxLoader.load();
            MusicMainBoxController musicMainBoxController = musicMainBoxLoader.getController();
            // Parsing artists of music
            JsonArray artistsJson = (musicJson).getAsJsonArray("artists");
            HashMap<String, UUID> artists = new HashMap<>();
            for (int i = 0; i < artistsJson.size(); i++) {
                String artistName = artistsJson.get(i).getAsJsonObject().get("name").getAsString();
                UUID artistID = UUID.fromString(artistsJson.get(i).getAsJsonObject().get("artistId").getAsString());
                artists.put(artistName, artistID);

                // Add artist hyperlink to it's Hbox
                musicMainBoxController.addHyperLink(new Hyperlink(artistName));
            }
            // Setters :
            musicMainBoxController.setArtists(artists);
            musicMainBoxController.setAlbumId(UUID.fromString(musicJson.get("albumId").getAsString()));
            musicMainBoxController.setTrackTitle(musicJson.get("title").getAsString());
            musicMainBoxController.setter(clientSocket, mainPageController);

            musicControllers.add(musicMainBoxController);
        }
        return musicControllers;
    }

    public static ArrayList<InjectableController> buildArtistBox(JsonObject jsonResults, String jsonArrayKey) throws IOException {
        // Parsing JsonObjects
        JsonArray artistsJson = jsonResults.getAsJsonArray(jsonArrayKey);
        // This stores each VBox that we create for objects, and it's Controller
        ArrayList<InjectableController> artistControllers = new ArrayList<>();

        // Parsing JsonObject of each individual artist
        for (JsonElement arrayItem : artistsJson) {
            JsonObject artistJson = (JsonObject) arrayItem;
            FXMLLoader artistBoxLoader = new FXMLLoader(HomePageController.class.getResource("../Boxes/ArtistBox/artist-box.fxml"));
            artistBoxLoader.load();
            ArtistBoxController artistBoxController = artistBoxLoader.getController();

            // Setters :
            artistBoxController.setArtistHyperLink(artistJson.get("name").getAsString());
            artistBoxController.setArtistId(UUID.fromString(artistJson.get("artistId").getAsString()));


            artistControllers.add(artistBoxController);
        }
        return artistControllers;
    }

    public static ArrayList<InjectableController> buildUserBox(JsonObject jsonResults, String jsonArrayKey) throws IOException {
        // Parsing JsonObjects
        JsonArray usersJson = jsonResults.getAsJsonArray(jsonArrayKey);
        // This stores each VBox that we create for objects, and it's Controller
        ArrayList<InjectableController> userControllers = new ArrayList<>();

        // Parsing JsonObject of each individual user
        for (JsonElement arrayItem : usersJson) {
            JsonObject userJson = (JsonObject) arrayItem;
            FXMLLoader userBoxLoader = new FXMLLoader(HomePageController.class.getResource("../Boxes/ArtistBox/artist-box.fxml"));
            userBoxLoader.load();
            UserBoxController userBoxController = userBoxLoader.getController();

            // Setters :
            userBoxController.setUsernameHyperLink(new Hyperlink(userJson.get("username").getAsString()));
            userBoxController.setUserId(UUID.fromString(userJson.get("userId").getAsString()));

            userControllers.add(userBoxController);
        }
        return userControllers;
    }

    public static ArrayList<InjectableController> buildPlaylistMainBox(JsonObject jsonResults, String jsonArrayKey) throws IOException {
        // Parsing JsonObjects
        JsonArray playlistsJson = jsonResults.getAsJsonArray(jsonArrayKey);
        // This stores each VBox that we create for objects, and it's Controller
        ArrayList<InjectableController> playlistControllers = new ArrayList<>();

        // Parsing JsonObject of each individual playlist
        for (JsonElement arrayItem : playlistsJson) {
            JsonObject playlistJson = (JsonObject) arrayItem;
            FXMLLoader playlistBoxLoader = new FXMLLoader(HomePageController.class.getResource("../Boxes/PlaylistMainBox/playlist-main-box.fxml"));
            playlistBoxLoader.load();
            PlaylistMainBoxController playlistMainBoxController = playlistBoxLoader.getController();

            // Setters :
            playlistMainBoxController.setPlaylistTitleHyperLink(new Hyperlink(playlistJson.get("title").getAsString()));
            playlistMainBoxController.setCreatorNameHyperLink(new Hyperlink(playlistJson.get("creator").getAsJsonObject().get("username").getAsString()));
            playlistMainBoxController.setPlaylistId(UUID.fromString(playlistJson.get("playlistId").getAsString()));
            playlistMainBoxController.setCreatorId(UUID.fromString(playlistJson.get("creator").getAsJsonObject().get("userId").getAsString()));

            playlistControllers.add(playlistMainBoxController);
        }
        return playlistControllers;
    }

    public static ArrayList<InjectableController> buildMusicThirdBox(JsonObject jsonResults, String jsonArrayKey, MainPageController mainPageController, Socket clientSocket) throws IOException {
        // Parsing JsonObjects
        JsonArray musicsJson = jsonResults.getAsJsonArray(jsonArrayKey);
        // This stores each VBox that we create for objects, and it's Controller
        ArrayList<InjectableController> musicControllers = new ArrayList<>();

        // Parsing JsonObject of each individual music
        for (JsonElement arrayItem : musicsJson) {
            JsonObject musicJson = (JsonObject) arrayItem;
            FXMLLoader musicThirdBoxLoader = new FXMLLoader(HomePageController.class.getResource("../Boxes/MusicThirdBox/music-third-box.fxml"));
            musicThirdBoxLoader.load();
            MusicThirdBoxController musicThirdBoxController = musicThirdBoxLoader.getController();
            // Parsing artists of music
            JsonArray artistsJson = (musicJson).getAsJsonArray("artists");
            HashMap<String, UUID> artists = new HashMap<>();
            for (int i = 0; i < artistsJson.size(); i++) {
                String artistName = artistsJson.get(i).getAsJsonObject().get("name").getAsString();
                UUID artistID = UUID.fromString(artistsJson.get(i).getAsJsonObject().get("artistId").getAsString());
                artists.put(artistName, artistID);

                // Add artist hyperlink to it's Hbox
                musicThirdBoxController.addHyperLink(new Hyperlink(artistName));
            }
            // Setters :
            musicThirdBoxController.setArtists(artists);
            musicThirdBoxController.setAlbumId(UUID.fromString(musicJson.get("albumId").getAsString()));
            musicThirdBoxController.setTrackTitle(musicJson.get("title").getAsString());
            musicThirdBoxController.setTrackId(UUID.fromString(musicJson.get("trackId").getAsString()));
            musicThirdBoxController.setFileName(musicJson.get("fileName").getAsString());
            musicThirdBoxController.setter(clientSocket, mainPageController);

            musicControllers.add(musicThirdBoxController);
        }
        return musicControllers;
    }
}
