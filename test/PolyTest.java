import java.util.*;

import gameLogic.DataCache;
import database.DBHandler;
import gameLogic.RatingHandler;
import javafx.util.Pair;
import objects.Quest;
import objects.Rating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PolyTest {

    @BeforeEach
    public void dbSetUp() {
        DataCache.getInstance().setUserList(new DBHandler().getUsersList());
        DataCache.getInstance().setQuestList(new DBHandler().getQuestsList());
        DataCache.getInstance().setUserName("test");
    }

    @Test
    public void findTopPlayers() {
        List<Pair<String, Integer>> playersTop = new ArrayList<>();

        playersTop.add(new Pair<>("test", 100));
        Assertions.assertEquals(playersTop, DataCache.getInstance().findTopPlayers(1));

        playersTop.add(new Pair<>("test1", 50));
        playersTop.add(new Pair<>("test2", 25));
        Assertions.assertEquals(playersTop, DataCache.getInstance().findTopPlayers(3));
    }

    @Test
    public void findActiveQuest() {
        Quest quest = new Quest("test", "just test", "test", 100, "active");

        Assertions.assertEquals(quest, DataCache.getInstance().findActiveQuest());
    }

    @Test
    public void placeInTop(){
        DataCache.getInstance().setUserName("test");
        Assertions.assertEquals(1, DataCache.getInstance().placeInTop());

        DataCache.getInstance().setUserName("test1");
        Assertions.assertEquals(2, DataCache.getInstance().placeInTop());

        DataCache.getInstance().setUserName("test2");
        Assertions.assertEquals(3, DataCache.getInstance().placeInTop());
    }

    @Test
    public void getCurrentRating(){
        Rating rating1 = new Rating("./rangs/First_rang.png", "Wood", 1);
        Rating rating2 = new Rating("./rangs/Second_rang.png", "Steel", 2);

        Assertions.assertEquals(rating1, RatingHandler.getCurrentRating(300));
        Assertions.assertEquals(rating2, RatingHandler.getCurrentRating(800));
        Assertions.assertEquals(0.13333333333333333, RatingHandler.getProgress(100));
    }
}
