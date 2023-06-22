package Client.Controllers.MainPage;
import Shared.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
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
        } catch (IOException io){
            io.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader homePageLoader = new FXMLLoader(MainPageController.class.getResource("../HomePage/home-page.fxml"));
        try {
            this.pageHolder.setContent(homePageLoader.load());

        } catch (IOException io){

        }
    }
}