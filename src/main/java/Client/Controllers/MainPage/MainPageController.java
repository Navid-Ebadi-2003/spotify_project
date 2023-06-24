package Client.Controllers.MainPage;
import Client.Controllers.Boxes.BoxBuilder;
import Client.Controllers.HomePage.HomePageController;
import Client.Controllers.InjectableController;
import Client.DownloadFiles;
import Shared.Request;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class MainPageController  {

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
    public void shuffleTracks(ActionEvent event) {

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

    public void buildPages(){
        // Loading homePage
        FXMLLoader homePageLoader = new FXMLLoader(MainPageController.class.getResource("../HomePage/home-page.fxml"));
        try {
            this.pageHolder.setContent(homePageLoader.load());
            HomePageController homePageController = homePageLoader.getController();
            // Setting putting homePage into pageHolder
            // Sending goHomePageRequest
            requestObject.goHomePageReq(userId);
            // Receiving response
            String response = in.nextLine();
            JsonObject jsonTemplate = new Gson().fromJson(response, JsonObject.class);
            JsonObject responseBody = jsonTemplate.getAsJsonObject("responseBody");
            JsonObject jsonResults = responseBody.getAsJsonObject("results");
            System.out.println(jsonResults);
            // Parsing JsonArrays
            JsonArray createdPlaylistsJson = jsonResults.getAsJsonArray("createdPlaylistsResult");
            JsonArray likedPlaylistsJson = jsonResults.getAsJsonArray("likedPlaylistsResult");
            JsonArray likedMusicsJson = jsonResults.getAsJsonArray("likedMusicsResult");
            JsonArray randomMusicsJson = jsonResults.getAsJsonArray("randomMusicsResult");
            System.out.println(createdPlaylistsJson);
            System.out.println(likedPlaylistsJson);
            System.out.println(likedMusicsJson);
            System.out.println(randomMusicsJson);
            // Building Hashmaps
            HashMap<HBox, InjectableController> createdPlaylists = BoxBuilder.buildPlaylistSecondBox(jsonResults, "createdPlaylistsResult");
            HashMap<HBox, InjectableController> likedPlaylists = BoxBuilder.buildPlaylistSecondBox(jsonResults, "likedPlaylistsResult");
            HashMap<VBox, InjectableController> suggestedMusics = BoxBuilder.buildMusicMainBox(jsonResults, "randomMusicsResult");
            HashMap<VBox, InjectableController> likedMusics = BoxBuilder.buildMusicMainBox(jsonResults, "likedPlaylistsResult");
            // Adding playlistBoxes to mainPage
            playlistVbox.getChildren().addAll(createdPlaylists.keySet());
            playlistVbox.getChildren().addAll(likedPlaylists.keySet());
            // Adding randomMusics & likedMusics Boxes to homePage
            homePageController.getSuggestedMusicsHbox().getChildren().addAll(suggestedMusics.keySet());
            homePageController.getLikedMusicsHbox().getChildren().addAll(likedMusics.keySet());
            // Building jsonArrays
            JsonArray jsonArrays = new JsonArray();
            jsonArrays.add(createdPlaylistsJson);
            jsonArrays.add(likedPlaylistsJson);
            jsonArrays.add(randomMusicsJson);
            jsonArrays.add(likedMusicsJson);
            // Building controllerArrays
            List<List<InjectableController>> controllerArrays = new ArrayList<>();
            controllerArrays.add(createdPlaylists.values().stream().toList());
            controllerArrays.add(likedPlaylists.values().stream().toList());
            controllerArrays.add(suggestedMusics.values().stream().toList());
            controllerArrays.add(likedMusics.values().stream().toList());
            // Assigning thread to download profilePictures
            DownloadFiles downloadFilesTask = new DownloadFiles(jsonArrays, controllerArrays, "profilePath", clientSocket);
            Thread thread_0 = new Thread(downloadFilesTask);
            // Starting thread
            thread_0.start();

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        // Loading homePage
//        FXMLLoader homePageLoader = new FXMLLoader(MainPageController.class.getResource("../HomePage/home-page.fxml"));
//        try {
//            homePageLoader.load();
//            HomePageController homePageController = homePageLoader.getController();
//            // Setting putting homePage into pageHolder
//            this.pageHolder.setContent(homePageLoader.load());
//            // Sending goHomePageRequest
//            requestObject.goHomePageReq(userId);
//            // Receiving response
//            String response = in.nextLine();
//            JsonObject jsonResults = new Gson().fromJson(response, JsonObject.class);
//            // Parsing JsonArrays
//            JsonArray createdPlaylistsJson = jsonResults.getAsJsonArray("createdPlaylistsResult");
//            JsonArray likedPlaylistsJson = jsonResults.getAsJsonArray("likedPlaylistsResult");
//            JsonArray likedMusicsJson = jsonResults.getAsJsonArray("likedMusicsResult");
//            JsonArray randomMusicsJson = jsonResults.getAsJsonArray("randomMusicsResult");
//            // Building Hashmaps
//            HashMap<HBox, InjectableController> createdPlaylists = BoxBuilder.buildPlaylistSecondBox(jsonResults, "createdPlaylistsResult");
//            HashMap<HBox, InjectableController> likedPlaylists = BoxBuilder.buildPlaylistSecondBox(jsonResults, "likedPlaylistsResult");
//            HashMap<VBox, InjectableController> suggestedMusics = BoxBuilder.buildMusicMainBox(jsonResults, "randomMusicsResult");
//            HashMap<VBox, InjectableController> likedMusics = BoxBuilder.buildMusicMainBox(jsonResults, "likedPlaylistsResult");
//            // Adding playlistBoxes to mainPage
//            playlistVbox.getChildren().addAll(createdPlaylists.keySet());
//            playlistVbox.getChildren().addAll(likedPlaylists.keySet());
//            // Adding randomMusics & likedMusics Boxes to homePage
//            homePageController.getSuggestedMusicsHbox().getChildren().addAll(suggestedMusics.keySet());
//            homePageController.getLikedMusicsHbox().getChildren().addAll(likedMusics.keySet());
//            // Assigning thread to download profilePictures
//            DownloadFiles downCreatedPlaylistsTask = new DownloadFiles(createdPlaylistsJson, new ArrayList<>(createdPlaylists.values()), "profilePath", clientSocket);
//            DownloadFiles downLikedPlaylistsTask = new DownloadFiles(likedPlaylistsJson, new ArrayList<>(likedPlaylists.values()), "profilePath", clientSocket);
//            DownloadFiles downSuggestedMusicsTask = new DownloadFiles(randomMusicsJson, new ArrayList<>(suggestedMusics.values()), "profilePath", clientSocket);
//            DownloadFiles downLikedMusicsTask = new DownloadFiles(likedMusicsJson, new ArrayList<>(likedMusics.values()), "profilePath", clientSocket);
//            // Assigning threads
//            Thread thread_0 = new Thread(downCreatedPlaylistsTask);
//            Thread thread_1 = new Thread(downLikedPlaylistsTask);
//            Thread thread_2 = new Thread(downSuggestedMusicsTask);
//            Thread thread_3 = new Thread(downLikedMusicsTask);
//            // Running Threads
//            thread_0.start();
//            thread_1.start();
//            thread_2.start();
//            thread_3.start();
//
//        } catch (IOException io) {
//
//        }
//    }







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