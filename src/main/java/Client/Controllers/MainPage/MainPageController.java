package Client.Controllers.MainPage;

import Client.Controllers.Boxes.BoxBuilder;
import Client.Controllers.HomePage.HomePageController;
import Client.Controllers.InjectableController;
import Client.Controllers.SearchPage.SearchPageController;
import Client.Download;
import Client.DownloadFile;
import Client.DownloadFiles;
import Shared.Request;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.*;

public class MainPageController implements InjectableController, Initializable {

    private Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    private UUID userId;
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
    private ProgressBar musicProgressBar;

    @FXML
    private HBox musicShowHbox;

    @FXML
    private Button nextButton;

    @FXML
    private ImageView nextButtonIcon;

    @FXML
    private ScrollPane pageHolder;

    @FXML
    private ImageView playStopButtonIcon;

    @FXML
    private ToggleButton playStopToggleButton;

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
    private ImageView shuffleButtonIcon;

    @FXML
    private ToggleButton shuffleToggleButton;

    @FXML
    private Slider volumeSlider;
    @FXML
    private Button playButton;
    @FXML
    private Button stopButton;

    private Timer timer;
    private TimerTask timerTask;
    private Duration playBackPosition;


    /*
        related to musicPlayer
     */
//    private HashMap<String, HBox> musics;
    private MediaPlayer musicPlayer;
    private Media currentMusic;
    //    private int currentMusicIndex;
    private boolean isPlayingMusic;

    @FXML
    void createPlaylist(ActionEvent event) {

    }

    @FXML
    void goAccountPage(ActionEvent event) {

    }

    @FXML
    void goHomePage(ActionEvent event) {

    }

    @FXML
    void goLyricsPage(ActionEvent event) {

    }

    @FXML
    void goNextTrack(ActionEvent event) {

    }

    @FXML
    void goPlaylistPage(ActionEvent event) {

    }

    @FXML
    void goPreviousTrack(ActionEvent event) {

    }

    @FXML
    void goProfilePage(ActionEvent event) {

    }


    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void play(ActionEvent event) {
        if (!isPlayingMusic) {
            beginTimer();
            musicPlayer.setStartTime(playBackPosition);
            musicPlayer.play();
            isPlayingMusic = true;
        }
    }

    @FXML
    void stop(ActionEvent event) {
        if (isPlayingMusic) {
            cancelTimer();
            playBackPosition = musicPlayer.getCurrentTime();
            musicPlayer.stop();
            isPlayingMusic = false;
        }
    }

    @FXML
    void shuffleTracks(ActionEvent event) {

    }

