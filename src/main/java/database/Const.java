package database;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class of constants for convenient access to database fields
 */
public class Const {
    private static final ResourceBundle con = ResourceBundle.getBundle("properties/dbConst", Locale.getDefault());
    public static final String USERS_TABLE = con.getString("u.table");

    public static final String USERS_ID = con.getString("u.id");
    public static final String USERS_FIRSTNAME = con.getString("u.firstname");
    public static final String USERS_LASTNAME = con.getString("u.lastname");
    public static final String USERS_USERNAME = con.getString("u.username");
    public static final String USERS_EMAIL = con.getString("u.email");
    public static final String USERS_PASSWORD = con.getString("u.password");
    public static final String USERS_RATING = con.getString("u.rating");
    public static final String USERS_COMPLETEDQUESTS = con.getString("u.completedquests");


    public static final String QUESTS_TABLE = con.getString("q.table");

    public static final String QUESTS_ID = con.getString("q.id");
    public static final String QUESTS_NAME = con.getString("q.name");
    public static final String QUESTS_DESCRIPTION = con.getString("q.description");
    public static final String QUESTS_CODE = con.getString("q.code");
    public static final String QUESTS_COST = con.getString("q.cost");
    public static final String QUESTS_MARK = con.getString("q.mark");

}
