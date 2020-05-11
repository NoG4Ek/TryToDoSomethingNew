package scenes.gameStageControllers;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import dataCache.DataCache;
import database.DBHandler;
import objects.Quest;
import gameLogic.Logic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
        Quest activeQuest = Logic.findActiveQuest();

        TQuestName.setText(activeQuest.getQuestName());
        TQuestDescription.setText(activeQuest.getDescription());

        codeButton.setOnAction(event -> {
            if (codeField.getText().equals(activeQuest.getCode())){
                if (DataCache.getSetCompletedQuests().contains(activeQuest.getQuestName())) {
                    System.out.println("Квест уже выполнен");
                } else {
                    new DBHandler().addCompletedQuest(activeQuest.getQuestName(), activeQuest.getCost());

                    HashSet<String> updateSetCompletedQuests = new HashSet<>(DataCache.getSetCompletedQuests());
                    updateSetCompletedQuests.add(activeQuest.getQuestName());
                    DataCache.setCompletedQuests(updateSetCompletedQuests);
                    DataCache.setRating(DataCache.getRating() + activeQuest.getCost());

                    System.out.println("Поздравляем! Квест выполнен");
                }
            }
        });

    }
}