    @FXML
    void goSearchPage(ActionEvent event) {
        Stage currentStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader searchPageLoader = new FXMLLoader(MainPageController.class.getResource("../SearchPage/search-page.fxml"));
        try {
            this.pageHolder.setContent(searchPageLoader.load());
            SearchPageController searchPageController = searchPageLoader.getController();
            searchPageController.setter(clientSocket, this);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public void buildPages() {
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
            // Parsing JsonArrays
            JsonArray createdPlaylistsJson = jsonResults.getAsJsonArray("createdPlaylistsResult");
            JsonArray likedPlaylistsJson = jsonResults.getAsJsonArray("likedPlaylistsResult");
            JsonArray likedMusicsJson = jsonResults.getAsJsonArray("likedMusicsResult");
            JsonArray randomMusicsJson = jsonResults.getAsJsonArray("randomMusicsResult");
            // Building Arraylists
            ArrayList<InjectableController> createdPlaylists = BoxBuilder.buildPlaylistSecondBox(jsonResults, "createdPlaylistsResult", this, clientSocket);
            ArrayList<InjectableController> likedPlaylists = BoxBuilder.buildPlaylistSecondBox(jsonResults, "likedPlaylistsResult", this, clientSocket);
            ArrayList<InjectableController> suggestedMusics = BoxBuilder.buildMusicMainBox(jsonResults, "randomMusicsResult", this, clientSocket);
            ArrayList<InjectableController> likedMusics = BoxBuilder.buildMusicMainBox(jsonResults, "likedPlaylistsResult", this, clientSocket);
            // Adding playlistBoxes to mainPage
            for (InjectableController controller : createdPlaylists) {
                this.playlistVbox.getChildren().add(controller.getMainScene());
            }
            for (InjectableController controller : likedPlaylists) {
                this.playlistVbox.getChildren().add(controller.getMainScene());
            }
            // Adding randomMusics & likedMusics Boxes to homePage
            for (InjectableController controller : suggestedMusics) {
                homePageController.getSuggestedMusicsHbox().getChildren().add(controller.getMainScene());
            }
            for (InjectableController controller : likedMusics) {
                homePageController.getLikedMusicsHbox().getChildren().add(controller.getMainScene());
            }

            // Building jsonArrays
            // JsonArray jsonArrays = new JsonArray();
            // jsonArrays.add(createdPlaylistsJson);
            // jsonArrays.add(likedPlaylistsJson);
            // jsonArrays.add(randomMusicsJson);
            // jsonArrays.add(likedMusicsJson);
            // Building controllerArrays
            // Assigning thread to download profilePictures
            DownloadFiles createdPlaylistsDownload = new DownloadFiles(createdPlaylistsJson, createdPlaylists, "profilePath", clientSocket);
            DownloadFiles likedPlaylistsDownload = new DownloadFiles(likedPlaylistsJson, likedPlaylists, "profilePath", clientSocket);
            DownloadFiles suggestedMusicsDownload = new DownloadFiles(randomMusicsJson, suggestedMusics, "profilePath", clientSocket);
            DownloadFiles likedMusicsDownload = new DownloadFiles(likedMusicsJson, likedMusics, "profilePath", clientSocket);


            ArrayList<DownloadFile> downloadFile = new ArrayList<DownloadFile>();
            ArrayList<DownloadFiles> downloadFiles = new ArrayList<DownloadFiles>();
            downloadFiles.add(createdPlaylistsDownload);
            downloadFiles.add(likedPlaylistsDownload);
            downloadFiles.add(suggestedMusicsDownload);
            downloadFiles.add(likedMusicsDownload);
            Download download = new Download(downloadFile, downloadFiles);
            Thread thread = new Thread(download);
            // Starting thread
            thread.start();

        } catch (IOException io) {
            io.printStackTrace();
        }
    }


    /*
        setter and getters
     */
    public void switchPage(Node page) {
        this.pageHolder.setContent(page);
    }

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

    public ToggleButton getPlayStopToggleButton() {
        return playStopToggleButton;
    }

    public void setPlayStopToggleButton(ToggleButton playStopToggleButton) {
        this.playStopToggleButton = playStopToggleButton;
    }

    @Override
    public void setControllerProfilePic(Image profilePic) {
        this.profilePictureView.setImage(profilePic);
    }

    @Override
    public Node getMainScene() {
        return null;
    }

    public ProgressBar getMusicProgressBar() {
        return musicProgressBar;
    }

    public void setMusicProgressBar(ProgressBar musicProgressBar) {
        this.musicProgressBar = musicProgressBar;
    }

    public HBox getMusicShowHbox() {
        return musicShowHbox;
    }

    public void setMusicShowHbox(HBox musicShowHbox) {
        this.musicShowHbox = musicShowHbox;
    }

    public ToggleButton getShuffleToggleButton() {
        return shuffleToggleButton;
    }

    public void setShuffleToggleButton(ToggleButton shuffleToggleButton) {
        this.shuffleToggleButton = shuffleToggleButton;
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public void setVolumeSlider(Slider volumeSlider) {
        this.volumeSlider = volumeSlider;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public void setPlayButton(Button playButton) {
        this.playButton = playButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public void setStopButton(Button stopButton) {
        this.stopButton = stopButton;
    }

    public MediaPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public void setMusicPlayer(MediaPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    public Media getCurrentMusic() {
        return currentMusic;
    }

    public void setCurrentMusic(Media currentMusic) {
        this.currentMusic = currentMusic;
    }

    public boolean isPlayingMusic() {
        return isPlayingMusic;
    }

    public void setPlayingMusic(boolean playingMusic) {
        isPlayingMusic = playingMusic;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.musics = new HashMap<>();
//        this.currentMusicIndex = 0;
        this.isPlayingMusic = false;
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                musicPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });
    }

    public void beginTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                double current = musicPlayer.getCurrentTime().toSeconds();
                double end = currentMusic.getDuration().toSeconds();
                musicProgressBar.setProgress(current / end);

                if (current / end == 1) {
                    cancelTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }

    public void cancelTimer() {
        timer.cancel();
    }

    /*
        related to music player
     */

    public void setTrackForMusicPlayer(String fileName) {
        if (isPlayingMusic) {
            musicPlayer.stop();
        }
        File file = new File("src/main/java/Client/Downloads/files/track_file/" + fileName + ".mp3");
        this.currentMusic = new Media(file.toURI().toString());
        this.musicPlayer = new MediaPlayer(currentMusic);
    }
}