package gameLogic;

import database.DatabaseConnection;
import javafx.util.Pair;
import objects.Quest;
import objects.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * A class for caching data from a data base and for storing property
 * Functions of gameLogic
 */
public class DataCache {
    private static DataCache instance;

    private static String firstName;
    private static String lastName;
    private static String userName;
    private static String email;
    private static String password;
    private static int rating;
    private static Set<String> completedQuests;
    private static List<User> userList;
    private static List<Quest> questList;

    private static final SimpleDoubleProperty progress = new SimpleDoubleProperty(rating);
    private static final ObjectProperty<Image> ratingImage = new SimpleObjectProperty<>();

    public static DataCache getInstance() {
        if (instance == null) {
            instance = new DataCache();
        }
        return instance;
    }

    public SimpleDoubleProperty getProgressProperty() {
        return progress;
    }

    public void setProgressProperty(double ratingPr) {
        DataCache.progress.set(ratingPr);
    }

    public ObjectProperty<Image> getRatingImageProperty() {
        return ratingImage;
    }

    public void setRatingImageProperty(String ratingImage) {
        DataCache.ratingImage.set(new Image(ratingImage));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        DataCache.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        DataCache.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        DataCache.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        DataCache.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        DataCache.password = password;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        setRatingImageProperty(RatingHandler.getCurrentRating(rating).getUrl());
        setProgressProperty(RatingHandler.getProgress(rating));
        DataCache.rating = rating;
    }

    public Set<String> getSetCompletedQuests(){
        return completedQuests;
    }

    public void setCompletedQuests(Set<String> completedQuests) {
        DataCache.completedQuests = completedQuests;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        DataCache.userList = userList;
    }

    public List<Quest> getQuestList() {
        return questList;
    }

    public void setQuestList(List<Quest> questList) {
        DataCache.questList = questList;
    }

    /**
     * Find the leaders of the rating table in quantity
     *
     * @param number
     */
    public List<Pair<String, Integer>> findTopPlayers(int number){
        List<Pair<String, Integer>> players = new ArrayList<>();
        for(int i = 0; i < number; i++){
            players.add(new Pair<>("", 0));
        }

        for (User user: userList) {
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
    public Quest findActiveQuest(){
        Quest activeQuest = Quest.NO_ACTIVE;
        for (Quest quest: questList){
            if (quest.getMark().equals("active")){
                return quest;
            }
        }
        return activeQuest;
    }

    /**
     * Calculate place in top of current player
     */
    public int placeInTop(){
        List<User> players = new ArrayList<>(userList);
        players.sort((u1, u2) -> u2.getRating() - u1.getRating());

        int place = 1;
        for (User player : players) {
            if (player.getUserName().equals(userName)) {
                return place;
            } else {
                place++;
            }
        }
        return place;
    }
}
