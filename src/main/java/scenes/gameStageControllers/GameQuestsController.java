package scenes.gameStageControllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Delayed;

import dataCache.DataCache;
import database.DBHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import objects.Quest;
import gameLogic.Logic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.log4j.Logger;
import javafx.animation.*;
import scenes.GameController;

public class GameQuestsController {
    static Logger logger = Logger.getLogger(GameQuestsController.class.getName());

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
    private volatile AnchorPane questAlertPane;

    @FXML
    private Text tQuestAlert;

    private boolean bAnimateAlert = false;

    @FXML
    void initialize() {
        questAlertPane.setVisible(false);

        Quest activeQuest = Logic.findActiveQuest();

        TQuestName.setText(activeQuest.getQuestName());
        TQuestDescription.setText(activeQuest.getDescription());

        codeButton.setOnAction(event -> {
            if (codeField.getText().equals(activeQuest.getCode())) {
                if (DataCache.getSetCompletedQuests().contains(activeQuest.getQuestName())) {
                    if(!bAnimateAlert) {
                        System.out.println("Квест уже выполнен");
                        animateAlert(false);
                    }
                } else {
                    new DBHandler().addCompletedQuest(activeQuest.getQuestName(), activeQuest.getCost());

                    HashSet<String> updateSetCompletedQuests = new HashSet<>(DataCache.getSetCompletedQuests());
                    updateSetCompletedQuests.add(activeQuest.getQuestName());
                    DataCache.setCompletedQuests(updateSetCompletedQuests);
                    DataCache.setRating(DataCache.getRating() + activeQuest.getCost());

                    if(!bAnimateAlert) {
                        System.out.println("Поздравляем! Квест выполнен");
                        animateAlert(true);
                    }

                }
            }
        });

    }

    private void animateAlert(boolean vista) {
        bAnimateAlert = true;
        if (vista) {
            tQuestAlert.setText("Поздравляем! Квест выполнен!");
        } else {
            tQuestAlert.setText("Квест уже выполнен");
        }

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.2), questAlertPane);
        fadeOut.setFromValue(0.0);
        fadeOut.setToValue(1.0);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), questAlertPane);
        fadeIn.setFromValue(1.0);
        fadeIn.setToValue(0.0);
        fadeIn.setDelay(Duration.seconds(3));

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(fadeOut, fadeIn);
        questAlertPane.setVisible(true);
        sequentialTransition.play();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                bAnimateAlert = false;
            }
        }, 5200);
    }
}
