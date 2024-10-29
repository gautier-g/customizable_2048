package me.gap.pcd2048;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class VueMenu extends MenuBar implements Observer {
    Menu menu = new Menu();
    MenuItem newGameItem = new MenuItem();
    MenuItem chooseSizeItem = new MenuItem();
    MenuItem chooseGoalItem = new MenuItem();
    MenuItem exitItem = new MenuItem();
    Game game;

    public VueMenu(Game game) {
        newGameItem.setText("New Game (backspace)");
        exitItem.setText("Exit (escape)");
        this.game = game;
        this.game.addObserver(this);
        react();
    }

    public void react() {
        chooseSizeItem.setText("Choose Game Size (actual: " + this.game.getSize() + ")");
        chooseGoalItem.setText("Choose Game Goal (actual: " + this.game.getGoal() + ")");
        this.menu.getItems().addAll(newGameItem, chooseSizeItem, chooseGoalItem, exitItem);
    }
}
