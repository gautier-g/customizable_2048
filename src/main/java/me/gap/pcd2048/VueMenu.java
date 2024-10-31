package me.gap.pcd2048;


import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class VueMenu extends MenuBar implements Observer {
    private MenuItem newGameItem = new MenuItem();
    private MenuItem chooseSizeItem = new MenuItem();
    private MenuItem chooseGoalItem = new MenuItem();
    private MenuItem exitItem = new MenuItem();
    private Game game;

    public VueMenu(Game game) {
        super(new Menu("Game"));
        this.setStyle("-fx-background-color: #213366; -fx-font-family: sans-serif; -fx-font-size: 20px; -fx-font-weight: bold; -fx-font-style: italic;");
        BorderPane.setMargin(this, new Insets(0, 0, 20, 0));
        this.setPrefHeight(50);
        this.setMinHeight(50);
        this.setMaxHeight(50);
        this.getMenus().getFirst().getItems().addAll(newGameItem, chooseSizeItem, chooseGoalItem, exitItem);
        newGameItem.setText("New Game (backspace)");
        newGameItem.setOnAction(new NewListener(game));
        exitItem.setText("Exit (escape)");
        exitItem.setOnAction(new ExitListener());
        chooseSizeItem.setOnAction(new SizeListener(game));
        chooseGoalItem.setOnAction(new GoalListener(game));
        this.game = game;
        this.game.addObserver(this);
        react();
    }

    public void react() {
        chooseSizeItem.setText("Choose Game Size (actual: " + this.game.getSize() + ")");
        chooseGoalItem.setText("Choose Game Goal (actual: " + this.game.getGoal() + ")");
    }
}
