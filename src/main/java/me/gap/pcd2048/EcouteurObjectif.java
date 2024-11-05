package me.gap.pcd2048;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class EcouteurObjectif implements EventHandler<ActionEvent> {
    private Jeu jeu;

    public EcouteurObjectif(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter a value");
        dialog.setHeaderText("Enter new game goal:");
        dialog.setContentText("Value (from 50 to 8192)");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && (result.get().matches("\\d{0,9}"))) {
            int int_result = Integer.parseInt(result.get());

            if (int_result < 50) {
                jeu.setObjectif(50);
            }
            else if (int_result > 8192) {
                jeu.setObjectif(8192);
            }
            else {
                jeu.setObjectif(int_result);
            }
        }
    }
}
