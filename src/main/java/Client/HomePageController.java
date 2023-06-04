package Client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitMenuButton;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private SplitMenuButton splitMenuButton;


    //TODO
    @FXML
    public void onMouseEntered(){
        splitMenuButton.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        splitMenuButton.setOnMouseEntered(event -> {
            // Actions to perform when the mouse enters the SplitMenuButton
            splitMenuButton.show();
        });
    }
}
