package Client.Controllers.Boxes.MusicThirdBox;

import Client.Controllers.Boxes.MusicSecondBox.MusicSecondBoxController;
import Client.Controllers.InjectableController;
import Client.Controllers.MainPage.MainPageController;
import Client.DownloadFile;
import Shared.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class MusicThirdBoxController implements InjectableController {
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    private MainPageController mainPageController;
    private Image playIcon = new Image("file:src\\main\\resources\\Icons\\play.png");
    private boolean isDownloaded;

    @FXML
    private HBox artistsHbox;

    @FXML
    private Button downPlayTrack;

    @FXML
    private ImageView downloadPlayIcon;

    @FXML
    private ImageView trackPicture;

    @FXML
    private Hyperlink trackTitleHyperLink;
    @FXML
    private HBox musicMainHbox;
    // This stores id of the album
    private UUID albumId;
    private UUID trackId;
    private String fileName;
    // This stores artistName, artistId
    private HashMap<String, UUID> artists;

    @FXML
    void downPlay(ActionEvent event) {
        if (!isDownloaded) {
            requestObject.watchMusicPageReq(trackId);
            String response = in.nextLine();
            JsonObject jsonTemplate = new Gson().fromJson(response, JsonObject.class);
            JsonObject responseBody = jsonTemplate.getAsJsonObject("responseBody");
            JsonObject jsonResults = responseBody.getAsJsonObject("results");
            String fileName = jsonResults.get("fileName").getAsString();
            try {
                DownloadFile.downloadTrackOnly(fileName, this, clientSocket);
                FXMLLoader musicSecondBoxLoader = new FXMLLoader(getClass().getResource("../MusicSecondBox/music-second-box.fxml"));
                musicSecondBoxLoader.load();
                MusicSecondBoxController musicSecondBoxController = musicSecondBoxLoader.getController();
                musicSecondBoxController.setControllerProfilePic(this.trackPicture.getImage());
                musicSecondBoxController.setTrackTitleHyperLink(this.trackTitleHyperLink.getText());
                mainPageController.setMusicShowHbox((HBox) musicSecondBoxController.getMainScene());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.setDownloaded(true);
        } else {
//            this.downloadPlayIcon.setDisable(true);
//            this.downloadPlayIcon.setVisible(false);
            mainPageController.setTrackForMusicPlayer(fileName);
        }
    }

    public void changeDownToPlayIcon() {
        this.downloadPlayIcon.setImage(playIcon);
    }

    @FXML
    void goToAlbumPage(ActionEvent event) {

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

    public Button getDownPlayTrack() {
        return downPlayTrack;
    }

    public void setDownPlayTrack(Button downPlayTrack) {
        this.downPlayTrack = downPlayTrack;
    }

    public ImageView getDownloadPlayIcon() {
        return downloadPlayIcon;
    }

    public void setDownloadPlayIcon(ImageView downloadPlayIcon) {
        this.downloadPlayIcon = downloadPlayIcon;
    }

    public ImageView getTrackPicture() {
        return trackPicture;
    }

    public void setTrackPicture(ImageView trackPicture) {
        this.trackPicture = trackPicture;
    }

    public Hyperlink getTrackTitleHyperLink() {
        return trackTitleHyperLink;
    }

    public void setTrackTitleHyperLink(String trackTitle) {
        this.trackTitleHyperLink.setText(trackTitle);
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

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Request getRequestObject() {
        return requestObject;
    }

    public void setRequestObject(Request requestObject) {
        this.requestObject = requestObject;
    }

    public Scanner getIn() {
        return in;
    }

    public void setIn(Scanner in) {
        this.in = in;
    }

    public MainPageController getMainPageController() {
        return mainPageController;
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    public Image getPlayIcon() {
        return playIcon;
    }

    public void setPlayIcon(Image playIcon) {
        this.playIcon = playIcon;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public HBox getMusicMainHbox() {
        return musicMainHbox;
    }

    public void setMusicMainHbox(HBox musicMainHbox) {
        this.musicMainHbox = musicMainHbox;
    }

    public UUID getTrackId() {
        return trackId;
    }

    public void setTrackId(UUID trackId) {
        this.trackId = trackId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    @Override
    public void setControllerProfilePic(Image profilePic) {
        this.trackPicture.setImage(profilePic);
    }

    @Override
    public Node getMainScene() {
        return this.musicMainHbox;
    }

    public void addHyperLink(Hyperlink hyperlink) {
        this.artistsHbox.getChildren().add(hyperlink);
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitleHyperLink.setText(trackTitle);
    }


}
