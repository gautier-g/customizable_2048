package me.gap.pcd2048;

import java.util.ArrayList;

public class Game {
    private int[][] tiles;
    private int goal;
    private int size;
    private ArrayList<Observer> observers;
    private String current_game_state;
    private static int parties_number;
    private static int wins_number;

    public Game(int tilesNb) {
        this.observers = new ArrayList<>();
        parties_number = 0;
        wins_number = 0;

        nouveau(tilesNb, 2048);
    }

    void nouveau(int tilesNb, int goal) {
        this.tiles = new int[tilesNb][tilesNb];
        this.size = tilesNb;
        this.goal = goal;

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.tiles[i][j] = 0;
            }
        }
        this.current_game_state = "running";
        parties_number++;
    }

    void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.notify();
        }
    }

    String addRandomNumber() {
        ArrayList<int[]> clear_indices = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                clear_indices.add(new int[]{i, j});
            }
        }
        int clear_size = clear_indices.size();

        if (clear_size == 0) {
            return String.valueOf("lost");
        }

        int[] random_coos = clear_indices.get((int) (Math.random() * clear_size));

        int[] spawn_values = new int[]{2, 4, 8};
        int random_spawn_value = spawn_values[(int) (Math.random() * 3)];

        this.tiles[random_coos[0]][random_coos[1]] = random_spawn_value;

        return String.valueOf("running");
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
                        if (current_value > max) {
                            max = current_value;
                        }
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

                int k = 0;
                for (Integer occupiedIndex : occupied_indices) {
                    this.tiles[indice][k] = occupiedIndex;
                    k++;
                }
            }
            case "right" -> {
                for (int j = this.size - 1; j > 0; j--) {
                    current_value = this.tiles[indice][j];
                    if (current_value != 0) {
                        if (current_value > max) {
                            max = current_value;
                        }
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

                int k = 0;
                for (Integer occupiedIndex : occupied_indices) {
                    this.tiles[indice][this.size - 1 - k] = occupiedIndex;
                    k++;
                }
            }
            case "up" -> {
                for (int i = 0; i < this.size; i++) {
                    current_value = this.tiles[i][indice];
                    if (current_value != 0) {
                        if (current_value > max) {
                            max = current_value;
                        }
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

                int k = 0;
                for (Integer occupiedIndex : occupied_indices) {
                    this.tiles[k][indice] = occupiedIndex;
                    k++;
                }
            }
            case "down" -> {
                for (int i = this.size - 1; i > 0; i--) {
                    current_value = this.tiles[i][indice];
                    if (current_value != 0) {
                        if (current_value > max) {
                            max = current_value;
                        }
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

                int k = 0;
                for (Integer occupiedIndex : occupied_indices) {
                    this.tiles[this.size - 1 - k][indice] = occupiedIndex;
                    k++;
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
        if (this.current_game_state.equals(String.valueOf("lost")) && max >= this.goal) {
            wins_number += 1;
            this.current_game_state = String.valueOf("won");
        }
    }

    int getSize() {
        return this.size;
    }

    int getTile(int i, int j) {
        return tiles[i][j];
    }

    void setSize(int tilesNb) {
        this.size = tilesNb;
        nouveau(tilesNb, this.goal);
    }

    void setGoal(int goalNb) {
        this.goal = goalNb;
        nouveau(this.size, goalNb);
    }
}
