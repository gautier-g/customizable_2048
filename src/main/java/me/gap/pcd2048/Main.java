package me.gap.pcd2048;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TP0");
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        BorderPane root = new BorderPane();
        Label helloWorldLabel = new Label("Hello World");

        Scene primaryScene = new Scene(helloWorldLabel);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
}