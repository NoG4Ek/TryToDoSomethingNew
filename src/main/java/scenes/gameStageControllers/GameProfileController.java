package scenes.gameStageControllers;

import java.net.URL;
import java.util.ResourceBundle;

import dataCache.DataCache;
import gameLogic.RatingHandler;
import gameLogic.Logic;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GameProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text TUserName;

    @FXML
    private Text TEmail;

    @FXML
    private Text TCompletedQuests;

    @FXML
    private Text TPlace;

    @FXML
    private Text TRating;

    @FXML
    private Text TZUserName;

    @FXML
    private Text TZEmail;

    @FXML
    private Text TZCompletedQuests;

    @FXML
    private Text TZPlace;

    @FXML
    private Text TZRating;

    @FXML
    void initialize() {
        TUserName.setText("Имя:");
        TEmail.setText("Почта:   ");
        TCompletedQuests.setText("Завершенные квесты:    ");
        TPlace.setText("Место в топе:    ");
        TRating.setText("Рейтинг:    ");

        TZUserName.setText(DataCache.getUserName());
        TZEmail.setText(DataCache.getEmail());
        TZCompletedQuests.setText(String.valueOf(DataCache.getSetCompletedQuests().size()));
        TZPlace.setText(String.valueOf(Logic.placeInTop()));
        TZRating.setText(RatingHandler.getCurrentRating(DataCache.getRating()).getName());
    }
}
