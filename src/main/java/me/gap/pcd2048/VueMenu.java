package me.gap.pcd2048;


import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class VueMenu extends MenuBar implements Observateur {
    private MenuItem newGameItem = new MenuItem();
    private MenuItem chooseSizeItem = new MenuItem();
    private MenuItem chooseGoalItem = new MenuItem();
    private MenuItem exitItem = new MenuItem();
    private Jeu jeu;

    public VueMenu(Jeu jeu) {
        super(new Menu("Game"));
        this.setStyle("-fx-background-color: #213366; -fx-font-family: sans-serif; -fx-font-size: 20px; -fx-font-weight: bold; -fx-font-style: italic;");
        BorderPane.setMargin(this, new Insets(0, 0, 20, 0));
        this.setPrefHeight(50);
        this.setMinHeight(50);
        this.setMaxHeight(50);
        this.getMenus().getFirst().getItems().addAll(newGameItem, chooseSizeItem, chooseGoalItem, exitItem);
        newGameItem.setText("New Game (backspace)");
        newGameItem.setOnAction(new EcouteurNouveau(jeu));
        exitItem.setText("Exit (escape)");
        exitItem.setOnAction(new EcouteurQuitter());
        chooseSizeItem.setOnAction(new EcouteurTaille(jeu));
        chooseGoalItem.setOnAction(new EcouteurObjectif(jeu));
        this.jeu = jeu;
        this.jeu.ajouterObservateur(this);
        reagir();
    }

    public void reagir() {
        chooseSizeItem.setText("Choose Game Size (actual: " + this.jeu.size() + ")");
        chooseGoalItem.setText("Choose Game Goal (actual: " + this.jeu.getObjectif() + ")");
    }
}
