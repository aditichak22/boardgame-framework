package Controllers;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckersControllerTest {

    @Test
    void moveSequenceFirstCondition() {
        CheckersController cc = new CheckersController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        Dimension selection = new Dimension(5,5);
        cc.selection = selection;
        cc.position = position;
        cc.pieceSelected = 0;
        boolean res = cc.moveSequence(selection);
        assertFalse(res);

    }

    @Test
    void moveSequenceSecondCondition() {
        CheckersController cc = new CheckersController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 0, 26, 0, 0, 0},
                {0, 0, 1, 4, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 2, 3, 4, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {19, 22, 21, 22, 23, 0, 0, 0},
                {0, 1, 3, 4, 5, 0, 0, 0},
                {0, 0, 0, 0, 0, 11, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}

        };

        int turn = 1;
        Dimension selection = new Dimension(5,5);
        cc.selection = selection;
        cc.position = position;
        cc.tempPosition = tempPosition;
        cc.pieceSelected = 0;
        cc.turn = turn;
        boolean res = cc.moveSequence(selection);
        assertTrue(res);

    }

    @Test
    void moveSequenceThirdCondition() {
        CheckersController cc = new CheckersController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 0, 26, 0, 0, 0},
                {0, 0, 1, 4, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 2, 3, 4, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {19, 22, 21, 22, 23, 0, 0, 0},
                {0, 1, 3, 4, 5, 0, 0, 0},
                {0, 0, 0, 0, 0, 11, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}

        };

        int turn = 1;
        Dimension selection = new Dimension(5,5);
        cc.selection = selection;
        cc.position = position;
        cc.tempPosition = tempPosition;
        cc.pieceSelected = 11;
        cc.turn = turn;
        boolean res = cc.moveSequence(selection);
        assertTrue(res);

    }

    @Test
    void checkWinnerFirstCondition() {
        CheckersController cc = new CheckersController();
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
        cc.position = position;
        assertEquals(cc.checkWinner(), 0);
    }

    @Test
    void checkWinnerSecondCondition() {
        CheckersController cc = new CheckersController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 2, 13, 0, 0, 0},
                {21, 2, 23, 0, 26, 0, 0, 0},
                {0, 0, 0, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        cc.position = position;
        assertEquals(cc.checkWinner(), 2);
    }

    @Test
    void checkWinnerThirdCondition() {
        CheckersController cc = new CheckersController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 0, 0, 5, 0, 0, 0},
                {9, 10, 11, 0, 13, 0, 0, 0},
                {21, 0, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0}
        };
        cc.position = position;
        assertEquals(cc.checkWinner(), 1);
    }

}