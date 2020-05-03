import javafx.scene.text.Font;
import sceneSwitcher.SceneSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;
import undecorator.Undecorator;

/**
 * Main application class.
 */
public class Main extends Application {
    private final SceneSwitcher sceneSwitcher = SceneSwitcher.getInstance();

    @Override
    public void start(Stage stage) throws Exception{
        loadAddResources();

        sceneSwitcher.addScene("signIn", "FXML/signIn.fxml", 530, 730);
        sceneSwitcher.addScene("signUp", "FXML/signUp.fxml", 530, 730);
        sceneSwitcher.addScene("game", "FXML/game.fxml", 630, 1030);

        stage.setTitle("PolyQuest");
        stage.setScene(sceneSwitcher.createMainScene(stage, Undecorator.Form.SIMPLE));

        sceneSwitcher.loadScene("signIn");
        stage.show();
    }

    private void loadAddResources() {
        Font.loadFont(getClass().getResourceAsStream("fonts/Planet Space.ttf"), 30);
    }

    public static void main(String[] args) {
        launch(args);
    }
}