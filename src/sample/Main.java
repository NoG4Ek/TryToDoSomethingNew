package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.undecorator.Undecorator;

public class Main extends Application {

    /* @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("singIn.fxml"));
        primaryStage.setTitle("Поликвест");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }*/

    @Override
    public void start(final Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        Undecorator undecorator = new Undecorator(stage, (Region) root);
        //undecorator.getStylesheets().add("./stagedecoration.fxml"); //?
        Scene scene = new Scene(undecorator);

        // Transparent scene and stage
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);

        // Set minimum size
        stage.setMinWidth(700);
        stage.setMinHeight(500);

        stage.setTitle("signInScene.fxml"); // Title name
        stage.setScene(scene);
        root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
