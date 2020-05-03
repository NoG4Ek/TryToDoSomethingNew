package scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import dataCache.DataCache;
import database.DBHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;

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
    private Polygon animHex;

    @FXML
    private AnchorPane swappable;

    @FXML
    private ImageView ratingImage;

    @FXML
    void initialize() throws IOException {
        try {
            DataCache.setUserList(new DBHandler().getUsersList());
            DataCache.setQuestList(new DBHandler().getQuestsList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
        });

        messages.setOnAction(event -> {
            try {
                swappable.getChildren().setAll(((Node)FXMLLoader.load(Objects.requireNonNull(uMessages))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        quests.setOnAction(event -> {
            try {
                swappable.getChildren().setAll(((Node)FXMLLoader.load(Objects.requireNonNull(uQuests))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        rating.setOnAction(event -> {
            try {
                swappable.getChildren().setAll(((Node)FXMLLoader.load(Objects.requireNonNull(uRating))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
