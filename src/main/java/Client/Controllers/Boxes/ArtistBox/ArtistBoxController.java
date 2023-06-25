package Client.Controllers.Boxes.ArtistBox;

import Client.Controllers.InjectableController;
import Client.Controllers.MainPage.MainPageController;
import Shared.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class ArtistBoxController implements InjectableController {
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    private MainPageController mainPageController;
    @FXML
    private Hyperlink artistHyperLink;

    @FXML
    private ImageView artistPicture;
    private UUID artistId;

    @FXML
    private VBox artistBox;

    @FXML
    void goToArtistPage(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public Hyperlink getArtistHyperLink() {
        return artistHyperLink;
    }

    public void setArtistHyperLink(String artistName) {
        this.artistHyperLink.setText(artistName);
    }

    public ImageView getArtistPicture() {
        return artistPicture;
    }

    public void setArtistPicture(ImageView artistPicture) {
        this.artistPicture = artistPicture;
    }

    public UUID getArtistId() {
        return artistId;
    }

    public void setArtistId(UUID artistId) {
        this.artistId = artistId;
    }


    @Override
    public void setControllerProfilePic(Image artistPicture) {
        this.artistPicture.setImage(artistPicture);
    }

    @Override
    public Node getMainScene() {
        return this.artistBox;
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
