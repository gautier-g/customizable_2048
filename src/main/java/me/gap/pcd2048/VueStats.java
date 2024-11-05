package me.gap.pcd2048;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class VueStats extends HBox implements Observateur {
    private HBox hboxQueJutiliseAfinDePouvoirCentrerLeLabelEnBasDeLEcran = new HBox();
    private Label label = new Label();
    private Jeu jeu;

    public VueStats(Jeu jeu) {
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
        this.jeu = jeu;
        this.jeu.ajouterObservateur(this);
        reagir();
    }

    public void reagir() {
        this.label.setText("Games won/played: " + this.jeu.getNbGagnees() + " / " + this.jeu.getNbJouees());
    }
}
