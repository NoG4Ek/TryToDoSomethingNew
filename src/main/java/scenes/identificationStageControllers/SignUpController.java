package scenes.identificationStageControllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import database.DBHandler;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.scene.control.Alert;
import javafx.scene.shape.*;
import javafx.util.Duration;
import objects.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import sceneSwitcher.SceneSwitcher;
import scenes.IdentificationController;

public class SignUpController extends IdentificationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginSingUpButton;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField checkPasswordField;

    @FXML
    private Button backToSignInButton;

    @FXML
    private Polygon animHex;

    @FXML
    private void switchScene(String scene) {
        SceneSwitcher.getInstance().loadScene(scene);
    }

    @FXML
    void initialize() {
        animHexStart();

        loginSingUpButton.setOnAction(event -> {
            signUpNewUser();
        });

        backToSignInButton.setOnAction((event -> {
            switchScene("signIn");
        }));

        firstNameField.setOnKeyTyped(event -> {
            animIncorrectInputOff(firstNameField);
        });

        lastNameField.setOnKeyTyped(event -> {
            animIncorrectInputOff(lastNameField);
        });

        userNameField.setOnKeyTyped(event -> {
            animIncorrectInputOff(userNameField);
        });

        emailField.setOnKeyTyped(event -> {
            animIncorrectInputOff(emailField);
        });

        passwordField.setOnKeyTyped(event -> {
            animIncorrectInputOff(passwordField);
        });

        checkPasswordField.setOnKeyTyped(event -> {
            animIncorrectInputOff(checkPasswordField);
        });

    }

    private void signUpNewUser(){

        String firsName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String userName = userNameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String checkPassword = checkPasswordField.getText().trim();

        if (!firsName.equals("") && !lastName.equals("") &&
            !userName.equals("") && !email.equals("") &&
            !password.equals("") && !checkPassword.equals("")) {
            if (password.equals(checkPassword)) {
                DBHandler dbHandler = new DBHandler();

                User user = new User(firsName, lastName, userName, email, password);

                if (!userExist(user)) {
                    dbHandler.signUpUser(user);
                    switchScene("signIn");
                } else {
                    showAlert();
                }
            } else {
                animIncorrectInput(true, checkPasswordField);
            }
        } else {
            animIncorrectInput(false, firstNameField, lastNameField, userNameField,
                    emailField, passwordField, checkPasswordField);
        }
    }

    private boolean userExist(User user){
        DBHandler dbHandler = new DBHandler();
        List<User> allUsers = dbHandler.getUsersList();

        for(User existingUser: allUsers){
            if (existingUser.getUserName().equals(user.getUserName()) ||
            existingUser.getEmail().equals(user.getEmail()))
                return true;
        }

        return false;
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");

        alert.setContentText("User already exist");

        alert.showAndWait();
    }

    private void animHexStart() {
        animHex.setLayoutX(360);
        animHex.setLayoutY(35);

        Path leftPath = new Path();
        leftPath.getElements().add(new MoveTo(0,17));
        leftPath.getElements().add(new LineTo(-250, 17));
        leftPath.getElements().add(new LineTo(0, 17));

        PathTransition leftPathTransition = new PathTransition(Duration.seconds(10), leftPath, animHex);
        leftPathTransition.setCycleCount(1);

        Path rightPath = new Path();
        rightPath.getElements().add(new MoveTo(0,17));
        rightPath.getElements().add(new LineTo(235, 17));
        rightPath.getElements().add(new LineTo(0, 17));

        PathTransition rightPathTransition = new PathTransition(Duration.seconds(10), rightPath, animHex);
        leftPathTransition.setCycleCount(1);

        SequentialTransition leftRightSequentialRotate = new SequentialTransition();
        leftRightSequentialRotate.getChildren().addAll(leftPathTransition, rightPathTransition);
        leftRightSequentialRotate.setCycleCount(Timeline.INDEFINITE);

        RotateTransition slowRotateTransitionLeft = new RotateTransition(Duration.seconds(5), animHex);
        slowRotateTransitionLeft.setByAngle(-360f);
        slowRotateTransitionLeft.setCycleCount(1);

        RotateTransition slowRotateTransitionRight = new RotateTransition(Duration.seconds(5), animHex);
        slowRotateTransitionRight.setByAngle(360f);
        slowRotateTransitionRight.setCycleCount(1);

        SequentialTransition slowSequentialRotate = new SequentialTransition();
        slowSequentialRotate.getChildren().addAll(slowRotateTransitionLeft, slowRotateTransitionRight);
        slowSequentialRotate.setCycleCount(Timeline.INDEFINITE);

        ParallelTransition slowParallelTransition = new ParallelTransition();
        slowParallelTransition.getChildren().addAll(leftRightSequentialRotate, slowSequentialRotate);
        slowParallelTransition.setCycleCount(Timeline.INDEFINITE);
        slowParallelTransition.play();
    }
}
