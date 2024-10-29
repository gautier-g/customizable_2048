package me.gap.pcd2048;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) {
        BorderPane root = new BorderPane() ;
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}