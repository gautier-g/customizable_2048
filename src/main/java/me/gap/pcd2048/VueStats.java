package me.gap.pcd2048;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class VueStats extends HBox implements Observer {
    Label label = new Label();
    Game game;

    public VueStats(Game game) {
        this.game = game;
        this.game.addObserver(this);
        react();
    }

    public void react() {
        this.label.setText("Parties gagnées / jouées: " + this.game.getWinsNumber() + " / " + this.game.getPartiesNumber());
    }
}
