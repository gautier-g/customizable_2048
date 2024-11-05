package me.gap.pcd2048;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Jeu {
    private Scene scene;
    private int[][] cases;
    private int objectif;
    private int size;
    private ArrayList<Observateur> observateurs;
    private String current_game_state;
    private String previous_game_state;
    private static int parties_number;
    private static int wins_number;

    public Jeu(int tilesNb, Scene scene) {
        this.observateurs = new ArrayList<>();
        this.scene = scene;
        parties_number = 0;
        wins_number = 0;

        nouveau(tilesNb, 2048);
    }

    public Jeu(int nbCases) { // FOR TESTS
        this.observateurs = new ArrayList<>();
        parties_number = 0;
        wins_number = 0;

        nouveau(nbCases, 2048);
    }

    void nouveau(int tilesNb, int goal) {
        this.cases = new int[tilesNb][tilesNb];
        this.size = tilesNb;
        this.objectif = goal;

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.cases[i][j] = 0;
            }
        }
        this.previous_game_state = "running";
        this.current_game_state = "running";
        addRandomNumber();
        addRandomNumber();
        parties_number++;
        notifierObservateurs();
    }

    public void ajouterObservateur(Observateur obs) {
        this.observateurs.add(obs);
    }

    public void notifierObservateurs() {
        for (Observateur observer : this.observateurs) {
            observer.reagir();
        }
    }

    public boolean oneTileFree() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.cases[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIsLost() {
        int[][] tiles_copy = new int[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                tiles_copy[i][j] = this.cases[i][j];
            }
        }
        swipe_grid("up");
        boolean lost_up = !oneTileFree();
        this.setCases(tiles_copy);
        swipe_grid("down");
        boolean lost_down = !oneTileFree();
        this.setCases(tiles_copy);
        swipe_grid("right");
        boolean lost_right = !oneTileFree();
        this.setCases(tiles_copy);
        swipe_grid("left");
        boolean lost_left = !oneTileFree();
        this.setCases(tiles_copy);

        return lost_up && lost_down && lost_right && lost_left;
    }

    public String addRandomNumber() {
        ArrayList<int[]> clear_indices = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.cases[i][j] == 0) {
                    clear_indices.add(new int[]{i, j});
                }
            }
        }
        int clear_size = clear_indices.size();

        if (clear_size == 0) {
            if (this.current_game_state.equals("won")) {
                return "won";
            }
            else if (this.current_game_state.equals("lost")) {
                return "lost";
            }
            else {
                return "running";
            }
        }

        Random rand = new Random();
        int[] random_coos = clear_indices.get((int) rand.nextInt(clear_size));

        int[] spawn_values = new int[]{2, 4, 8};
        int random_spawn_value = spawn_values[(int) rand.nextInt(3)];

        this.cases[random_coos[0]][random_coos[1]] = random_spawn_value;

        if (checkIsLost()) {
            return "lost";
        }

        if (this.current_game_state.equals("won")) {
            return "won";
        }
        else {
            return "running";
        }
    }

    int align_items(String direction, int indice) {
        int max = 0;
        ArrayList<Integer> occupied_indices = new ArrayList<>();
        int previous_value = -1;
        int current_value = 0;

        switch (direction) {
            case "left" -> {
                for (int j = 0; j < this.size; j++) {
                    current_value = this.cases[indice][j];
                    if (current_value != 0) {
                        if (current_value == previous_value) {
                            occupied_indices.set(occupied_indices.size() - 1, previous_value*2);
                            previous_value = -1;
                        }
                        else {
                            occupied_indices.add(current_value);
                            previous_value = current_value;
                        }
                        this.cases[indice][j] = 0;
                    }
                }

                for (int i = 0; i < occupied_indices.size(); i++) {
                    if (occupied_indices.get(i) > max) {
                        max = occupied_indices.get(i);
                    }
                }

                int k = 0;
                for (int occupiedIndex = 0; occupiedIndex<occupied_indices.size(); occupiedIndex++) {
                    this.cases[indice][occupiedIndex] = occupied_indices.get(occupiedIndex);
                }
            }
            case "right" -> {
                for (int j = this.size - 1; j >= 0; j--) {
                    current_value = this.cases[indice][j];
                    if (current_value != 0) {
                        if (current_value == previous_value) {
                            occupied_indices.set(occupied_indices.size() - 1, previous_value*2);
                            previous_value = -1;
                        }
                        else {
                            occupied_indices.add(current_value);
                            previous_value = current_value;
                        }
                        this.cases[indice][j] = 0;
                    }
                }

                for (int i = 0; i < occupied_indices.size(); i++) {
                    if (occupied_indices.get(i) > max) {
                        max = occupied_indices.get(i);
                    }
                }

                for (int occupiedIndex = 0; occupiedIndex<occupied_indices.size(); occupiedIndex++) {
                    this.cases[indice][this.size - 1 - occupiedIndex] = occupied_indices.get(occupiedIndex);
                }
            }
            case "up" -> {
                for (int i = 0; i < this.size; i++) {
                    current_value = this.cases[i][indice];
                    if (current_value != 0) {
                        if (current_value == previous_value) {
                            occupied_indices.set(occupied_indices.size() - 1, previous_value*2);
                            previous_value = -1;
                        }
                        else {
                            occupied_indices.add(current_value);
                            previous_value = current_value;
                        }
                        this.cases[i][indice] = 0;
                    }
                }

                for (int i = 0; i < occupied_indices.size(); i++) {
                    if (occupied_indices.get(i) > max) {
                        max = occupied_indices.get(i);
                    }
                }

                for (int occupiedIndex = 0; occupiedIndex<occupied_indices.size(); occupiedIndex++) {
                    this.cases[occupiedIndex][indice] = occupied_indices.get(occupiedIndex);
                }
            }
            case "down" -> {
                for (int i = this.size - 1; i >= 0; i--) {
                    current_value = this.cases[i][indice];
                    if (current_value != 0) {
                        if (current_value == previous_value) {
                            occupied_indices.set(occupied_indices.size() - 1, previous_value*2);
                            previous_value = -1;
                        }
                        else {
                            occupied_indices.add(current_value);
                            previous_value = current_value;
                        }
                        this.cases[i][indice] = 0;
                    }
                }

                for (int i = 0; i < occupied_indices.size(); i++) {
                    if (occupied_indices.get(i) > max) {
                        max = occupied_indices.get(i);
                    }
                }

                for (int occupiedIndex = 0; occupiedIndex<occupied_indices.size(); occupiedIndex++) {
                    this.cases[this.size - 1 - occupiedIndex][indice] = occupied_indices.get(occupiedIndex);
                }
            }
        }
        return max;
    }

    int swipe_grid(String direction) {
        int max = 0;
        for (int i = 0; i < this.size; i++) {
            int max_i = align_items(direction, i);
            if (max_i > max) {
                max = max_i;
            }
        }
        return max;
    }

    void jouer(String direction) {
        int max = swipe_grid(direction);
        this.current_game_state = addRandomNumber();
        System.out.println(this.current_game_state);
        if (this.current_game_state == "running" && max >= this.objectif) {
            wins_number += 1;
            this.current_game_state = "won";
        }
        notifierObservateurs();
        display_state();
    }

    void play_no_gui(String direction) { // FOR TESTS
        int max = swipe_grid(direction);
        this.current_game_state = addRandomNumber();
        System.out.println(this.current_game_state);
        if (this.current_game_state == "running" && max >= this.objectif) {
            wins_number += 1;
            this.current_game_state = "won";
        }
        notifierObservateurs();
    }

    void display_state() {
        Popup popup = new Popup();
        popup.setAutoHide(true);

        if (this.current_game_state == "won" && this.previous_game_state == "running") {
            Label label = new Label("You won!");
            label.setFont(Font.font("Arial", 25));
            label.setTextFill(Color.WHITE);
            label.setStyle("-fx-background-color: black; -fx-padding: 10px;");
            popup.getContent().add(label);
            popup.show(this.scene.getWindow());
        }
        else if (this.current_game_state == "lost" && this.previous_game_state == "running") {
            Label label = new Label("You lost!");
            label.setFont(Font.font("Arial", 25));
            label.setTextFill(Color.WHITE);
            label.setStyle("-fx-background-color: black; -fx-padding: 10px;");
            popup.getContent().add(label);
            popup.show(this.scene.getWindow());
        }
        this.previous_game_state = current_game_state;

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> popup.hide());
        pause.play();
    }

    int size() {
        return this.size;
    }

    int getCase(int lig, int col) {
        return cases[lig][col];
    }

    int getObjectif() {
        return this.objectif;
    }

    int getNbJouees() {
        return parties_number;
    }

    int getNbGagnees() {
        return wins_number;
    }

    String getGameState() {
        return this.current_game_state;
    }

    ArrayList<Observateur> getObservateurs() {
        return this.observateurs;
    }

    void setTaille(int taille) {
        this.size = taille;
        nouveau(taille, this.objectif);
    }

    void setObjectif(int objectif) {
        this.objectif = objectif;
        nouveau(this.size, objectif);
    }

    void setCases(int[][] cases) { // used for testing
        for (int i = 0; i< cases.length; i++) {
            for (int j = 0; j< cases.length; j++) {
                this.cases[i][j] = cases[i][j];
            }
        }
    }
}
