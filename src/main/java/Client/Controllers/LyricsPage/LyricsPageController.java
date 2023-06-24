package Client.Controllers.LyricsPage;

import Shared.Request;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class LyricsPageController {
    public Socket clientSocket;
    private Request requestObject;
    private Scanner in;
    @FXML
    private Text lyricsText;

    /*
        setter and getters
     */

    public Text getLyricsText() {
        return lyricsText;
    }

    public void setLyricsText(Text lyricsText) {
        this.lyricsText = lyricsText;
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
