package dataCache;

import database.Quest;
import database.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataCache {
    private static String firstName;
    private static String lastName;
    private static String userName;
    private static String email;
    private static String password;
    private static int rating;
    private static String completedQuests;
    private static List<User> userList;

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
        DataCache.rating = rating;
    }

    public static String getCompletedQuests() {
        return completedQuests;
    }

    public static Set<String> getSetCompletedQuests(){
        return new HashSet<>(Arrays.asList(completedQuests.split(",")));
    }

    public static void setCompletedQuests(String completedQuests) {
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
