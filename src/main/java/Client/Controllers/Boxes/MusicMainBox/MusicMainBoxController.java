package Client.Controllers.Boxes.MusicMainBox;

import Client.Controllers.AlbumPage.AlbumPageController;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class MusicMainBoxController implements InjectableController {
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    private MainPageController mainPageController;


    @FXML
    private HBox artistsHbox;

    @FXML
    private ImageView trackPicture;

    @FXML
    private Hyperlink trackTitle;
    // This stores the albumId of the track
    @FXML
    private VBox musicMainVbox;
    private UUID albumId;
    // This stores artistName, artistId
    private HashMap<String, UUID> artists;

    @FXML
    void goToAlbumPage(ActionEvent event) throws IOException {
        // Loading albumPage
        FXMLLoader albumPageLoader = new FXMLLoader(MusicMainBoxController.class.getResource("../../AlbumPage/album-page.fxml"));
        albumPageLoader.load();
        // Passing albumPage Controller
        AlbumPageController albumPageController = albumPageLoader.getController();
        // Switching pages from homePage to albumPage
        mainPageController.switchPage(albumPageController.getMainScene());
        // Sending Request
        requestObject.watchAlbumPageReq(albumId);
        // Receiving response
        String response = in.nextLine();
        JsonObject jsonTemplate = new Gson().fromJson(response, JsonObject.class);
        JsonObject responseBody = jsonTemplate.getAsJsonObject("responseBody");
        // Parsing JsonObject
        JsonObject jsonResults = responseBody.getAsJsonObject("results");
        // Building Arraylist of Controllers

        ArrayList<InjectableController> musicControllers = BoxBuilder.buildMusicThirdBox(jsonResults, "tracks", mainPageController, clientSocket);
        // Adding musicBoxes to AlbumPage
        for (InjectableController controller : musicControllers) {
            albumPageController.getTracksVbox().getChildren().add(controller.getMainScene());
        }
        // AlbumPage setters:
        albumPageController.setAlbumTitle(new Label(jsonResults.get("title").getAsString()));
        DownloadFile downloadFileTask = new DownloadFile(jsonResults.get("fileName").getAsString(), albumPageController, "profilePath", clientSocket);
        ArrayList<DownloadFile> downloadFileArray = new ArrayList<DownloadFile>();
        downloadFileArray.add(downloadFileTask);

        // Thread thread_0 = new Thread(downloadFileTask);
        // thread_0.start();
        // Building jsonArrays
        JsonArray tracks = jsonResults.getAsJsonArray("tracks");
        // Building controllerArrays
        // Assigning thread to download profilePictures
        DownloadFiles downloadFilesTask = new DownloadFiles(tracks, musicControllers, "profilePath", clientSocket);
        ArrayList<DownloadFiles> downloadFilesArray = new ArrayList<DownloadFiles>();
        downloadFilesArray.add(downloadFilesTask);

        Download download = new Download(downloadFileArray, downloadFilesArray);
        download.start();

        // Thread thread_1 = new Thread(downloadFilesTask);
        // // Starting thread
        // thread_1.start();

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
    /*
        setter and getters
     */

    public HBox getArtistsHbox() {
        return artistsHbox;
    }

    public void setArtistsHbox(HBox artistsHbox) {
        this.artistsHbox = artistsHbox;
    }

    public ImageView getTrackPicture() {
        return trackPicture;
    }

    public void setTrackPicture(ImageView trackPicture) {
        this.trackPicture = trackPicture;
    }

    public Hyperlink getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle.setText(trackTitle);
    }

    public UUID getAlbumId() {
        return albumId;
    }

    public void setAlbumId(UUID albumId) {
        this.albumId = albumId;
    }

    public HashMap<String, UUID> getArtists() {
        return artists;
    }

    public void setArtists(HashMap<String, UUID> artists) {
        this.artists = artists;
    }

    public void addHyperLink(Hyperlink hyperlink) {
        this.artistsHbox.getChildren().add(hyperlink);
    }

    @Override
    public void setControllerProfilePic(Image profilePic) {
        this.trackPicture.setImage(profilePic);
    }

    @Override
    public Node getMainScene() {
        return this.musicMainVbox;
    }

    public void setMusicMainVbox(VBox musicMainVbox) {
        this.musicMainVbox = musicMainVbox;
    }

}
