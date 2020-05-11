package scenes.gameStageControllers;

import java.net.URL;
import java.util.*;

import dataCache.DataCache;
import database.DBHandler;
import gameLogic.Logic;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.util.Pair;

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
        DataCache.setUserList(new DBHandler().getUsersList());

        List<Pair<String, Integer>> top = Logic.findTopPlayers(5);

        first.setText("1.    " + top.get(0).getKey());
        second.setText("2.    " + top.get(1).getKey());
        third.setText("3.    " + top.get(2).getKey());
        fourth.setText("4.    " + top.get(3).getKey());
        fifth.setText("5.    " + top.get(4).getKey());
    }
}
