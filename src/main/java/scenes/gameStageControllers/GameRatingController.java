package scenes.gameStageControllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import dataCache.DataCache;
import database.DBHandler;
import database.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

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
        try {
            DataCache.setUserList(new DBHandler().getUsersList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<Pair<String, Integer>> top = findTopPlayers(   5);

        first.setText("1.    " + top.get(0).getKey());
        second.setText("2.    " + top.get(1).getKey());
        third.setText("3.    " + top.get(2).getKey());
        fourth.setText("4.    " + top.get(3).getKey());
        fifth.setText("5.    " + top.get(4).getKey());
    }

    @NotNull
    private List<Pair<String, Integer>> findTopPlayers(int number){
        List<Pair<String, Integer>> players = new ArrayList<>();
        for(int i = 0; i < number; i++){
            players.add(new Pair<>("", 0));
        }

        for (User user: DataCache.getUserList()) {
            for (int i = 0; i < number; i++) {
                if (user.getRating() == 0 && players.get(i).getKey().equals("")){
                    players.add(i, new Pair<>(user.getUserName(), user.getRating()));
                    players.remove(number);
                    break;
                }
                if (user.getRating() > players.get(i).getValue()) {
                    players.add(i, new Pair<>(user.getUserName(), user.getRating()));
                    players.remove(number);
                    break;
                }
            }
        }

        return players;
    }
}
