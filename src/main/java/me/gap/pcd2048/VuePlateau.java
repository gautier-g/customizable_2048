package me.gap.pcd2048;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class VuePlateau extends GridPane implements Observer {
    private Label[][] tiles;
    private Game game;

    public VuePlateau(Game game) {
        super();
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(8), BorderWidths.DEFAULT)));
        this.setStyle("-fx-background-color: #42858C; -fx-background-radius: 8px;");
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setHgap(5);
        this.setVgap(5);
        this.setPrefWidth(700);
        this.setPrefHeight(700);
        this.setMinHeight(700);
        this.setMaxHeight(700);
        this.setMinWidth(700);
        this.setMaxWidth(700);
        this.game = game;
        for (int i = 0; i < this.game.getSize(); i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS);
            this.getColumnConstraints().add(column);

            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            this.getRowConstraints().add(row);
        }
        this.game.addObserver(this);
        Platform.runLater(this::react);
    }

    public void react() {
        this.getChildren().clear();
        int grid_width = this.game.getSize();
        this.tiles = new Label[grid_width][grid_width];
        double tile_size = (double) ((700 - 40 - 10*(grid_width-1))/grid_width) + 5;


        for (int i = 0; i < grid_width; i++) {
            for (int j = 0; j < grid_width; j++) {
                if (game.getTile(i,j) == 0) {
                    this.tiles[i][j] = new Label();
                }
                else {
                    this.tiles[i][j] = new Label(String.valueOf(game.getTile(i,j)));
                }
                this.tiles[i][j].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(8), BorderWidths.DEFAULT)));
                this.tiles[i][j].setStyle("-fx-background-color: #63CCCA; -fx-background-radius: 8px;");
                this.tiles[i][j].setTextFill(Color.web("#0B132B"));
                this.tiles[i][j].setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, tile_size/3));

                this.tiles[i][j].setPrefWidth(tile_size);
                this.tiles[i][j].setPrefHeight(tile_size);
                this.tiles[i][j].setMinHeight(tile_size);
                this.tiles[i][j].setMaxHeight(tile_size);
                this.tiles[i][j].setMinWidth(tile_size);
                this.tiles[i][j].setMaxWidth(tile_size);

                this.tiles[i][j].setTextAlignment(TextAlignment.CENTER);
                this.tiles[i][j].setAlignment(Pos.CENTER);

                this.add(this.tiles[i][j], i, j);

                GridPane.setHgrow(this.tiles[i][j], Priority.ALWAYS);
                GridPane.setVgrow(this.tiles[i][j], Priority.ALWAYS);
            }
        }
    }
}
