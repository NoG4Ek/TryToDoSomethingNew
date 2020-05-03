package scenes;

import java.net.URL;
import java.util.ResourceBundle;

import database.DBHandler;
import database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.jetbrains.annotations.NotNull;
import sceneSwitcher.SceneSwitcher;

public class SignUpController {

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

        loginSingUpButton.setOnAction(event -> {
            signUpNewUser();
            switchScene("signIn");
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

        String firsName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String userName = userNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (!firsName.equals("") && !lastName.equals("") &&
            !userName.equals("") && !email.equals("") &&
            !password.equals("") && !checkPasswordField.getText().equals("")) {
            if (password.equals(checkPasswordField.getText())) {
                DBHandler dbHandler = new DBHandler();

                User user = new User(firsName, lastName, userName, email, password);

                dbHandler.signUpUser(user);
            } else {
                animIncorrectInput(checkPasswordField);
            }
        } else {
            if (firsName.equals(""))
                animIncorrectInput(firstNameField);
            if (lastName.equals(""))
                animIncorrectInput(lastNameField);
            if (userName.equals(""))
                animIncorrectInput(userNameField);
            if (email.equals(""))
                animIncorrectInput(emailField);
            if (password.equals(""))
                animIncorrectInput(passwordField);
            if (checkPasswordField.getText().equals(""))
                animIncorrectInput(checkPasswordField);
        }
    }

    private void animIncorrectInput(@NotNull TextField ... textFields) {
        for (TextField textField: textFields) {
            textField.setBorder(new Border(new BorderStroke(Color.rgb(110, 7, 38), BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
            textField.setEffect(new DropShadow(20, Color.rgb(89, 1, 28)));
        }
    }

    private void animIncorrectInputOff(@NotNull TextField textField) {
        textField.setBorder(Border.EMPTY);
        textField.setEffect(new DropShadow(4.5, Color.rgb(0, 0, 0)));
    }

}
