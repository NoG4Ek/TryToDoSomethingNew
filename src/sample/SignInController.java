package sample;

import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.undecorator.Undecorator;

public class SignInController {

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
    void initialize() {
        animHexStart();

        loginSignInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String passwordText = passwordField.getText().trim();

            if (!loginText.equals("") && !passwordText.equals("")) {
                loginUser(loginText, passwordText);
            } else {
                System.out.println("Пустые поля");
            }
        });

        loginSignUpButton.setOnAction(event -> {
            loginSignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("./signUp.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            Undecorator undecorator = new Undecorator(stage, (Region) root);
            Scene scene = new Scene(undecorator);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.showAndWait();
        });

    }

    private void loginUser(String loginText, String passwordText) {

    }

    void animHexStart() {
        animHex.setLayoutX(360);
        animHex.setLayoutY(35);

        RotateTransition startRotate = new RotateTransition(Duration.seconds(9), animHex);
        startRotate.setByAngle(-360f);
        startRotate.setCycleCount(1);
        startRotate.play();

        Path path = new Path();
        path.getElements().add(new MoveTo(0,0));
        path.getElements().add(new LineTo(0, 17));
        path.getElements().add(new CubicCurveTo(-10,10,-20,2,-50, 17));
        path.getElements().add(new LineTo(-150, -15));
        path.getElements().add(new LineTo(-340, 100));
        path.getElements().add(new LineTo(-175, 200));
        path.getElements().add(new LineTo(-340, 300));
        path.getElements().add(new LineTo(-150, 459));
        path.getElements().add(new LineTo(-80, 425));
        path.getElements().add(new LineTo(-10, 459));
        path.getElements().add(new LineTo(60,  425));
        path.getElements().add(new LineTo(130, 459));
        path.getElements().add(new LineTo(335, 350));
        path.getElements().add(new LineTo(170, 250));
        path.getElements().add(new LineTo(335, 150));
        path.getElements().add(new LineTo(170, -15));
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
