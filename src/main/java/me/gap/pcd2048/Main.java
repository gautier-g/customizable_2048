package me.gap.pcd2048;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) {
        primaryStage.setTitle("PCD 2048");

        Game game = new Game(8);

        BorderPane root = new BorderPane();

        VBox leftRegion = new VBox();
        root.setLeft(leftRegion);
        VBox rightRegion = new VBox();
        root.setRight(rightRegion);

        leftRegion.setPrefWidth(100);
        leftRegion.setMinWidth(100);
        leftRegion.setMaxWidth(100);

        rightRegion.setPrefWidth(100);
        rightRegion.setMinWidth(100);
        rightRegion.setMaxWidth(100);

        VuePlateau vp = new VuePlateau(game);
        VueMenu vm = new VueMenu(game);
        VueStats vs = new VueStats(game);

        root.setTop(vm);
        root.setCenter(vp);
        root.setBottom(vs);
        root.setStyle("-fx-background-color: #040E2F;");

        Scene scene = new Scene(root, 900, 900);
        scene.setOnKeyPressed(new PlayListener(game));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}