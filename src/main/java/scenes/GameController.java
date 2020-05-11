package scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import dataCache.DataCache;
import database.DBHandler;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class GameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton messages;

    @FXML
    private JFXButton quests;

    @FXML
    private JFXButton rating;

    @FXML
    private JFXButton profile;

    @FXML
    private ImageView settings;

    @FXML
    protected JFXProgressBar progressBar;

    @FXML
    private Polygon animHex;

    @FXML
    private AnchorPane swappable;

    @FXML
    private ImageView ratingImage;

    @FXML
    void initialize() throws IOException {
        updateDataFromBase();

        animHexStart();

        ratingImage.imageProperty().bind(DataCache.getRatingImageProperty());
        progressBar.progressProperty().bind(DataCache.getProgressProperty());

        final JFXButton[] activeButton = {profile};

        URL uWelcome = this.getClass().getClassLoader().getResource("FXML/gameWelcome.fxml");
        URL uProfile = this.getClass().getClassLoader().getResource("FXML/gameProfile.fxml");
        URL uMessages = this.getClass().getClassLoader().getResource("FXML/gameMessages.fxml");
        URL uQuests = this.getClass().getClassLoader().getResource("FXML/gameQuests.fxml");
        URL uRating = this.getClass().getClassLoader().getResource("FXML/gameRating.fxml");

        swappable.getChildren().setAll(((Node)FXMLLoader.load((Objects.requireNonNull(uWelcome)))));

        profile.setOnAction(event -> {
            try {
                swappable.getChildren().setAll(((Node)FXMLLoader.load(Objects.requireNonNull(uProfile))));
            } catch (IOException e) {
                e.printStackTrace();
            }

            activeButton[0].getStyleClass().set(2, "stageButton");
            profile.getStyleClass().set(2, "stageButtonPressed");
            activeButton[0] = profile;
        });

        messages.setOnAction(event -> {
            try {
                swappable.getChildren().setAll(((Node)FXMLLoader.load(Objects.requireNonNull(uMessages))));
            } catch (IOException e) {
                e.printStackTrace();
            }

            activeButton[0].getStyleClass().set(2, "stageButton");
            messages.getStyleClass().set(2, "stageButtonPressed");
            activeButton[0] = messages;
        });

        quests.setOnAction(event -> {
            try {
                swappable.getChildren().setAll(((Node)FXMLLoader.load(Objects.requireNonNull(uQuests))));
            } catch (IOException e) {
                e.printStackTrace();
            }

            activeButton[0].getStyleClass().set(2, "stageButton");
            quests.getStyleClass().set(2, "stageButtonPressed");
            activeButton[0] = quests;
        });

        rating.setOnAction(event -> {
            try {
                swappable.getChildren().setAll(((Node)FXMLLoader.load(Objects.requireNonNull(uRating))));
            } catch (IOException e) {
                e.printStackTrace();
            }

            activeButton[0].getStyleClass().set(2, "stageButton");
            rating.getStyleClass().set(2, "stageButtonPressed");
            activeButton[0] = rating;
        });

        settings.setOnMousePressed(event -> {
            System.out.println("Настроечки");
            settings.setImage(new Image("./skin/menu_pressed/Settings_pressed.png"));
        });

    }

    private void updateDataFromBase(){
        DataCache.setUserList(new DBHandler().getUsersList());
        DataCache.setQuestList(new DBHandler().getQuestsList());
    }

    private void animHexStart() {

        RotateTransition rotate = new RotateTransition(Duration.seconds(5), animHex);
        rotate.setByAngle(-360f);
        rotate.setCycleCount(Timeline.INDEFINITE);
        rotate.play();
    }
}
