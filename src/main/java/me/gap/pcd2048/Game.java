package me.gap.pcd2048;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {
    private Scene scene;
    private int[][] tiles;
    private int goal;
    private int size;
    private ArrayList<Observer> observers;
    private String current_game_state;
    private String previous_game_state;
    private static int parties_number;
    private static int wins_number;

    public Game(int tilesNb, Scene scene) {
        this.observers = new ArrayList<>();
        this.scene = scene;
        parties_number = 0;
        wins_number = 0;

        create(tilesNb, 2048);
    }

    void create(int tilesNb, int goal) {
        this.tiles = new int[tilesNb][tilesNb];
        this.size = tilesNb;
        this.goal = goal;

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.tiles[i][j] = 0;
            }
        }
        this.previous_game_state = "running";
        this.current_game_state = "running";
        addRandomNumber();
        addRandomNumber();
        parties_number++;
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.react();
        }
    }

    public boolean oneTileFree() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.tiles[i][j] == 0) {
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
                tiles_copy[i][j] = this.tiles[i][j];
            }
        }
        swipe_grid("up");
        boolean lost_up = !oneTileFree();
        this.setTiles(tiles_copy);
        swipe_grid("down");
        boolean lost_down = !oneTileFree();
        this.setTiles(tiles_copy);
        swipe_grid("right");
        boolean lost_right = !oneTileFree();
        this.setTiles(tiles_copy);
        swipe_grid("left");
        boolean lost_left = !oneTileFree();
        this.setTiles(tiles_copy);

        return lost_up && lost_down && lost_right && lost_left;
    }

    public String addRandomNumber() {
        ArrayList<int[]> clear_indices = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.tiles[i][j] == 0) {
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

        this.tiles[random_coos[0]][random_coos[1]] = random_spawn_value;

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
                    current_value = this.tiles[indice][j];
                    if (current_value != 0) {
                        if (current_value == previous_value) {
                            occupied_indices.set(occupied_indices.size() - 1, previous_value*2);
                            previous_value = -1;
                        }
                        else {
                            occupied_indices.add(current_value);
                            previous_value = current_value;
                        }
                        this.tiles[indice][j] = 0;
                    }
                }

                for (int i = 0; i < occupied_indices.size(); i++) {
                    if (occupied_indices.get(i) > max) {
                        max = occupied_indices.get(i);
                    }
                }

                int k = 0;
                for (int occupiedIndex = 0; occupiedIndex<occupied_indices.size(); occupiedIndex++) {
                    this.tiles[indice][occupiedIndex] = occupied_indices.get(occupiedIndex);
                }
            }
            case "right" -> {
                for (int j = this.size - 1; j >= 0; j--) {
                    current_value = this.tiles[indice][j];
                    if (current_value != 0) {
                        if (current_value == previous_value) {
                            occupied_indices.set(occupied_indices.size() - 1, previous_value*2);
                            previous_value = -1;
                        }
                        else {
                            occupied_indices.add(current_value);
                            previous_value = current_value;
                        }
                        this.tiles[indice][j] = 0;
                    }
                }

                for (int i = 0; i < occupied_indices.size(); i++) {
                    if (occupied_indices.get(i) > max) {
                        max = occupied_indices.get(i);
                    }
                }

                for (int occupiedIndex = 0; occupiedIndex<occupied_indices.size(); occupiedIndex++) {
                    this.tiles[indice][this.size - 1 - occupiedIndex] = occupied_indices.get(occupiedIndex);
                }
            }
            case "up" -> {
                for (int i = 0; i < this.size; i++) {
                    current_value = this.tiles[i][indice];
                    if (current_value != 0) {
                        if (current_value == previous_value) {
                            occupied_indices.set(occupied_indices.size() - 1, previous_value*2);
                            previous_value = -1;
                        }
                        else {
                            occupied_indices.add(current_value);
                            previous_value = current_value;
                        }
                        this.tiles[i][indice] = 0;
                    }
                }

                for (int i = 0; i < occupied_indices.size(); i++) {
                    if (occupied_indices.get(i) > max) {
                        max = occupied_indices.get(i);
                    }
                }

                for (int occupiedIndex = 0; occupiedIndex<occupied_indices.size(); occupiedIndex++) {
                    this.tiles[occupiedIndex][indice] = occupied_indices.get(occupiedIndex);
                }
            }
            case "down" -> {
                for (int i = this.size - 1; i >= 0; i--) {
                    current_value = this.tiles[i][indice];
                    if (current_value != 0) {
                        if (current_value == previous_value) {
                            occupied_indices.set(occupied_indices.size() - 1, previous_value*2);
                            previous_value = -1;
                        }
                        else {
                            occupied_indices.add(current_value);
                            previous_value = current_value;
                        }
                        this.tiles[i][indice] = 0;
                    }
                }

                for (int i = 0; i < occupied_indices.size(); i++) {
                    if (occupied_indices.get(i) > max) {
                        max = occupied_indices.get(i);
                    }
                }

                for (int occupiedIndex = 0; occupiedIndex<occupied_indices.size(); occupiedIndex++) {
                    this.tiles[this.size - 1 - occupiedIndex][indice] = occupied_indices.get(occupiedIndex);
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

    void play(String direction) {
        int max = swipe_grid(direction);
        this.current_game_state = addRandomNumber();
        System.out.println(this.current_game_state);
        if (this.current_game_state == "running" && max >= this.goal) {
            wins_number += 1;
            this.current_game_state = "won";
        }
        notifyObservers();
        display_state();
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

    int getSize() {
        return this.size;
    }

    int getTile(int i, int j) {
        return tiles[i][j];
    }

    int getGoal() {
        return this.goal;
    }

    int getPartiesNumber() {
        return parties_number;
    }

    int getWinsNumber() {
        return wins_number;
    }

    String getGameState() {
        return this.current_game_state;
    }

    ArrayList<Observer> getObservers() {
        return this.observers;
    }

    void setSize(int tilesNb) {
        this.size = tilesNb;
        create(tilesNb, this.goal);
    }

    void setGoal(int goalNb) {
        this.goal = goalNb;
        create(this.size, goalNb);
    }

    void setTiles(int[][] tiles) { // used for testing
        for (int i = 0; i<tiles.length; i++) {
            for (int j = 0; j<tiles.length; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }
}
