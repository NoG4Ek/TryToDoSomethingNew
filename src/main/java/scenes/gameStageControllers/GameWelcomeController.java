package scenes.gameStageControllers;

import java.net.URL;
import java.util.ResourceBundle;

import dataCache.DataCache;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GameWelcomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text TWelcome;

    @FXML
    void initialize() {
        if (DataCache.getSetCompletedQuests().size() == 1) {
            TWelcome.setText("Ты уже решил " + DataCache.getSetCompletedQuests().size() + " квест!" +
                    " Не сдавайся!");
        } else
            if (DataCache.getSetCompletedQuests().size() < 5) {
                TWelcome.setText("Ты уже решил " + DataCache.getSetCompletedQuests().size() + " квеста!" +
                        " Не сдавайся!");
            } else
                TWelcome.setText("Ты уже решил " + DataCache.getSetCompletedQuests().size() + " квестов!" +
                        " Не сдавайся!");
    }
}
