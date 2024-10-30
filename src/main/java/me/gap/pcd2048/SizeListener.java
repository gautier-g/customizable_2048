package me.gap.pcd2048;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class SizeListener implements EventHandler<ActionEvent> {
    private Game game;

    public SizeListener(Game game) {
        this.game = game;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Entrer une valeur");
        dialog.setHeaderText("Entrer une nouvelle taille de côté pour le carré:");
        dialog.setContentText("Valeur");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && (result.get().matches("\\d+"))) {
            game.setSize(Integer.parseInt(result.get()));
        }
    }
}
