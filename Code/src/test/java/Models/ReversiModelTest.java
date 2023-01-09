package Models;

import Controllers.ReversiController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ReversiModelTest {


    @Test
    void getNumDark() {
        ReversiModel rm = new ReversiModel();
        rm.setNumDark(3);
        assertEquals(rm.getNumDark(), 3);
    }

    @Test
    void getNumLight() {
        ReversiModel rm = new ReversiModel();
        rm.setNumLight(2);
        assertEquals(rm.getNumLight(), 2);
    }


    @Test
    void markCell() {
        ReversiModel rm = new ReversiModel();

        ReversiController rc = new ReversiController();
        int row = 3;
        int col = 3;
        int value = 2;
        rc.turn = 3;
        rm.markCell(rc, row, col, value);
        assertEquals(rc.position[3][3], 2);
        assertEquals(rc.turn, 2);

        int valueW = 1;
        rc.turn = 4;
        rm.markCell(rc, row, col, valueW);
        assertEquals(rc.position[3][3], 1);
        assertEquals(rc.turn, 1);
    }

    @Test
    void countPieces() {
        ReversiModel rm = new ReversiModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 2, 13, 0, 0, 0},
                {21, 2, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0}
        };

        rm.countPieces(position);
        assertEquals(rm.getNumDark(), 4);
        assertEquals(rm.getNumLight(), 4);

    }

    @Test
    void legitMove() {

        ReversiModel rm = new ReversiModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 2, 13, 0, 0, 0},
                {21, 2, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 2, 3, 4, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {19, 22, 21, 22, 23, 0, 0, 0},
                {0, 1, 3, 4, 5, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        ReversiController rc = new ReversiController();
        rc.position = position;
        rc.tempPosition = tempPosition;
        assertTrue(rm.legitMove(rc));

    }

    @Test
    void nonLegitMove() {

        ReversiModel rm = new ReversiModel();
        int[][] position =  new int[][]{
                {1, 4, 2, 1, 5, 1, 1, 18},
                {1, 4, 2, 1, 5, 1, 1, 18},
                {1, 4, 2, 1, 5, 1, 1, 18},
                {21, 2, 23, 1, 26, 10, 10, 10},
                {21, 2, 23, 1, 26, 10, 10, 10},
                {21, 2, 23, 1, 26, 10, 10, 10},
                {21, 2, 23, 1, 16, 11, 12, 18},
                {21, 2, 23, 1, 16, 11, 12, 18}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 2, 3, 4, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {19, 22, 21, 22, 23, 0, 0, 0},
                {0, 1, 3, 4, 5, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        ReversiController rc = new ReversiController();
        rc.position = position;
        rc.tempPosition = tempPosition;
        assertFalse(rm.legitMove(rc));

    }
}