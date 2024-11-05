package me.gap.pcd2048;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EcouteurJouer implements EventHandler<KeyEvent> {
    private Jeu jeu;

    public EcouteurJouer(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            this.jeu.jouer("up");
        }
        else if (event.getCode() == KeyCode.RIGHT) {
            this.jeu.jouer("down");
        }
        else if (event.getCode() == KeyCode.UP) {
            this.jeu.jouer("left");
        }
        else if (event.getCode() == KeyCode.DOWN) {
            this.jeu.jouer("right");
        }
        else if (event.getCode() == KeyCode.BACK_SPACE) {
            this.jeu.nouveau(this.jeu.size(), this.jeu.getObjectif());
        }
        else if (event.getCode() == KeyCode.ESCAPE) {
            Platform.exit();
        }
    }
}
