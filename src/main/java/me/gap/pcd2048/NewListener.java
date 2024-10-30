package me.gap.pcd2048;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class NewListener implements EventHandler<ActionEvent> {
    private final Game game;

    public NewListener(Game game) {
        this.game = game;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        game.create(game.getSize(), game.getGoal());
    }
}
