package database;

import dataCache.DataCache;
import objects.Quest;
import objects.User;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.*;
import java.util.*;

/**
 * Working with database connection class
 */
public class  DBHandler extends Configs {
    static Logger logger = Logger.getLogger(DBHandler.class.getName());

    private Set<String> jsonGetSetCompletedQuests(ResultSet resSet) {
        Object obj = null;
        try {
            obj = new JSONParser().parse(resSet.getString(Const.USERS_COMPLETEDQUESTS));
        } catch (ParseException | SQLException e) {
            logger.info("Parse exception of json completedQuests");
        }
        JSONArray objArray = (JSONArray) obj;
        Set<String> setCompletedQuests = new HashSet<>();
        for (Object object: objArray){
            setCompletedQuests.add(String.valueOf(object));
        }
        return setCompletedQuests;
    }

    /**
     * Sign up new user in database
     *
     * @param user - new user
     */
    public void signUpUser(@NotNull User user) {
        String insert = "INSERT INTO " + Const.USERS_TABLE + "(" + Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME
                + "," + Const.USERS_USERNAME + "," + Const.USERS_EMAIL + "," + Const.USERS_PASSWORD + "," +
                Const.USERS_COMPLETEDQUESTS + ")"
                + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = DatabaseConnection.getInstance().getConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getEmail());
            prSt.setString(5, user.getPassword());

            JSONArray startCompletedQuests = new JSONArray();
            startCompletedQuests.add("Начало");
            prSt.setString(6, startCompletedQuests.toString());

            prSt.executeUpdate();
            DatabaseConnection.getInstance().closeConnection();
        } catch (SQLException e) {
            logger.info("Problems with user registration request");
        }
    }


    /**
     * Find user in database
     *
     * @param user - user to find
     * @return "fullInfo" user or null
     */
    public User getUser(@NotNull User user) {
        ResultSet resSet = null;
        User currentUser = null;

        String select = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = DatabaseConnection.getInstance().getConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();

            while (resSet.next()){
                currentUser = new User(resSet.getString(Const.USERS_FIRSTNAME),
                        resSet.getString(Const.USERS_LASTNAME),
                        resSet.getString(Const.USERS_USERNAME),
                        resSet.getString(Const.USERS_EMAIL),
                        resSet.getString(Const.USERS_PASSWORD),
                        Integer.parseInt(resSet.getString(Const.USERS_RATING)),
                        jsonGetSetCompletedQuests(resSet));
            }

            DatabaseConnection.getInstance().closeConnection();
        } catch (SQLException e) {
            logger.info("Problems with user sign in request");
        }
        return currentUser;
    }

    /**
     * Get a list of all users
     *
     * @return List<User>
     * @throws SQLException - exception of get new user or regular field of database
     */
    public List<User> getUsersList() {
        ResultSet resSet = null;
        List<User> userList = new ArrayList<>();

        String select = "SELECT * FROM " + Const.USERS_TABLE;

        try {
            PreparedStatement prSt = DatabaseConnection.getInstance().getConnection().prepareStatement(select);

            resSet = prSt.executeQuery();

            while (Objects.requireNonNull(resSet).next()){
                userList.add(new User(resSet.getString(Const.USERS_FIRSTNAME), resSet.getString(Const.USERS_LASTNAME),
                        resSet.getString(Const.USERS_USERNAME), resSet.getString(Const.USERS_EMAIL),
                        resSet.getString(Const.USERS_PASSWORD), Integer.parseInt(resSet.getString(Const.USERS_RATING)),
                        jsonGetSetCompletedQuests(resSet)));
            }

            DatabaseConnection.getInstance().closeConnection();
        } catch (SQLException e) {
            logger.info("Problems with get user list request");
        }

        return userList;
    }

    /**
     * Executed when the user completed the quest
     * Add info in database
     *
     * @param questName - name of completed quest
     * @param questCost - cost of completed quest
     */
    public void addCompletedQuest(String questName, int questCost){
        String select = "UPDATE " + Const.USERS_TABLE + " SET " +
                Const.USERS_RATING + " =? , " + Const.USERS_COMPLETEDQUESTS + " =?" +
                " WHERE (" + Const.USERS_USERNAME + " =?)";

        try {
            PreparedStatement prSt = DatabaseConnection.getInstance().getConnection().prepareStatement(select);

            int newRating = DataCache.getRating() + questCost;
            prSt.setInt(1, newRating);

            JSONArray newCompletedQuests = new JSONArray();
            newCompletedQuests.addAll(DataCache.getSetCompletedQuests());
            newCompletedQuests.add(questName);
            prSt.setString(2, newCompletedQuests.toString());

            prSt.setString(3, DataCache.getUserName());

            prSt.executeUpdate();
            DatabaseConnection.getInstance().closeConnection();
        } catch (SQLException e) {
            logger.info("Problems with add completed quest request");
        }
    }

    /**
     * Get a list of all quests
     *
     * @return List<Quest>
     * @throws SQLException - exception of get new quest or regular field of quest
     */
    public List<Quest> getQuestsList() {
        ResultSet resSet = null;
        List<Quest> list = new ArrayList<>();

        String select = "SELECT * FROM " + Const.QUESTS_TABLE;

        try {
            PreparedStatement prSt = DatabaseConnection.getInstance().getConnection().prepareStatement(select);;

            resSet = prSt.executeQuery();

            while (Objects.requireNonNull(resSet).next()){
                list.add(new Quest(resSet.getString(Const.QUESTS_QUESTNAME), resSet.getString(Const.QUESTS_DESCRIPTION),
                        resSet.getString(Const.QUESTS_CODE), Integer.parseInt(resSet.getString(Const.QUESTS_COST)),
                        resSet.getString(Const.QUESTS_MARK)));
            }

            DatabaseConnection.getInstance().closeConnection();
        } catch (SQLException e) {
            logger.info("Problems with get quest list request");
        }

        return list;
    }
}
