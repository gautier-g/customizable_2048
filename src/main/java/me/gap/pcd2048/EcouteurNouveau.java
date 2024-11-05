package me.gap.pcd2048;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class EcouteurNouveau implements EventHandler<ActionEvent> {
    private final Jeu jeu;

    public EcouteurNouveau(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        jeu.nouveau(jeu.size(), jeu.getObjectif());
    }
}
