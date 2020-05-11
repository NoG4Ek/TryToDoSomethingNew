package gameLogic;

import dataCache.DataCache;
import objects.Quest;
import objects.User;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Main application logic
 */
public class Logic {


    /**
     * Find the leaders of the rating table in quantity
     *
     * @param number
     */
    public static List<Pair<String, Integer>> findTopPlayers(int number){
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


    /**
     * Find active quest
     */
    public static Quest findActiveQuest(){
        Quest activeQuest = Quest.NO_ACTIVE;
        for (Quest quest: DataCache.getQuestList()){
            if (quest.getMark().equals("active")){
                return quest;
            }
        }
        return activeQuest;
    }


    /**
     * Calculate place in top of current player
     */
    public static int placeInTop(){
        List<User> players = new ArrayList<>(DataCache.getUserList());
        players.sort((u1, u2) -> u2.getRating() - u1.getRating());

        int place = 1;
        for (User player : players) {
            if (player.getUserName().equals(DataCache.getUserName())) {
                return place;
            } else {
                place++;
            }
        }
        return place;
    }
}
