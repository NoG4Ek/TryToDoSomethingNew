package scenes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GameRatingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text first;

    @FXML
    private Text second;

    @FXML
    private Text third;

    @FXML
    private Text fourth;

    @FXML
    private Text fifth;

    @FXML
    void initialize() {
        first.setText("1.  NoGe4Ek");
        second.setText("2.  NoGe4Ek");
        third.setText("3.  NoGe4Ek");
        fourth.setText("4.  NoGe4Ek");
        fifth.setText("5.  NoGe4Ek");
    }
}
