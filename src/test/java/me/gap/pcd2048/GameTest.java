package me.gap.pcd2048;

import javafx.scene.layout.BorderPane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private final Game game = new Game(4);
    private final int[][] testGrid1 = {
        {0, 32, 16, 0},
        {8, 0, 16, 0},
        {2, 4, 2, 2},
        {128, 0, 16, 0}
    };
    private final int[][] testGrid2 = {
            {2, 32, 16, 2},
            {8, 2, 16, 2},
            {2, 4, 2, 2},
            {128, 2, 16, 2}
    };
    private final int[][] testGrid3 = {
            {2, 32, 1024, 2},
            {8, 2, 1024, 4},
            {2, 4, 8, 2},
            {128, 2, 16, 64}
    };

    @Test
    void create() {
        assertEquals(4, game.getSize());
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                assertEquals(0, game.getTile(i, j));
            }
        }
        assertEquals(2048, game.getGoal());
        assertEquals(1, game.getPartiesNumber());
        assertEquals(0, game.getWinsNumber());
        assertEquals("running", game.getGameState());
        assertNotNull(game.getObservers());
    }

    @Test
    void addObserver() {
        VueStats vue_stats = new VueStats(game);
        game.addObserver(vue_stats);
        assertEquals(1, game.getObservers().size());
        VueMenu vue_menu = new VueMenu(game);
        game.addObserver(vue_menu);
        assertEquals(2, game.getObservers().size());
        VuePlateau vue_plateau = new VuePlateau(game);
        game.addObserver(vue_plateau);
        assertEquals(3, game.getObservers().size());
    }

    @Test
    void addRandomNumber() {
        game.setTiles(testGrid1);
        String running_state = game.addRandomNumber();
        assertEquals("running", running_state);
        int number_of_zeros1 = 0;
        int numbers_added1 = 0;
        String str = "";
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                str += game.getTile(i, j);
                str += testGrid1[i][j];
                if (game.getTile(i, j) != 0 && testGrid1[i][j] == 0) {
                    numbers_added1++;
                }
                else if (game.getTile(i, j) != 0) {
                    assertEquals(testGrid1[i][j], game.getTile(i, j));
                }
                else {
                    number_of_zeros1++;
                }
            }
        }
        assertEquals(5, number_of_zeros1);
        assertEquals(1, numbers_added1);

        game.setTiles(testGrid2);
        String lost_state = game.addRandomNumber();
        assertEquals("lost", lost_state);
        int number_of_zeros2 = 0;
        int numbers_added2 = 0;
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                if (game.getTile(i, j) != 0 && testGrid2[i][j] == 0) {
                    numbers_added2++;
                }
                else if (game.getTile(i, j) != 0) {
                    assertEquals(testGrid2[i][j], game.getTile(i, j));
                }
                else {
                    number_of_zeros2++;
                }
            }
        }
        assertEquals(0, number_of_zeros2);
        assertEquals(0, numbers_added2);
    }

    @Test
    void align_items() {
        game.setTiles(testGrid1);
        game.align_items("right", 0);
        assertEquals(0, game.getTile(0, 0));
        assertEquals(0, game.getTile(0, 1));
        assertEquals(32, game.getTile(0, 2));
        assertEquals(16, game.getTile(0, 3));

        game.setTiles(testGrid1);
        game.align_items("right", 1);
        assertEquals(0, game.getTile(1, 0));
        assertEquals(0, game.getTile(1, 1));
        assertEquals(8, game.getTile(1, 2));
        assertEquals(16, game.getTile(1, 3));

        game.setTiles(testGrid1);
        game.align_items("right", 2);
        assertEquals(0, game.getTile(2, 0));
        assertEquals(2, game.getTile(2, 1));
        assertEquals(4, game.getTile(2, 2));
        assertEquals(4, game.getTile(2, 3));

        game.setTiles(testGrid1);
        game.align_items("right", 3);
        assertEquals(0, game.getTile(3, 0));
        assertEquals(0, game.getTile(3, 1));
        assertEquals(128, game.getTile(3, 2));
        assertEquals(16, game.getTile(3, 3));

        game.setTiles(testGrid1);
        game.align_items("left", 0);
        assertEquals(32, game.getTile(0, 0));
        assertEquals(16, game.getTile(0, 1));
        assertEquals(0, game.getTile(0, 2));
        assertEquals(0, game.getTile(0, 3));

        game.setTiles(testGrid1);
        game.align_items("left", 1);
        assertEquals(8, game.getTile(1, 0));
        assertEquals(16, game.getTile(1, 1));
        assertEquals(0, game.getTile(1, 2));
        assertEquals(0, game.getTile(1, 3));

        game.setTiles(testGrid1);
        game.align_items("left", 2);
        assertEquals(2, game.getTile(2, 0));
        assertEquals(4, game.getTile(2, 1));
        assertEquals(4, game.getTile(2, 2));
        assertEquals(0, game.getTile(2, 3));

        game.setTiles(testGrid1);
        game.align_items("left", 3);
        assertEquals(128, game.getTile(3, 0));
        assertEquals(16, game.getTile(3, 1));
        assertEquals(0, game.getTile(3, 2));
        assertEquals(0, game.getTile(3, 3));

        game.setTiles(testGrid1);
        game.align_items("up", 0);
        assertEquals(8, game.getTile(0, 0));
        assertEquals(2, game.getTile(1, 0));
        assertEquals(128, game.getTile(2, 0));
        assertEquals(0, game.getTile(3, 0));

        game.setTiles(testGrid1);
        game.align_items("up", 1);
        assertEquals(32, game.getTile(0, 1));
        assertEquals(4, game.getTile(1, 1));
        assertEquals(0, game.getTile(2, 1));
        assertEquals(0, game.getTile(3, 1));

        game.setTiles(testGrid1);
        game.align_items("up", 2);
        assertEquals(32, game.getTile(0, 2));
        assertEquals(2, game.getTile(1, 2));
        assertEquals(16, game.getTile(2, 2));
        assertEquals(0, game.getTile(3, 2));

        game.setTiles(testGrid1);
        game.align_items("up", 3);
        assertEquals(2, game.getTile(0, 3));
        assertEquals(0, game.getTile(1, 3));
        assertEquals(0, game.getTile(2, 3));
        assertEquals(0, game.getTile(3, 3));

        game.setTiles(testGrid1);
        game.align_items("down", 0);
        assertEquals(0, game.getTile(0, 0));
        assertEquals(8, game.getTile(1, 0));
        assertEquals(2, game.getTile(2, 0));
        assertEquals(128, game.getTile(3, 0));

        game.setTiles(testGrid1);
        game.align_items("down", 1);
        assertEquals(0, game.getTile(0, 1));
        assertEquals(0, game.getTile(1, 1));
        assertEquals(32, game.getTile(2, 1));
        assertEquals(4, game.getTile(3, 1));

        game.setTiles(testGrid1);
        game.align_items("down", 2);
        assertEquals(0, game.getTile(0, 2));
        assertEquals(32, game.getTile(1, 2));
        assertEquals(2, game.getTile(2, 2));
        assertEquals(16, game.getTile(3, 2));

        game.setTiles(testGrid1);
        game.align_items("down", 3);
        assertEquals(0, game.getTile(0, 3));
        assertEquals(0, game.getTile(1, 3));
        assertEquals(0, game.getTile(2, 3));
        assertEquals(2, game.getTile(3, 3));
    }

    @Test
    void swipe_grid() {
        assertEquals(0, game.swipe_grid("up"));
        assertEquals(0, game.swipe_grid("right"));
        assertEquals(0, game.swipe_grid("left"));
        assertEquals(0, game.swipe_grid("down"));
        game.setTiles(testGrid1);
        assertEquals(128, game.swipe_grid("up"));
        assertEquals(128, game.swipe_grid("down"));
        assertEquals(128, game.swipe_grid("right"));
        assertEquals(128, game.swipe_grid("left"));
    }

    @Test
    void play() {
        game.setTiles(testGrid1);
        game.play("up");
        assertEquals("running", game.getGameState());
        assertEquals(0, game.getWinsNumber());
        game.setTiles(testGrid3);
        game.play("up");
        assertEquals("won", game.getGameState());
        assertEquals(1, game.getWinsNumber());
    }
}