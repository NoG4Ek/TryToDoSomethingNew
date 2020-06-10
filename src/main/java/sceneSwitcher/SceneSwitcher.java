package sceneSwitcher;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import undecorator.Undecorator;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Utility class for controlling navigation between scenes.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class SceneSwitcher {
	static Logger logger = Logger.getLogger(SceneSwitcher.class.getName());

	private static final SceneSwitcher singleton = new SceneSwitcher();
	private SceneSwitcher() {}
	public static SceneSwitcher getInstance() {
		return singleton;
	}
	/**
	 * Convenience constants for fxml layouts managed by the navigator.
	 */
	private final String MAIN = "FXML/main.fxml";
	private List<SceneElement> scenes = new ArrayList<>();
	//private final Map<String, Pair<String, Pair<Integer, Integer>>> scenes = new HashMap<>();
	private String lastFxml = "";
	private String lastLocale = "";
	public static Stage stage;

	/**
	 * The main application layout controller.
	 */
	private static MainController mainController;

	/**
	 * Creates the main application scene.
	 * @return the created scene.
	 * @my.modified {@return} decorated scene, considering form
	 */
	public Scene createMainScene(Stage stage, Undecorator.Form form) throws IOException {
		SceneSwitcher.stage = stage;

		Undecorator undecorator = new Undecorator(stage, loadMainPane(), form);
		Scene scene = new Scene(undecorator);

		// @my.modified Transparent scene and stage
		try {
			scene.setFill(Color.TRANSPARENT);
			stage.initStyle(StageStyle.TRANSPARENT);
		} catch (java.lang.IllegalStateException ignored){

		}

		return scene;
	}

	/** Add new sceneName with path and window dimensions */
	 public void addScene(String sceneName, String fileName, int height, int width) {
		scenes.add(new SceneElement(sceneName, fileName, height, width));
	}

	/**
	 * Loads the main fxml layout.
	 * Sets up the vista switching VistaNavigator.
	 * Loads the first vista into the fxml layout.
	 *
	 * @return the loaded pane.
	 * @throws IOException if the pane could not be loaded.
	 */
	private Region loadMainPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();

		Region mainPane = loader.load(
				this.getClass().getClassLoader().getResourceAsStream(MAIN)
		);

		mainController = loader.getController();

		return mainPane;
	}


	/**
	 * Loads the scene specified by the fxml file into the
	 * vistaHolder pane of the main application layout.
	 * <p>
	 * Previously loaded scene for the same fxml file are not cached.
	 * The fxml is loaded anew and a new vista node hierarchy generated
	 * every time this method is invoked.
	 * <p>
	 * A more sophisticated load function could potentially add some
	 * enhancements or optimizations, for example:
	 * cache FXMLLoaders
	 * cache loaded scene nodes, so they can be recalled or reused
	 * allow a user to specify scene node reuse or new creation
	 * allow back and forward history like a browser
	 *
	 * @param scene name to be loaded.
	 */
	public void loadScene(String scene) {
		lastFxml = scene;
		try {
				URL url = this.getClass().getClassLoader().getResource(getScene(scene).getFileName());
				mainController.setScene(FXMLLoader.load(Objects.requireNonNull(url)));
		} catch (IOException e) {
			logger.info("Problems with load scene");
		}

		// Set minimum size
		SceneSwitcher.stage.setWidth(getScene(scene).getWidth());
		SceneSwitcher.stage.setHeight(getScene(scene).getHeight());

	}

	private SceneElement getScene(String sceneName) {
		for (SceneElement sceneFL : scenes) {
			if (sceneFL.getSceneName().equals(sceneName)){
				return sceneFL;
			}
		}
		throw new NoSuchElementException("Scene " + sceneName + " not found");
	}
}