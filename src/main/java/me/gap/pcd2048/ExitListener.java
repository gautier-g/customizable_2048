package me.gap.pcd2048;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ExitListener implements EventHandler<ActionEvent> {
    public ExitListener() {
        super();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Platform.exit();
    }
}
