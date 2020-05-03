package scenes.gameStageControllers;

import java.awt.geom.QuadCurve2D;
import java.net.URL;
import java.util.ResourceBundle;

import dataCache.DataCache;
import database.DBHandler;
import database.Quest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javax.xml.crypto.Data;

public class GameQuestsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label TQuestName;

    @FXML
    private TextField codeField;

    @FXML
    private Button codeButton;

    @FXML
    private Text TQuestDescription;

    @FXML
    void initialize() {
        Quest activeQuest = findActiveQuest();

        TQuestName.setText(activeQuest.getQuestName());
        TQuestDescription.setText(activeQuest.getDescription());

        codeButton.setOnAction(event -> {
            if (codeField.getText().equals(activeQuest.getCode())){
                if (DataCache.getSetCompletedQuests().contains(activeQuest.getQuestName())) {
                    System.out.println("Квест уже выполнен");
                } else {
                    new DBHandler().addCompletedQuest(activeQuest.getQuestName());
                    DataCache.setCompletedQuests(DataCache.getCompletedQuests() + "," + activeQuest.getQuestName());
                    System.out.println("Поздравляем! Квест выполнен");
                }
            }
        });

    }

    private Quest findActiveQuest(){
        Quest activeQuest = Quest.NO_ACTIVE;
        for (Quest quest: DataCache.getQuestList()){
            if (quest.getMark().equals("active")){
                return quest;
            }
        }
        return activeQuest;
    }
}
