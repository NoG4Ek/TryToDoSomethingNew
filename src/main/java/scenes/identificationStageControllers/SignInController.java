package scenes.identificationStageControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dataCache.DataCache;
import database.DBHandler;
import objects.User;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import sceneSwitcher.SceneSwitcher;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.*;
import javafx.util.Duration;
import scenes.IdentificationController;
import undecorator.Undecorator;

public class SignInController extends IdentificationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private Polygon animHex;

    @FXML
    private void switchScene(String scene) {
        SceneSwitcher.getInstance().loadScene(scene);
    }

    @FXML
    void initialize() {
        animHexStart();

        loginSignInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String passwordText = passwordField.getText().trim();

            if (!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginUser(loginText, passwordText);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                animIncorrectInput(false, loginField, passwordField);
            }
        });

        loginSignUpButton.setOnAction(event -> {
            switchScene("signUp");
        });

        loginField.setOnKeyTyped(event -> {
            animIncorrectInputOff(loginField);
        });

        passwordField.setOnKeyTyped(event -> {
            animIncorrectInputOff(passwordField);
        });

    }

    private void loginUser(String loginText, String passwordText) throws IOException {
        System.out.println(loginText);
        DBHandler dbHandler = new DBHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(passwordText);
        User currentUser = dbHandler.getUser(user);

        if(currentUser != null){
            initDataCache(currentUser);
            switchScene("game");
            final SceneSwitcher sceneSwitcher = SceneSwitcher.getInstance();
            sceneSwitcher.createMainScene(SceneSwitcher.stage, Undecorator.Form.STANDARD);
        } else {
            animIncorrectInput(true, loginField, passwordField);
        }
    }

    private void initDataCache(User user){
            DataCache.setFirstName(user.getFirstName());
            DataCache.setLastName(user.getLastName());
            DataCache.setUserName(user.getUserName());
            DataCache.setEmail(user.getEmail());
            DataCache.setPassword(user.getPassword());
            DataCache.setRating(user.getRating());
            DataCache.setCompletedQuests(user.getCompletedQuests());
    }

    private void animHexStart() {
        animHex.setLayoutX(360);
        animHex.setLayoutY(35);

        RotateTransition startRotate = new RotateTransition(Duration.seconds(9), animHex);
        startRotate.setByAngle(-360f);
        startRotate.setCycleCount(1);
        startRotate.play();

        Path path = new Path();
        path.getElements().add(new MoveTo(0,0));
        path.getElements().add(new LineTo(0, 17));
        path.getElements().add(new CubicCurveTo(-10,10,-20,2,-50, 18));
        path.getElements().add(new LineTo(-150, -15));
        path.getElements().add(new LineTo(-340, 100));
        path.getElements().add(new LineTo(-175, 200));
        path.getElements().add(new LineTo(-340, 300));
        path.getElements().add(new LineTo(-150, 448));
        path.getElements().add(new LineTo(-80, 414));
        path.getElements().add(new LineTo(-10, 448));
        path.getElements().add(new LineTo(60,  414));
        path.getElements().add(new LineTo(130, 448));
        path.getElements().add(new LineTo(322, 350));
        path.getElements().add(new LineTo(159, 250));
        path.getElements().add(new LineTo(324, 150));
        path.getElements().add(new LineTo(159, -15));
        path.getElements().add(new LineTo(70, 17));
        path.getElements().add(new LineTo(0, 0));

        PathTransition pathTransition = new PathTransition(Duration.seconds(15), path, animHex);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setDelay(Duration.seconds(2));


        RotateTransition rotateTransition1 = new RotateTransition(Duration.millis(500), animHex);
        rotateTransition1.setByAngle(120f);
        rotateTransition1.setCycleCount(1);

        RotateTransition rotateTransition2 = new RotateTransition(Duration.millis(500), animHex);
        rotateTransition2.setByAngle(180f);
        rotateTransition2.setCycleCount(1);

        RotateTransition rotateTransition3 = new RotateTransition(Duration.millis(500), animHex);
        rotateTransition3.setByAngle(-180f);
        rotateTransition3.setCycleCount(1);

        SequentialTransition sequentialRotate = new SequentialTransition();
        sequentialRotate.getChildren().addAll(rotateTransition1, rotateTransition2, rotateTransition3);
        sequentialRotate.setCycleCount(Timeline.INDEFINITE);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(pathTransition, sequentialRotate);
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.setDelay(Duration.seconds(5));
        parallelTransition.play();
    }
}
