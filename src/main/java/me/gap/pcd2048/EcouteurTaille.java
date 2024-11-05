package me.gap.pcd2048;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class EcouteurTaille implements EventHandler<ActionEvent> {
    private Jeu jeu;

    public EcouteurTaille(Jeu jeu) {
        this.jeu = jeu;
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
                jeu.setTaille(3);
            }
            else if (int_result > 16) {
                jeu.setTaille(16);
            }
            else {
                jeu.setTaille(int_result);
            }
        }
    }
}
