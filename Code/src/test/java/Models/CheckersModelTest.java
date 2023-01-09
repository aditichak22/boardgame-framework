package Models;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckersModelTest {

    @Test
    void canJumpFirstCondition() {
        CheckersModel cm = new CheckersModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0},
                {1, 2, 3, 4},
                {9, 10, 11, 12},
                {21, 22, 23, 24}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0},
                {1, 2, 3, 4},
                {9, 10, 11, 12},
                {21, 22, 23, 24}

        };
        int xPos = 2;
        int yPos = 2;
        assertTrue(cm.canJump(position, tempPosition, xPos, yPos));
    }

    @Test
    void canJumpSecondCondition() {
        CheckersModel cm = new CheckersModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0},
                {1, 4, 3, 2, 5},
                {9, 10, 11, 12, 13},
                {21, 22, 23, 24, 26}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 2, 3, 4, 5},
                {9, 10, 11, 12, 13},
                {21, 22, 23, 24, 25}

        };
        int xPos = 2;
        int yPos = 2;
        assertTrue(cm.canJump(position, tempPosition, xPos, yPos));
    }

    @Test
    void canJumpThirdCondition() {
        CheckersModel cm = new CheckersModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0},
                {1, 4, 3, 2, 5},
                {9, 10, 11, 12, 13},
                {21, 22, 23, 24, 26},
                {0, 1, 3, 4, 5},
                {0, 0, 0, 0, 0}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 2, 3, 4, 5},
                {9, 10, 11, 12, 13},
                {19, 20, 21, 22, 23},
                {0, 1, 3, 4, 5},
                {0, 0, 0, 0, 0}

        };
        int xPos = 2;
        int yPos = 3;
        assertTrue(cm.canJump(position, tempPosition, xPos, yPos));
    }
    @Test
    void canJumpFourthCondition() {
        CheckersModel cm = new CheckersModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0},
                {1, 4, 3, 2, 5},
                {9, 10, 11, 12, 13},
                {21, 22, 23, 1, 26},
                {0, 1, 3, 4, 0},
                {0, 0, 0, 0, 0}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 2, 3, 4, 5},
                {9, 10, 11, 12, 13},
                {19, 20, 21, 22, 23},
                {0, 1, 3, 4, 5},
                {0, 0, 0, 0, 0}

        };
        int xPos = 2;
        int yPos = 3;
        assertTrue(cm.canJump(position, tempPosition, xPos, yPos));
    }
    @Test
    void canJumpFifthCondition() {
        CheckersModel cm = new CheckersModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0},
                {1, 4, 2, 3, 5},
                {9, 10, 11, 12, 13},
                {21, 22, 23, 1, 26},
                {0, 1, 3, 4, 0},
                {0, 0, 0, 0, 0}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 2, 3, 4, 5},
                {9, 10, 11, 12, 13},
                {19, 20, 21, 22, 23},
                {0, 1, 3, 4, 5},
                {0, 0, 0, 0, 0}

        };
        int xPos = 3;
        int yPos = 2;
        assertTrue(cm.canJump(position, tempPosition, xPos, yPos));
    }

    @Test
    void canJumpSixthCondition() {
        CheckersModel cm = new CheckersModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5},
                {9, 10, 1, 12, 13},
                {21, 22, 23, 1, 26},
                {0, 1, 3, 4, 0},
                {0, 0, 0, 0, 0}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 2, 3, 4, 5},
                {9, 10, 11, 12, 13},
                {19, 22, 21, 22, 23},
                {0, 1, 3, 4, 5},
                {0, 0, 0, 0, 0}

        };
        int xPos = 1;
        int yPos = 3;
        assertTrue(cm.canJump(position, tempPosition, xPos, yPos));
    }

    @Test
    void canJumpSeventhCondition() {
        CheckersModel cm = new CheckersModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0},
                {1, 4, 3, 3, 5},
                {9, 10, 11, 12, 13},
                {21, 22, 1, 23, 26},
                {0, 0, 3, 4, 0},
                {0, 0, 0, 0, 0}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 2, 3, 4, 5},
                {9, 10, 11, 12, 13},
                {19, 20, 21, 22, 23},
                {0, 1, 3, 4, 5},
                {0, 0, 0, 0, 0}

        };
        int xPos = 3;
        int yPos = 2;
        assertTrue(cm.canJump(position, tempPosition, xPos, yPos));
    }
    @Test
    void canJumpEighthCondition() {
        CheckersModel cm = new CheckersModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5},
                {9, 10, 11, 12, 13},
                {21, 22, 23, 1, 26},
                {0, 0, 1, 4, 0},
                {0, 0, 0, 0, 0}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 2, 3, 4, 5},
                {9, 10, 11, 12, 13},
                {19, 22, 21, 22, 23},
                {0, 1, 3, 4, 5},
                {0, 0, 0, 0, 0}

        };
        int xPos = 1;
        int yPos = 3;
        assertTrue(cm.canJump(position, tempPosition, xPos, yPos));
    }

    @Test
    void canJumpNinthCondition() {
        CheckersModel cm = new CheckersModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5},
                {9, 10, 11, 12, 13},
                {21, 22, 23, 1, 26},
                {0, 0, 1, 4, 0},
                {0, 0, 0, 0, 0}
        };
        int[][] tempPosition = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 2, 3, 4, 5},
                {9, 10, 11, 12, 13},
                {19, 22, 21, 22, 23},
                {0, 1, 3, 4, 5},
                {0, 0, 0, 0, 0}

        };
        int xPos = 4;
        int yPos = 4;
        assertFalse(cm.canJump(position, tempPosition, xPos, yPos));
    }

    @Test
    void jumpPossibleFirstCondition() {
        CheckersModel cm = new CheckersModel();
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

        int turn = 22;

        assertTrue(cm.jumpPossible(position, tempPosition, turn));
    }


    @Test
        void jumpPossibleSecondCondition() {
            CheckersModel cm = new CheckersModel();
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

            int turn = 19;

            assertFalse(cm.jumpPossible(position, tempPosition, turn));
        }


    @Test
    void legitMoveFirstCondition() {

        CheckersModel cm = new CheckersModel();
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

        int pieceSelected = 0;
        Dimension selection = new Dimension(6,5);
        int turn = 1;
        int x = 4;
        int y = 5;
        boolean jumpRequired = false;
        assertEquals(cm.legitMove(pieceSelected, position, selection, turn, x, y, jumpRequired), 1);

    }

    @Test
    void legitMoveSecondCondition() {

        CheckersModel cm = new CheckersModel();
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

        int pieceSelected = 12;
        Dimension selection = new Dimension(6,5);
        int turn = 1;
        int x = 4;
        int y = 7;
        boolean jumpRequired = false;
        int res = cm.legitMove(pieceSelected, position, selection, turn, x, y, jumpRequired);
        assertEquals(res, 1);
    }

    @Test
    void legitMoveThirdCondition() {

        CheckersModel cm = new CheckersModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        int pieceSelected = 12;
        Dimension selection = new Dimension(3,5);
        int turn = 3;
        int x = 3;
        int y = 5;
        boolean jumpRequired = true;
        int res = cm.legitMove(pieceSelected, position, selection, turn, x, y, jumpRequired);
        assertEquals(res, 2);
    }

    @Test
    void legitMoveFourthCondition() {

        CheckersModel cm = new CheckersModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        int pieceSelected = 22;
        Dimension selection = new Dimension(7,5);
        int turn = 3;
        int x = 3;
        int y = 5;
        boolean jumpRequired = true;
        int res = cm.legitMove(pieceSelected, position, selection, turn, x, y, jumpRequired);
        assertEquals(res, 2);
    }

    @Test
    void legitMoveFifthCondition() {

        CheckersModel cm = new CheckersModel();
        int[][] position =  new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 2, 0, 5, 0, 0, 0},
                {9, 10, 11, 12, 13, 0, 0, 0},
                {21, 22, 23, 1, 26, 0, 0, 0},
                {0, 0, 1, 4, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        int pieceSelected = 22;
        Dimension selection = new Dimension(7,7);
        int turn = 3;
        int x = 3;
        int y = 5;
        boolean jumpRequired = true;
        int res = cm.legitMove(pieceSelected, position, selection, turn, x, y, jumpRequired);
        assertEquals(res, 0);
    }
}