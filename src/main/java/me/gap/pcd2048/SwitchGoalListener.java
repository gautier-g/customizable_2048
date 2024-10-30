package me.gap.pcd2048;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class SwitchGoalListener implements EventHandler<ActionEvent> {
    private Game game;

    public SwitchGoalListener(Game game) {
        this.game = game;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Entrer une valeur");
        dialog.setHeaderText("Entrer un nouvel objectif de jeu:");
        dialog.setContentText("Valeur");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && (result.get().matches("\\d+"))) {
            game.setGoal(Integer.parseInt(result.get()));
        }
    }
}
