package me.gap.pcd2048;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class VueStats extends HBox implements Observer {
    private HBox hboxQueJutiliseAfinDePouvoirCentrerLeLabelEnBasDeLEcran = new HBox();
    private Label label = new Label();
    private Game game;

    public VueStats(Game game) {
        super();
        BorderPane.setMargin(this, new Insets(20, 0, 0, 0));
        this.label.setFont(Font.font("sans-serif", FontWeight.BOLD, 20));
        this.label.setTextAlignment(TextAlignment.CENTER);
        this.label.setAlignment(Pos.CENTER);
        this.setPrefHeight(150);
        this.setMinHeight(150);
        this.setMaxHeight(150);
        this.label.setStyle("-fx-text-fill: white;");
        this.hboxQueJutiliseAfinDePouvoirCentrerLeLabelEnBasDeLEcran.setMinWidth(900);
        this.hboxQueJutiliseAfinDePouvoirCentrerLeLabelEnBasDeLEcran.setMaxWidth(900);
        this.hboxQueJutiliseAfinDePouvoirCentrerLeLabelEnBasDeLEcran.setAlignment(Pos.CENTER);
        this.hboxQueJutiliseAfinDePouvoirCentrerLeLabelEnBasDeLEcran.getChildren().add(this.label);
        this.getChildren().add(hboxQueJutiliseAfinDePouvoirCentrerLeLabelEnBasDeLEcran);
        this.game = game;
        this.game.addObserver(this);
        react();
    }

    public void react() {
        this.label.setText("Parties gagnées / jouées: " + this.game.getWinsNumber() + " / " + this.game.getPartiesNumber());
    }
}
