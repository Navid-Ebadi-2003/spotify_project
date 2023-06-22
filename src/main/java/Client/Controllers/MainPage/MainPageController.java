package Client.Controllers.MainPage;
import Client.Controllers.Boxes.PlaylistSecondBox.PlaylistSecondBoxController;
import Shared.Request;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.UUID;

public class MainPageController implements Initializable {

    @FXML
    private MenuItem accountPageButton;

    @FXML
    private ImageView addPlaylistButton;

    @FXML
    private Button createPlaylistButton;

    @FXML
    private Button homePageButton;

    @FXML
    private MenuItem logoutButton;

    @FXML
    private Button lyricsButton;

    @FXML
    private Button nextButton;

    @FXML
    private ImageView nextButtonIcon;

    @FXML
    private ScrollPane pageHolder;

    @FXML
    private Button playStopButton;

    @FXML
    private ImageView playStopButtonIcon;

    @FXML
    private Button playlistPageButton;

    @FXML
    private VBox playlistVbox;

    @FXML
    private Button previousButton;

    @FXML
    private ImageView previousButtonIcon;

    @FXML
    private MenuItem profilePageButton;

    @FXML
    private ImageView profilePictureView;

    @FXML
    private Button searchPageButton;

    @FXML
    private Button shuffleButton;

    @FXML
    private ImageView shuffleButtonIcon;

    private Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    private UUID userId;

    @FXML
    public void createPlaylist(ActionEvent event) {

    }

    @FXML
    public void goAccountPage(ActionEvent event) {

    }

    @FXML
    public void goHomePage(ActionEvent event) {

    }

    @FXML
    public void goLyricsPage(ActionEvent event) {

    }

    @FXML
    public void goNextTrack(ActionEvent event) {

    }

    @FXML
    public void goPlaylistPage(ActionEvent event) {

    }

    @FXML
    public void goPreviousTrack(ActionEvent event) {

    }

    @FXML
    public void goProfilePage(ActionEvent event) {

    }

    @FXML
    public void goSearchPage(ActionEvent event) {

    }

    @FXML
    public void logout(ActionEvent event) {

    }

    @FXML
    public void playStopTrack(ActionEvent event) {

    }

    @FXML
    public void shufflePlaylist(ActionEvent event) {

    }

    /*
        methods related to initializing object
     */


    public void setter(Socket clientSocket, UUID userId) {
        this.clientSocket = clientSocket;
        this.requestObject = new Request(clientSocket);
        this.userId = userId;
        try {
            this.in = new Scanner(clientSocket.getInputStream());
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Loading homePage
        FXMLLoader homePageLoader = new FXMLLoader(MainPageController.class.getResource("../HomePage/home-page.fxml"));
        try {
            // Setting putting homePage into pageHolder
            this.pageHolder.setContent(homePageLoader.load());
            // Sending goHomePageRequest
            requestObject.goHomePageReq(userId);
            // Receiving response
            String response = in.nextLine();
            JsonObject jsonResults = new Gson().fromJson(response, JsonObject.class);


        } catch (IOException io) {

        }
    }

    public ArrayList<HBox> buildLeftSidePlaylists(JsonObject jsonResults) {
        JsonArray createdPlaylistsJson = jsonResults.getAsJsonArray("createdPlaylistsResult");
        JsonArray likedPlaylistsJson = jsonResults.getAsJsonArray("likedPlaylistsResult");
        ArrayList<HBox> playlistBoxes = new ArrayList<>();
        for (JsonElement arrayItem : createdPlaylistsJson) {
            FXMLLoader playlistBoxLoader = new FXMLLoader(MainPageController.class.getResource("../Boxes/PlaylistSecondBox/playlist-second-box.fxml"));
            PlaylistSecondBoxController playlistSecondBoxController = playlistBoxLoader.getController();
            arrayItem = (JsonObject) arrayItem;
            // Fill its controller
        }
    }

    /*
        setter and getters
     */
    public MenuItem getAccountPageButton() {
        return accountPageButton;
    }

    public void setAccountPageButton(MenuItem accountPageButton) {
        this.accountPageButton = accountPageButton;
    }

    public ImageView getAddPlaylistButton() {
        return addPlaylistButton;
    }

    public void setAddPlaylistButton(ImageView addPlaylistButton) {
        this.addPlaylistButton = addPlaylistButton;
    }

    public Button getCreatePlaylistButton() {
        return createPlaylistButton;
    }

    public void setCreatePlaylistButton(Button createPlaylistButton) {
        this.createPlaylistButton = createPlaylistButton;
    }

    public Button getHomePageButton() {
        return homePageButton;
    }

    public void setHomePageButton(Button homePageButton) {
        this.homePageButton = homePageButton;
    }

    public MenuItem getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(MenuItem logoutButton) {
        this.logoutButton = logoutButton;
    }

    public Button getLyricsButton() {
        return lyricsButton;
    }

    public void setLyricsButton(Button lyricsButton) {
        this.lyricsButton = lyricsButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public void setNextButton(Button nextButton) {
        this.nextButton = nextButton;
    }

    public ImageView getNextButtonIcon() {
        return nextButtonIcon;
    }

    public void setNextButtonIcon(ImageView nextButtonIcon) {
        this.nextButtonIcon = nextButtonIcon;
    }

    public ScrollPane getPageHolder() {
        return pageHolder;
    }

    public void setPageHolder(ScrollPane pageHolder) {
        this.pageHolder = pageHolder;
    }

    public Button getPlayStopButton() {
        return playStopButton;
    }

    public void setPlayStopButton(Button playStopButton) {
        this.playStopButton = playStopButton;
    }

    public ImageView getPlayStopButtonIcon() {
        return playStopButtonIcon;
    }

    public void setPlayStopButtonIcon(ImageView playStopButtonIcon) {
        this.playStopButtonIcon = playStopButtonIcon;
    }

    public Button getPlaylistPageButton() {
        return playlistPageButton;
    }

    public void setPlaylistPageButton(Button playlistPageButton) {
        this.playlistPageButton = playlistPageButton;
    }

    public VBox getPlaylistVbox() {
        return playlistVbox;
    }

    public void setPlaylistVbox(VBox playlistVbox) {
        this.playlistVbox = playlistVbox;
    }

    public Button getPreviousButton() {
        return previousButton;
    }

    public void setPreviousButton(Button previousButton) {
        this.previousButton = previousButton;
    }

    public ImageView getPreviousButtonIcon() {
        return previousButtonIcon;
    }

    public void setPreviousButtonIcon(ImageView previousButtonIcon) {
        this.previousButtonIcon = previousButtonIcon;
    }

    public MenuItem getProfilePageButton() {
        return profilePageButton;
    }

    public void setProfilePageButton(MenuItem profilePageButton) {
        this.profilePageButton = profilePageButton;
    }

    public ImageView getProfilePictureView() {
        return profilePictureView;
    }

    public void setProfilePictureView(ImageView profilePictureView) {
        this.profilePictureView = profilePictureView;
    }

    public Button getSearchPageButton() {
        return searchPageButton;
    }

    public void setSearchPageButton(Button searchPageButton) {
        this.searchPageButton = searchPageButton;
    }

    public Button getShuffleButton() {
        return shuffleButton;
    }

    public void setShuffleButton(Button shuffleButton) {
        this.shuffleButton = shuffleButton;
    }

    public ImageView getShuffleButtonIcon() {
        return shuffleButtonIcon;
    }

    public void setShuffleButtonIcon(ImageView shuffleButtonIcon) {
        this.shuffleButtonIcon = shuffleButtonIcon;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}