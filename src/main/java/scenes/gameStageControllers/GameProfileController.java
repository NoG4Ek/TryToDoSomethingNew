package scenes.gameStageControllers;

import java.net.URL;
import java.util.ResourceBundle;

import gameLogic.DataCache;
import gameLogic.RatingHandler;
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

        TZUserName.setText(DataCache.getInstance().getUserName());
        TZEmail.setText(DataCache.getInstance().getEmail());
        TZCompletedQuests.setText(String.valueOf(DataCache.getInstance().getSetCompletedQuests().size()));
        TZPlace.setText(String.valueOf(DataCache.getInstance().placeInTop()));
        TZRating.setText(RatingHandler.getCurrentRating(DataCache.getInstance().getRating()).getName());
    }
}
