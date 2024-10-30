package me.gap.pcd2048;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayListener implements EventHandler<KeyEvent> {
    private Game game;

    public PlayListener(Game game) {
        this.game = game;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            this.game.play("up");
        }
        else if (event.getCode() == KeyCode.RIGHT) {
            this.game.play("down");
        }
        else if (event.getCode() == KeyCode.UP) {
            this.game.play("left");
        }
        else if (event.getCode() == KeyCode.DOWN) {
            this.game.play("right");
        }
    }
}
