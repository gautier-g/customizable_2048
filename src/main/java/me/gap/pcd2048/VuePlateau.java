package me.gap.pcd2048;

import javafx.scene.layout.GridPane;

public class VuePlateau extends GridPane implements Observer {
    Game game;

    public VuePlateau(Game game) {

        this.game = game;
        this.game.addObserver(this);
    }

    public void react() {

    }
}
