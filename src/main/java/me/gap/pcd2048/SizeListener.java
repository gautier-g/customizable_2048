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
        dialog.setTitle("Enter a value");
        dialog.setHeaderText("Enter new game square width:");
        dialog.setContentText("Value (from 3 to 16)");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && (result.get().matches("\\d{0,9}"))) {
            int int_result = Integer.parseInt(result.get());

            if (int_result < 2) {
                game.setSize(3);
            }
            else if (int_result > 16) {
                game.setSize(16);
            }
            else {
                game.setSize(int_result);
            }
        }
    }
}
