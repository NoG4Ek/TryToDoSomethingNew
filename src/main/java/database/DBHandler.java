package database;

import dataCache.DataCache;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class  DBHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
         String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" +
                 dbName + "?useUnicode=true&serverTimezone=UTC&useSSL=false";

         Class.forName("com.mysql.cj.jdbc.Driver");

         dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

         return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME
                + "," + Const.USERS_USERNAME + "," + Const.USERS_EMAIL + "," + Const.USERS_PASSWORD + ")"
                + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getEmail());
            prSt.setString(5, user.getPassword());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public List<User> getUsersList() throws SQLException {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);;

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<User> list = new ArrayList<>();
        while (Objects.requireNonNull(resSet).next()){
            list.add(new User(resSet.getString(Const.USERS_FIRSTNAME), resSet.getString(Const.USERS_LASTNAME),
                    resSet.getString(Const.USERS_USERNAME), resSet.getString(Const.USERS_EMAIL),
                    resSet.getString(Const.USERS_PASSWORD), Integer.parseInt(resSet.getString(Const.USERS_RATING)),
                    resSet.getString(Const.USERS_COMPLETEDQUESTS)));
        }

        return list;
    }

    public void addCompletedQuest(String questName){
        String select = "UPDATE " + Const.USER_TABLE + " SET " +
                Const.USERS_COMPLETEDQUESTS + " =?" + " WHERE (" + Const.USERS_USERNAME + " =?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            String newListCompletedQuests = DataCache.getCompletedQuests() + "," + questName;
            prSt.setString(1, newListCompletedQuests);
            prSt.setString(2, DataCache.getUserName());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Quest> getQuestsList() throws SQLException {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.QUESTS_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);;

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Quest> list = new ArrayList<>();
        while (Objects.requireNonNull(resSet).next()){
            list.add(new Quest(resSet.getString(Const.QUESTS_QUESTNAME), resSet.getString(Const.QUESTS_DESCRIPTION),
            resSet.getString(Const.QUESTS_CODE), resSet.getString(Const.QUESTS_MARK)));
        }

        return list;
    }
}
