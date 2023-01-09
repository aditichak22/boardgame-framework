package Controllers;


import Controllers.ReversiController;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ReversiControllerTest {

    @Test
    void placeMoveWrong() {
        ReversiController cc = new ReversiController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 2, 0, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        Dimension selection = new Dimension(2,5);
        cc.selection = selection;
        cc.position = position;
        //cc.pieceSelected = 0;
        boolean res = cc.moveSequence(selection);
        assertFalse(res);

    }

    @Test
    void placeMoveCorrect() {
        ReversiController cc = new ReversiController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 2, 0, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        Dimension selection = new Dimension(5,4);
        cc.selection = selection;
        cc.position = position;
        //cc.pieceSelected = 0;
        boolean res = cc.moveSequence(selection);
        assertTrue(res);

    }

    @Test
    void placeMoveOnFilled() {
        ReversiController cc = new ReversiController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 2, 0, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        Dimension selection = new Dimension(4,3);
        cc.selection = selection;
        cc.position = position;
        //cc.pieceSelected = 0;
        boolean res = cc.moveSequence(selection);
        assertFalse(res);

    }


    @Test
    void checkWinnerDarkWinsWithMore() {
        ReversiController cc = new ReversiController();
        int[][] position =  new int[][]{
                {1, 1, 1, 1, 1, 1, 2, 2},
                {2, 1, 2, 2, 1, 1, 1, 1},
                {2, 2, 2, 1, 2, 2, 1, 1},
                {2, 2, 1, 2, 2, 1, 1, 1},
                {2, 1, 2, 2, 1, 1, 2, 1},
                {2, 1, 2, 2, 2, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 1, 1},
                {2, 2, 2, 2, 2, 2, 1, 1}
        };
        cc.position = position;
        assertEquals(cc.checkWinner(), 2);
    }

    @Test
    void checkWinnerWhiteWinsWithMore() {
        ReversiController cc = new ReversiController();
        int[][] position =  new int[][]{
                {1, 1, 1, 1, 1, 1, 2, 2},
                {2, 1, 2, 2, 1, 1, 1, 1},
                {2, 2, 2, 1, 2, 2, 1, 1},
                {2, 2, 1, 2, 2, 1, 1, 1},
                {2, 1, 2, 2, 1, 1, 2, 1},
                {2, 1, 2, 2, 2, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 1, 1},
                {2, 2, 1, 1, 1, 1, 1, 1}
        };
        cc.position = position;
        assertEquals(cc.checkWinner(), 1);
    }

    @Test
    void checkWinnerDraw() {
        ReversiController cc = new ReversiController();
        int[][] position =  new int[][]{
                {1, 1, 1, 1, 1, 1, 2, 2},
                {2, 1, 2, 2, 1, 1, 1, 1},
                {2, 2, 2, 1, 2, 2, 1, 1},
                {2, 2, 1, 2, 2, 1, 1, 1},
                {2, 1, 2, 2, 1, 1, 2, 1},
                {2, 1, 2, 2, 2, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 1, 1},
                {2, 2, 2, 2, 1, 1, 1, 1}
        };
        cc.position = position;
        assertEquals(cc.checkWinner(), 3);
    }

}