package Client.Controllers.LyricsPage;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class LyricsPageController {
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
}
