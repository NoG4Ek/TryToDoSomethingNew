package dataCache;

import objects.Quest;
import gameLogic.RatingHandler;
import objects.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Set;


/**
 * A class for caching data from a data base and for storing property
 * All functions are static for convenience
 */
public class DataCache {
    private static String firstName;
    private static String lastName;
    private static String userName;
    private static String email;
    private static String password;
    private static int rating;
    private static Set<String> completedQuests;
    private static List<User> userList;

    private static final SimpleDoubleProperty progress = new SimpleDoubleProperty(rating);
    private static final ObjectProperty<Image> ratingImage = new SimpleObjectProperty<>();

    public static SimpleDoubleProperty getProgressProperty() {
        return progress;
    }

    public static void setProgressProperty(double ratingPr) {
        DataCache.progress.set(ratingPr);
    }

    public static ObjectProperty<Image> getRatingImageProperty() {
        return ratingImage;
    }

    public static void setRatingImageProperty(String ratingImage) {
        DataCache.ratingImage.set(new Image(ratingImage));
    }

    private static List<Quest> questList;

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        DataCache.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        DataCache.lastName = lastName;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        DataCache.userName = userName;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        DataCache.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DataCache.password = password;
    }

    public static int getRating() {
        return rating;
    }

    public static void setRating(int rating) {
        setRatingImageProperty(RatingHandler.getCurrentRating(rating).getUrl());
        setProgressProperty(RatingHandler.getProgress(rating));
        DataCache.rating = rating;
    }

    public static Set<String> getSetCompletedQuests(){
        return completedQuests;
    }

    public static void setCompletedQuests(Set<String> completedQuests) {
        DataCache.completedQuests = completedQuests;
    }

    public static List<User> getUserList() {
        return userList;
    }

    public static void setUserList(List<User> userList) {
        DataCache.userList = userList;
    }

    public static List<Quest> getQuestList() {
        return questList;
    }

    public static void setQuestList(List<Quest> questList) {
        DataCache.questList = questList;
    }
}
