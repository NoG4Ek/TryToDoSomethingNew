package scenes;

import java.net.URL;
import java.util.ResourceBundle;

import database.DBHandler;
import database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Polygon;

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
    private Polygon animHex;

    @FXML
    void initialize() {

        loginSingUpButton.setOnAction(event -> {
            signUpNewUser();
        });
    }

    private void signUpNewUser(){
        DBHandler dbHandler = new DBHandler();

        String firsName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String userName = userNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        User user = new User(firsName, lastName, userName, email, password);

        dbHandler.signUpUser(user);
    }

}
