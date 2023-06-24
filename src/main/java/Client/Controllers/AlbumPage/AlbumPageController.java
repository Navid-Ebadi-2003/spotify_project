package Client.Controllers.AlbumPage;

import Shared.Request;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class AlbumPageController{
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    @FXML
    private ImageView albumPicture;

    @FXML
    private Label albumTitle;

    @FXML
    private ScrollPane bottomScrollPane;

    @FXML
    private VBox tracksVbox;

    /*
        setter and getters
     */

    public ImageView getAlbumPicture() {
        return albumPicture;
    }

    public void setAlbumPicture(ImageView albumPicture) {
        this.albumPicture = albumPicture;
    }

    public Label getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(Label albumTitle) {
        this.albumTitle = albumTitle;
    }

    public ScrollPane getBottomScrollPane() {
        return bottomScrollPane;
    }

    public void setBottomScrollPane(ScrollPane bottomScrollPane) {
        this.bottomScrollPane = bottomScrollPane;
    }

    public VBox getTracksVbox() {
        return tracksVbox;
    }

    public void setTracksVbox(VBox tracksVbox) {
        this.tracksVbox = tracksVbox;
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
