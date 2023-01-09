package Controllers;

import Constants.FrameworkConstants;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessControllerTest {

    @Test
    void moveSequenceFirstCondition() {
        // try to move a blank piece
        ChessController cc = new ChessController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {30, 31, 32, 0, 32, 0, 0, 0},
                {34, 31, 31, 30, 36, 0, 0, 0},
                {36, 36, 36, 0, 30, 0, 0, 0},
                {0, 0, 30, 41, 30, 0, 35, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 30, 0, 30, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        Dimension selection = new Dimension(5,5);
        cc.selection = selection;
        cc.position = position;
        cc.pieceSelected = 0;
        assertFalse(cc.moveSequence(selection));

    }

    @Test
    void moveSequenceSecondCondition() {
        // undo a movement
        ChessController cc = new ChessController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 31, 32, 0, 32, 0, 0, 0},
                {34, 31, 31, 0, 36, 0, 0, 0},
                {36, 36, 36, 0, 30, 0, 0, 0},
                {0, 0, 30, 41, 30, 0, 35, 0},
                {0, 0, 0, 0, 0, 30, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        Dimension selection = new Dimension(5,5);
        cc.selection = selection;
        cc.position = position;
        cc.pieceSelected = 30;
        assertTrue(cc.moveSequence(selection));

    }
    @Test
    void moveSequenceThirdCondition() {
        // try to move a pawn
        ChessController cc = new ChessController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 31, 32, 0, 32, 0, 0, 0},
                {34, 31, 31, 0, 36, 0, 0, 0},
                {36, 36, 36, 0, 30, 0, 0, 0},
                {0, 0, 30, 41, 30, 0, 35, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 30, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        Dimension selection = new Dimension(5,5);
        cc.selection = selection;
        cc.position = position;
        cc.pieceSelected = 30;
        assertTrue(cc.moveSequence(selection));

    }


    @Test
    void checkWinnerFirstCondition() {
        // black piece player wins
        ChessController cc = new ChessController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 31, 32, 0, 32, 0, 0, 0},
                {34, 31, 31, 0, 36, 0, 0, 0},
                {36, 36, 36, 0, 30, 0, 0, 0},
                {0, 0, 30, 41, 30, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 30, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        cc.tempPosition = position;
        assertEquals(cc.checkWinner(), FrameworkConstants.PLAYER_BLACK);
    }

    @Test
    void checkWinnerSecondCondition() {
        // white piece player wins
        ChessController cc = new ChessController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 31, 32, 0, 32, 0, 0, 0},
                {34, 31, 31, 0, 36, 0, 0, 0},
                {36, 36, 36, 0, 30, 0, 0, 0},
                {0, 0, 30, 35, 30, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 30, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        cc.tempPosition = position;
        assertEquals(cc.checkWinner(), FrameworkConstants.PLAYER_WHITE);
    }


    @Test
    void checkWinnerThirdCondition() {
        // neither piece player won yet
        ChessController cc = new ChessController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 31, 32, 0, 32, 0, 0, 0},
                {34, 31, 31, 0, 36, 0, 0, 0},
                {36, 36, 36, 0, 30, 0, 0, 0},
                {0, 0, 30, 41, 30, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 35, 0, 0, 30, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        cc.tempPosition = position;
        assertEquals(cc.checkWinner(), FrameworkConstants.BLANK_POSITION);
    }
}
