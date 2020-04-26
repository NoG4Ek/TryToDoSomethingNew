package sceneSwitcher;

import javafx.fxml.FXMLLoader;
import lang.EncodedControl;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import undecorator.Undecorator;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class SceneSwitcher {
	private static final SceneSwitcher singleton = new SceneSwitcher();
	private SceneSwitcher() {}
	public static SceneSwitcher getInstance() {
		return singleton;
	}
	/**
	 * Convenience constants for fxml layouts managed by the navigator.
	 */
	private final String MAIN = "FXML/main.fxml";
	private final Map<String, String> scenes = new HashMap<>();
	private String lastFxml = "";
	private String lastLocale = "";

	public static Stage stage = null;

	/**
	 * The main application layout controller.
	 */
	private static MainController mainController;

	/**
	 * Creates the main application scene.
	 * @return the created scene.
	 */
	public Scene createMainScene(Stage stage) throws IOException {
		SceneSwitcher.stage = stage;

		Undecorator undecorator = new Undecorator(stage, loadMainPane());
		Scene scene = new Scene(undecorator);

		// Transparent scene and stage
		scene.setFill(Color.TRANSPARENT);
		stage.initStyle(StageStyle.TRANSPARENT);

		return scene;
	}

	public void addScene(String sceneName, String fileName) {
		scenes.put(sceneName, fileName);
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
	public void loadScene(String scene, int height, int width) {
		loadScene(scene, height, width, lastLocale);
	}

	/**
	 * Loads scene with specified language.
	 * @param scene name to be loaded.
	 * @param language to be loaded from properties file.
	 */
	public void loadScene(String scene, int height, int width, String language) {
		lastFxml = scene;
		try {
				URL url = this.getClass().getClassLoader().getResource( getScene(scene));
				mainController.setScene(FXMLLoader.load(url, getLocalization(language)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Set minimum size
		SceneSwitcher.stage.setWidth(width);
		SceneSwitcher.stage.setHeight(height);

	}
	private String getScene(String sceneName) {
		String scene = scenes.get(sceneName);
		if (scene == null) {
			throw new NoSuchElementException("Scene " + sceneName + " not found");
		} else {
			return scene;
		}
	}

	private ResourceBundle getLocalization(String language) {
		if (language.equals("")) {
			return null;
		} else {
			Locale locale = new Locale(language);

			return ResourceBundle.getBundle( "strings",
											locale,
											new EncodedControl("UTF8")
			);
		}
	}

	// TODO: default language from system
	/**
	 * Sets used locale in SceneSwither and calls loadScene()
	 * @param language language code
	 */
	public void setLocale(String language) {
		lastLocale = language;
		//loadScene(lastFxml, language);
	}
}