package scenes.gameStageControllers;

import java.net.URL;
import java.util.ResourceBundle;

import gameLogic.DataCache;
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
        TWelcome.setText(String.format("Ты уже решил %s %s! Не сдавайся!",
                DataCache.getInstance().getSetCompletedQuests().size(), cases()));
    }

    private String cases(){
        String questTF = "";
        if (DataCache.getInstance().getSetCompletedQuests().size() == 1) {
            questTF = "квест";
        } else
        if (DataCache.getInstance().getSetCompletedQuests().size() < 5) {
            questTF = "квеста";
        } else
            questTF = "квестов";

        return questTF;
    }
}
