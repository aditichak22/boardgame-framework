package Models;

import Controllers.ChessController;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ChessModelTest {

    @Test
    void getXandYandCastled() {
        ChessModel cm = new ChessModel();
        cm.setX(3);
        cm.setY(4);
        cm.setCastled(false);
        assertEquals(cm.getX(), 3);
        assertEquals(cm.getY(), 4);
        assertFalse(cm.isCastled());
    }

    @Test
    void pawnMoveFirstCondition() {
        ChessModel cm = new ChessModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };


        Dimension selection = new Dimension(5,5);
        int turn = 1;
        int x = 6;
        int y = 4;
        assertTrue(cm.pawnMove(position, selection, turn, x, y));
    }

    @Test
    void pawnMoveSecondCondition() {
        ChessModel cm = new ChessModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };


        Dimension selection = new Dimension(5,5);
        int turn = 3;
        int x = 5;
        int y = 6;
        assertTrue(cm.pawnMove(position, selection, turn, x, y));
    }


    @Test
    void pawnMoveThirdCondition() {
        ChessModel cm = new ChessModel();
        int[][] position = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };


        Dimension selection = new Dimension(6, 4);
        int turn = 1;
        int x = 4;
        int y = 4;
        boolean res = cm.pawnMove(position, selection, turn, x, y);
        assertTrue(res);
    }


    @Test
    void pawnMoveFourthCondition() {
        ChessModel cm = new ChessModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };


        Dimension selection = new Dimension(5,5);
        int turn = 1;
        int x = 4;
        int y = 4;
        boolean res = cm.pawnMove(position, selection, turn, x, y);
        assertFalse(res);
    }

    @Test
    void collisionDetectionFirstCondition() {

        ChessModel cm = new ChessModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };


        Dimension selection = new Dimension(5,5);
        int x = 6;
        int y = 6;
        int xDirection = 1;
        int yDirection = 1;
        boolean res = cm.collisionDetection(position, selection, x, y, xDirection, yDirection);
        assertTrue(res);

    }
    @Test
    void collisionDetectionSecondCondition() {

        ChessModel cm = new ChessModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };


        Dimension selection = new Dimension(5,5);
        int x = 4;
        int y = 4;
        int xDirection = 1;
        int yDirection = 1;
        boolean res = cm.collisionDetection(position, selection, x, y, xDirection, yDirection);
        assertFalse(res);

    }
    @Test
    void rookMoveFirstCondition() {
        ChessModel cm = new ChessModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };


        Dimension selection = new Dimension(6,5);
        int x = 6;
        int y = 6;
        boolean res = cm.rookMove(position, selection, x, y);
        assertTrue(res);
    }

    @Test
    void rookMoveSecondCondition() {
        ChessModel cm = new ChessModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };


        Dimension selection = new Dimension(6,5);
        int x = 3;
        int y = 6;
        boolean res = cm.rookMove(position, selection, x, y);
        assertFalse(res);
    }

    @Test
    void rookMoveThirdCondition() {
        ChessModel cm = new ChessModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };


        Dimension selection = new Dimension(5,5);
        int x = 5;
        int y = 6;
        boolean res = cm.rookMove(position, selection, x, y);
        assertTrue(res);
    }


    @Test
    void rookMoveFourthCondition() {
        ChessModel cm = new ChessModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };


        Dimension selection = new Dimension(5,5);
        int x = 5;
        int y = 4;
        boolean res = cm.rookMove(position, selection, x, y);
        assertTrue(res);
    }

    @Test
    void rookMoveFifthCondition() {
        ChessModel cm = new ChessModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };


        Dimension selection = new Dimension(5,6);
        int x = 5;
        int y = 4;
        boolean res = cm.rookMove(position, selection, x, y);
        assertFalse(res);
    }


    @Test
    void knightMove() {

        ChessModel cm = new ChessModel();
        Dimension selection1 = new Dimension(5,6);
        int x1 = 7;
        int y1 = 7;
        assertTrue(cm.knightMove(selection1, x1, y1));

        Dimension selection2 = new Dimension(5,6);
        int x2 = 7;
        int y2 = 3;
        assertTrue(cm.knightMove(selection2, x2, y2));

        Dimension selection3 = new Dimension(5,6);
        int x3 = 5;
        int y3 = 7;
        assertTrue(cm.knightMove(selection3, x3, y3));

        Dimension selection4 = new Dimension(5,6);
        int x4 = 5;
        int y4 = 3;
        assertTrue(cm.knightMove(selection4, x4, y4));

        Dimension selection5 = new Dimension(3,3);
        int x5 = 5;
        int y5 = 4;
        assertTrue(cm.knightMove(selection5, x5, y5));

        Dimension selection6 = new Dimension(3,3);
        int x6 = 5;
        int y6 = 2;
        assertTrue(cm.knightMove(selection6, x6, y6));

        Dimension selection7 = new Dimension(3,3);
        int x7 = 1;
        int y7 = 4;
        assertTrue(cm.knightMove(selection7, x7, y7));

        Dimension selection8 = new Dimension(3,3);
        int x8 = 1;
        int y8 = 2;
        assertTrue(cm.knightMove(selection8, x8, y8));

        Dimension selection9 = new Dimension(10,10);
        int x9 = 1;
        int y9 = 2;
        assertFalse(cm.knightMove(selection9, x9, y9));
    }

    @Test
    void bishopMoveFirstCondition() {
        ChessModel cm = new ChessModel();
        ChessController cc = new ChessController();
        Dimension selection = new Dimension(5,5);
        cm.setX(3);
        cm.setY(3);

        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        cc.selection = selection;
        cc.position = position;
        assertTrue(cm.bishopMove(cc));

    }

    @Test
    void bishopMoveSecondCondition() {
        ChessModel cm = new ChessModel();
        ChessController cc = new ChessController();
        Dimension selection = new Dimension(5,5);
        cm.setX(6);
        cm.setY(6);

        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        cc.selection = selection;
        cc.position = position;
        boolean res = cm.bishopMove(cc);
        assertTrue(res);
    }

    @Test
    void bishopMoveThirdCondition() {
        ChessModel cm = new ChessModel();
        ChessController cc = new ChessController();
        Dimension selection = new Dimension(3,6);
        cm.setX(7);
        cm.setY(5);

        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        cc.selection = selection;
        cc.position = position;
        boolean res = cm.bishopMove(cc);
        assertFalse(res);
    }


    @Test
    void kingMoveFirstCondition() {
        ChessModel cm = new ChessModel();
        ChessController cc = new ChessController();
        Dimension selection = new Dimension(3,6);
        cm.setX(7);
        cm.setY(4);

        cc.selection = selection;
        boolean res = cm.kingMove(cc);
        assertTrue(res);
    }

    @Test
    void kingMoveSecondCondition() {
        ChessModel cm = new ChessModel();
        ChessController cc = new ChessController();
        Dimension selection = new Dimension(3,6);
        cm.setX(4);
        cm.setY(4);
        cc.turn = 2;

        int[][] tempPosition =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 3, 0, 0},
                {0, 0, 1, 4, 22, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 3, 0, 0}
        };
        cc.tempPosition = tempPosition;
        cc.position = tempPosition;
        cc.selection = selection;
        boolean res = cm.kingMove(cc);
        assertFalse(res);
    }

    @Test
    void kingMoveThirdCondition() {
        ChessModel cm = new ChessModel();
        ChessController cc = new ChessController();
        Dimension selection = new Dimension(4,5);
        cm.setX(4);
        cm.setY(4);
        cc.turn = 1;

        int[][] tempPosition =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 12, 0, 0},
                {0, 12, 1, 4, 33, 0, 1, 0},
                {0, 0, 0, 0, 12, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 3, 0, 0}
        };
        cc.tempPosition = tempPosition;
        cc.position = tempPosition;
        cc.selection = selection;
        boolean res = cm.kingMove(cc);
        boolean cas = cm.isCastled();
        assertTrue(res);
        assertTrue(cas);
    }

    @Test
    void queenMoveFirstCondition() {
        ChessModel cm = new ChessModel();
        ChessController cc = new ChessController();
        Dimension selection = new Dimension(5,5);
        cm.setX(6);
        cm.setY(6);

        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        cc.selection = selection;
        cc.position = position;
        boolean res = cm.queenMove(cc);
        assertTrue(res);

    }

    @Test
    void queenMoveSecondCondition() {
        ChessModel cm = new ChessModel();
        ChessController cc = new ChessController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        cm.setX(6);
        cm.setY(6);
        Dimension selection = new Dimension(6,5);
        cc.selection = selection;
        cc.position = position;
        boolean res = cm.queenMove(cc);
        assertTrue(res);
    }

    @Test
    void queenMoveThirdCondition() {
        ChessModel cm = new ChessModel();
        ChessController cc = new ChessController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        cm.setX(3);
        cm.setY(6);
        Dimension selection = new Dimension(6,5);
        cc.selection = selection;
        cc.position = position;
        boolean res = cm.queenMove(cc);
        assertFalse(res);
    }

    @Test
    void legitMoveFirstCondition() {
        ChessModel cm = new ChessModel();
        ChessController cc = new ChessController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        cc.position = position;
        Dimension selection = new Dimension(4,2);
        cc.selection = selection;
        cm.setX(3);
        cm.setY(6);

        cc.pieceSelected = 14;
        assertFalse(cm.legitMove(cc));
    }

    @Test
    void legitMoveSecondCondition() {
        ChessModel cm = new ChessModel();
        ChessController cc = new ChessController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 2, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 2, 0, 0}
        };
        cc.position = position;
        Dimension selection = new Dimension(3,6);
        cm.setX(7);
        cm.setY(4);
        cc.selection = selection;


        cc.pieceSelected = 41;
        boolean res = cm.legitMove(cc);
        assertTrue(res);
    }

    @Test
    void legitMoveThirdCondition() {
        ChessModel cm = new ChessModel();
        ChessController cc = new ChessController();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 7, 0},
                {0, 0, 1, 4, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 2, 0, 0}
        };
        cc.position = position;
        Dimension selection = new Dimension(3,6);
        cm.setX(7);
        cm.setY(4);
        cc.selection = selection;

        cc.pieceSelected = 11;
        assertFalse(cm.legitMove(cc));
    }
}