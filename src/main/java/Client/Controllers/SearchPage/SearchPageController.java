package Client.Controllers.SearchPage;

import Client.Controllers.Boxes.BoxBuilder;
import Client.Controllers.InjectableController;
import Client.Controllers.MainPage.MainPageController;
import Client.Download;
import Client.DownloadFile;
import Client.DownloadFiles;
import Shared.Request;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchPageController {
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    private MainPageController mainPageController;

    @FXML
    private HBox albumsHBox;

    @FXML
    private HBox artistsHBox;

    @FXML
    private HBox musicsHBox;

    @FXML
    private HBox playlistsHBox;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private HBox usersHBox;

    @FXML
    void searchText(ActionEvent event) throws IOException {

        String pattern = searchField.getText();
        requestObject.searchReq(pattern);

        String response = in.nextLine();
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        JsonObject responseBody = jsonResponse.getAsJsonObject("responseBody");
        JsonObject jsonResults = responseBody.getAsJsonObject("results");

        //JsonArray albumsJson = jsonResults.getAsJsonArray("albumsResult"); todo
        JsonArray artistsJson = jsonResults.getAsJsonArray("artistsResult");
        JsonArray musicsJson = jsonResults.getAsJsonArray("musicsResult");
        JsonArray playlistsJson = jsonResults.getAsJsonArray("playlistsResult");
        JsonArray usersJson = jsonResults.getAsJsonArray("usersResult");

        // albums todo
        ArrayList<InjectableController> artists = BoxBuilder.buildArtistBox(jsonResults, "artistsResult");
        ArrayList<InjectableController> musics = BoxBuilder.buildMusicMainBox(jsonResults, "musicsResult", mainPageController, clientSocket);
        ArrayList<InjectableController> playlists = BoxBuilder.buildPlaylistMainBox(jsonResults, "playlistsResult");
        ArrayList<InjectableController> users = BoxBuilder.buildUserBox(jsonResults, "usersResult");

        // forLoop for albums todo
        for (InjectableController controller : artists) {
            this.artistsHBox.getChildren().add(controller.getMainScene());
        }
        for (InjectableController controller : musics) {
            this.musicsHBox.getChildren().add(controller.getMainScene());
        }
        for (InjectableController controller : playlists) {
            this.playlistsHBox.getChildren().add(controller.getMainScene());
        }
        for (InjectableController controller : users) {
            this.usersHBox.getChildren().add(controller.getMainScene());
        }

        JsonArray jsonArrays = new JsonArray();
        // add albumsJson todo
        jsonArrays.add(artistsJson);
        jsonArrays.add(musicsJson);
        jsonArrays.add(playlistsJson);
        jsonArrays.add(usersJson);
        // Building controllerArrays
        List<InjectableController> controllers = new ArrayList<>();
        // add albums todo
        DownloadFiles donwloadArtists = new DownloadFiles(artistsJson, artists, "profilePath", clientSocket);
        DownloadFiles donwloadMusics = new DownloadFiles(musicsJson, musics, "profilePath", clientSocket);
        DownloadFiles donwloadPlaylists = new DownloadFiles(playlistsJson, playlists, "profilePath", clientSocket);
        DownloadFiles donwloadUsers = new DownloadFiles(usersJson, users, "profilePath", clientSocket);

        ArrayList<DownloadFiles> downloadFilesTask = new ArrayList<>();
        downloadFilesTask.add(donwloadArtists);
        downloadFilesTask.add(donwloadMusics);
        downloadFilesTask.add(donwloadPlaylists);
        downloadFilesTask.add(donwloadUsers);

        ArrayList<DownloadFile> downloadFileTask = new ArrayList<>();
        Download downloadThread = new Download(downloadFileTask, downloadFilesTask);
        downloadThread.start();
        // Assigning thread to download profilePictures
    }
    /*
        setter and getters
     */

    public HBox getAlbumsHBox() {
        return albumsHBox;
    }

    public void setAlbumsHBox(HBox albumsHBox) {
        this.albumsHBox = albumsHBox;
    }

    public HBox getArtistsHBox() {
        return artistsHBox;
    }

    public void setArtistsHBox(HBox artistsHBox) {
        this.artistsHBox = artistsHBox;
    }

    public HBox getMusicsHBox() {
        return musicsHBox;
    }

    public void setMusicsHBox(HBox musicsHBox) {
        this.musicsHBox = musicsHBox;
    }

    public HBox getPlaylistsHBox() {
        return playlistsHBox;
    }

    public void setPlaylistsHBox(HBox playlistsHBox) {
        this.playlistsHBox = playlistsHBox;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public void setSearchField(TextField searchField) {
        this.searchField = searchField;
    }

    public HBox getUsersHBox() {
        return usersHBox;
    }

    public void setUsersHBox(HBox usersHBox) {
        this.usersHBox = usersHBox;
    }

    public void setter(Socket clientSocket, MainPageController mainPageController) {
        this.mainPageController = mainPageController;
        this.clientSocket = clientSocket;
        this.requestObject = new Request(this.clientSocket);
        try {
            this.in = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
