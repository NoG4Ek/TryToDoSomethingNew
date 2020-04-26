import javafx.scene.text.Font;
import sceneSwitcher.SceneSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application class.
 */
public class Main extends Application {
    private final SceneSwitcher sceneSwitcher = SceneSwitcher.getInstance();

    @Override
    public void start(Stage stage) throws Exception{
        Font.loadFont(getClass().getResourceAsStream("fonts/Planet Space.ttf"), 30);
        sceneSwitcher.addScene("signIn", "FXML/signIn.fxml");
        sceneSwitcher.addScene("signUp", "FXML/signUp.fxml");
        sceneSwitcher.addScene("game", "FXML/game.fxml");

        stage.setTitle("PolyQuest");
        stage.setScene(sceneSwitcher.createMainScene(stage));

        sceneSwitcher.loadScene("signIn", 530, 730);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}