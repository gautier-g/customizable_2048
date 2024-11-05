package me.gap.pcd2048;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JeuTest {
    private final Jeu jeu = new Jeu(4);
    private final int[][] testGrid1 = {
        {0, 32, 16, 0},
        {8, 0, 16, 0},
        {2, 4, 2, 2},
        {128, 0, 16, 0}
    };
    private final int[][] testGrid2 = {
            {2, 32, 16, 2},
            {32, 2, 32, 4},
            {0, 32, 2, 8},
            {128, 2, 16, 2}
    };
    private final int[][] testGrid3 = {
            {2, 32, 1024, 2},
            {8, 2, 1024, 4},
            {2, 4, 8, 2},
            {128, 2, 4, 8}
    };

    @Test
    void create() {
        assertEquals(4, jeu.size());
        int not_null_numbers = 0;
        for (int i = 0; i < jeu.size(); i++) {
            for (int j = 0; j < jeu.size(); j++) {
                if (jeu.getCase(i,j) != 0) {
                    not_null_numbers++;
                }
            }
        }
        assertEquals(2, not_null_numbers);
        assertEquals(2048, jeu.getObjectif());
        assertEquals(1, jeu.getNbJouees());
        assertEquals(0, jeu.getNbGagnees());
        assertEquals("running", jeu.getGameState());
        assertNotNull(jeu.getObservateurs());
    }

    @Test
    void addRandomNumber() {
        jeu.nouveau(4, 2048);
        jeu.setCases(testGrid1);
        String running_state = jeu.addRandomNumber();
        assertEquals("running", running_state);
        int number_of_zeros1 = 0;
        int numbers_added1 = 0;
        String str = "";
        for (int i = 0; i < jeu.size(); i++) {
            for (int j = 0; j < jeu.size(); j++) {
                str += jeu.getCase(i, j);
                str += testGrid1[i][j];
                if (jeu.getCase(i, j) != 0 && testGrid1[i][j] == 0) {
                    numbers_added1++;
                }
                else if (jeu.getCase(i, j) != 0) {
                    assertEquals(testGrid1[i][j], jeu.getCase(i, j));
                }
                else {
                    number_of_zeros1++;
                }
            }
        }
        assertEquals(5, number_of_zeros1);
        assertEquals(1, numbers_added1);

        jeu.nouveau(4, 2048);
        jeu.setCases(testGrid2);
        String lost_state = jeu.addRandomNumber();
        assertEquals("lost", lost_state);
        int number_of_zeros2 = 0;
        int numbers_added2 = 0;
        for (int i = 0; i < jeu.size(); i++) {
            for (int j = 0; j < jeu.size(); j++) {
                if (jeu.getCase(i, j) != 0 && testGrid2[i][j] == 0) {
                    numbers_added2++;
                }
                else if (jeu.getCase(i, j) != 0) {
                    assertEquals(testGrid2[i][j], jeu.getCase(i, j));
                }
                else {
                    number_of_zeros2++;
                }
            }
        }
        assertEquals(0, number_of_zeros2);
        assertEquals(1, numbers_added2);
    }

    @Test
    void align_items() {
        jeu.setCases(testGrid1);
        jeu.align_items("right", 0);
        assertEquals(0, jeu.getCase(0, 0));
        assertEquals(0, jeu.getCase(0, 1));
        assertEquals(32, jeu.getCase(0, 2));
        assertEquals(16, jeu.getCase(0, 3));

        jeu.setCases(testGrid1);
        jeu.align_items("right", 1);
        assertEquals(0, jeu.getCase(1, 0));
        assertEquals(0, jeu.getCase(1, 1));
        assertEquals(8, jeu.getCase(1, 2));
        assertEquals(16, jeu.getCase(1, 3));

        jeu.setCases(testGrid1);
        jeu.align_items("right", 2);
        assertEquals(0, jeu.getCase(2, 0));
        assertEquals(2, jeu.getCase(2, 1));
        assertEquals(4, jeu.getCase(2, 2));
        assertEquals(4, jeu.getCase(2, 3));

        jeu.setCases(testGrid1);
        jeu.align_items("right", 3);
        assertEquals(0, jeu.getCase(3, 0));
        assertEquals(0, jeu.getCase(3, 1));
        assertEquals(128, jeu.getCase(3, 2));
        assertEquals(16, jeu.getCase(3, 3));

        jeu.setCases(testGrid1);
        jeu.align_items("left", 0);
        assertEquals(32, jeu.getCase(0, 0));
        assertEquals(16, jeu.getCase(0, 1));
        assertEquals(0, jeu.getCase(0, 2));
        assertEquals(0, jeu.getCase(0, 3));

        jeu.setCases(testGrid1);
        jeu.align_items("left", 1);
        assertEquals(8, jeu.getCase(1, 0));
        assertEquals(16, jeu.getCase(1, 1));
        assertEquals(0, jeu.getCase(1, 2));
        assertEquals(0, jeu.getCase(1, 3));

        jeu.setCases(testGrid1);
        jeu.align_items("left", 2);
        assertEquals(2, jeu.getCase(2, 0));
        assertEquals(4, jeu.getCase(2, 1));
        assertEquals(4, jeu.getCase(2, 2));
        assertEquals(0, jeu.getCase(2, 3));

        jeu.setCases(testGrid1);
        jeu.align_items("left", 3);
        assertEquals(128, jeu.getCase(3, 0));
        assertEquals(16, jeu.getCase(3, 1));
        assertEquals(0, jeu.getCase(3, 2));
        assertEquals(0, jeu.getCase(3, 3));

        jeu.setCases(testGrid1);
        jeu.align_items("up", 0);
        assertEquals(8, jeu.getCase(0, 0));
        assertEquals(2, jeu.getCase(1, 0));
        assertEquals(128, jeu.getCase(2, 0));
        assertEquals(0, jeu.getCase(3, 0));

        jeu.setCases(testGrid1);
        jeu.align_items("up", 1);
        assertEquals(32, jeu.getCase(0, 1));
        assertEquals(4, jeu.getCase(1, 1));
        assertEquals(0, jeu.getCase(2, 1));
        assertEquals(0, jeu.getCase(3, 1));

        jeu.setCases(testGrid1);
        jeu.align_items("up", 2);
        assertEquals(32, jeu.getCase(0, 2));
        assertEquals(2, jeu.getCase(1, 2));
        assertEquals(16, jeu.getCase(2, 2));
        assertEquals(0, jeu.getCase(3, 2));

        jeu.setCases(testGrid1);
        jeu.align_items("up", 3);
        assertEquals(2, jeu.getCase(0, 3));
        assertEquals(0, jeu.getCase(1, 3));
        assertEquals(0, jeu.getCase(2, 3));
        assertEquals(0, jeu.getCase(3, 3));

        jeu.setCases(testGrid1);
        jeu.align_items("down", 0);
        assertEquals(0, jeu.getCase(0, 0));
        assertEquals(8, jeu.getCase(1, 0));
        assertEquals(2, jeu.getCase(2, 0));
        assertEquals(128, jeu.getCase(3, 0));

        jeu.setCases(testGrid1);
        jeu.align_items("down", 1);
        assertEquals(0, jeu.getCase(0, 1));
        assertEquals(0, jeu.getCase(1, 1));
        assertEquals(32, jeu.getCase(2, 1));
        assertEquals(4, jeu.getCase(3, 1));

        jeu.setCases(testGrid1);
        jeu.align_items("down", 2);
        assertEquals(0, jeu.getCase(0, 2));
        assertEquals(32, jeu.getCase(1, 2));
        assertEquals(2, jeu.getCase(2, 2));
        assertEquals(16, jeu.getCase(3, 2));

        jeu.setCases(testGrid1);
        jeu.align_items("down", 3);
        assertEquals(0, jeu.getCase(0, 3));
        assertEquals(0, jeu.getCase(1, 3));
        assertEquals(0, jeu.getCase(2, 3));
        assertEquals(2, jeu.getCase(3, 3));
    }

    @Test
    void swipe_grid() {
        assertTrue(jeu.swipe_grid("up") <= 8);
        assertTrue(jeu.swipe_grid("down") <= 8);
        assertTrue(jeu.swipe_grid("right") <= 8);
        assertTrue(jeu.swipe_grid("left") <= 8);
        jeu.setCases(testGrid1);
        assertEquals(128, jeu.swipe_grid("up"));
        assertEquals(128, jeu.swipe_grid("down"));
        assertEquals(128, jeu.swipe_grid("right"));
        assertEquals(128, jeu.swipe_grid("left"));
    }

    @Test
    void jouer() {
        jeu.nouveau(4, 2048);
        jeu.setCases(testGrid1);
        jeu.play_no_gui("up");
        assertEquals("running", jeu.getGameState());
        assertEquals(0, jeu.getNbGagnees());
        jeu.nouveau(4, 2048);
        jeu.setCases(testGrid3);
        jeu.play_no_gui("up");
        assertEquals("won", jeu.getGameState());
        assertEquals(1, jeu.getNbGagnees());
    }
}