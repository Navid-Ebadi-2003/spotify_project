package Client.Controllers.HomePage;

import Shared.Request;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class HomePageController {
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    @FXML
    private AnchorPane homePageAnchorPane;

    @FXML
    private HBox likedMusicsHbox;

    @FXML
    private HBox suggestedMusicsHbox;
    /*
        setter and getters
     */

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

    public void setter(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.requestObject = new Request(this.clientSocket);
        try {
            this.in = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
