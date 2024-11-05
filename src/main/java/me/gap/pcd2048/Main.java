package me.gap.pcd2048;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) {
        primaryStage.setTitle("PCD 2048");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 900, 900);

        Jeu jeu = new Jeu(4, scene);


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

        VuePlateau vp = new VuePlateau(jeu);
        VueMenu vm = new VueMenu(jeu);
        VueStats vs = new VueStats(jeu);

        root.setTop(vm);
        root.setCenter(vp);
        root.setBottom(vs);
        root.setStyle("-fx-background-color: #040E2F;");

        scene.setOnKeyPressed(new EcouteurJouer(jeu));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}