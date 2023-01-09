package Views;

import Abstract.AbstractController;
import Constants.ChessConstants;
import Constants.FrameworkConstants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static ClientServer.Client.board_position;

public class ChessView {
    private static Border ROLLOVER_BORDER = BorderFactory.createLineBorder(Color.BLACK, 1);
    private static ImageIcon blankChess = new ImageIcon("src/main/resources/Chess/blank.jpg");
    private static  ImageIcon lightPawn = new ImageIcon("src/main/resources/Chess/whitePawn.gif");
    private static  ImageIcon lightRook = new ImageIcon("src/main/resources/Chess/whiteRook.gif");
    private static  ImageIcon lightKnight = new ImageIcon("src/main/resources/Chess/whiteKnight.gif");
    private static  ImageIcon lightBishop = new ImageIcon("src/main/resources/Chess/whiteBishop.gif");
    private static  ImageIcon lightQueen = new ImageIcon("src/main/resources/Chess/whiteQueen.gif");
    private static  ImageIcon lightKing = new ImageIcon("src/main/resources/Chess/whiteKing.gif");
    private static  ImageIcon darkPawn = new ImageIcon("src/main/resources/Chess/blackPawn.gif");
    private static  ImageIcon darkRook = new ImageIcon("src/main/resources/Chess/blackRook.gif");
    private static  ImageIcon darkKnight = new ImageIcon("src/main/resources/Chess/blackKnight.gif");
    private static  ImageIcon darkBishop = new ImageIcon("src/main/resources/Chess/blackBishop.gif");
    private static  ImageIcon darkQueen = new ImageIcon("src/main/resources/Chess/blackQueen.gif");
    private static  ImageIcon darkKing = new ImageIcon("src/main/resources/Chess/blackKing.gif");
    private static  ImageIcon lightPawnSel = new ImageIcon("src/main/resources/Chess/selected/whitePawn.gif");
    private static  ImageIcon lightRookSel = new ImageIcon("src/main/resources/Chess/selected/whiteRook.gif");
    private static  ImageIcon lightKnightSel = new ImageIcon("src/main/resources/Chess/selected/whiteKnight.gif");
    private static  ImageIcon lightBishopSel = new ImageIcon("src/main/resources/Chess/selected/whiteBishop.gif");
    private static  ImageIcon lightQueenSel = new ImageIcon("src/main/resources/Chess/selected/whiteQueen.gif");
    private static  ImageIcon lightKingSel = new ImageIcon("src/main/resources/Chess/selected/whiteKing.gif");
    private static  ImageIcon darkPawnSel = new ImageIcon("src/main/resources/Chess/selected/blackPawn.gif");
    private static  ImageIcon darkRookSel = new ImageIcon("src/main/resources/Chess/selected/blackRook.gif");
    private static  ImageIcon darkKnightSel = new ImageIcon("src/main/resources/Chess/selected/blackKnight.gif");
    private static  ImageIcon darkBishopSel = new ImageIcon("src/main/resources/Chess/selected/blackBishop.gif");
    private static  ImageIcon darkQueenSel = new ImageIcon("src/main/resources/Chess/selected/blackQueen.gif");
    private static  ImageIcon darkKingSel = new ImageIcon("src/main/resources/Chess/selected/blackKing.gif");


    public static void setupBoard(AbstractController updatedBoard) {
        int matrix[][] = updatedBoard.getTempPosition();
        for ( int i = 0; i < 8; i++ ) {
            for ( int j = 0; j < 8; j++ ) {
                int cell = matrix[i][j];
                board_position[i][j].setBorder(ROLLOVER_BORDER);
                if (cell == FrameworkConstants.BLANK_POSITION) {
                    board_position[i][j].setIcon(blankChess);
                } else if (cell == ChessConstants.BLACK_BISHOP) {
                    board_position[i][j].setIcon(darkBishop);
                } else if (cell == ChessConstants.WHITE_BISHOP) {
                    board_position[i][j].setIcon(lightBishop);
                } else if (cell == ChessConstants.BLACK_KING) {
                    board_position[i][j].setIcon(darkKing);
                } else if (cell == ChessConstants.WHITE_KING) {
                    board_position[i][j].setIcon(lightKing);
                } else if (cell == ChessConstants.BLACK_KNIGHT) {
                    board_position[i][j].setIcon(darkKnight);
                } else if (cell == ChessConstants.WHITE_KNIGHT) {
                    board_position[i][j].setIcon(lightKnight);
                } else if (cell == ChessConstants.BLACK_PAWN) {
                    board_position[i][j].setIcon(darkPawn);
                } else if (cell == ChessConstants.WHITE_PAWN) {
                    board_position[i][j].setIcon(lightPawn);
                } else if (cell == ChessConstants.BLACK_QUEEN) {
                    board_position[i][j].setIcon(darkQueen);
                } else if (cell == ChessConstants.WHITE_QUEEN) {
                    board_position[i][j].setIcon(lightQueen);
                } else if (cell == ChessConstants.BLACK_ROOK) {
                    board_position[i][j].setIcon(darkRook);
                } else if (cell == ChessConstants.WHITE_ROOK) {
                    board_position[i][j].setIcon(lightRook);
                }
            }
        }


        if (updatedBoard.getPieceSelected() != FrameworkConstants.BLANK_POSITION) {
            Dimension selection = updatedBoard.getSelection();
            int i = selection.width;
            int j = selection.height;
            int cell = matrix[i][j];

            if (cell == ChessConstants.BLACK_BISHOP) {
                board_position[i][j].setIcon(darkBishopSel);
            } else if (cell == ChessConstants.WHITE_BISHOP) {
                board_position[i][j].setIcon(lightBishopSel);
            } else if (cell == ChessConstants.BLACK_KING) {
                board_position[i][j].setIcon(darkKingSel);
            } else if (cell == ChessConstants.WHITE_KING) {
                board_position[i][j].setIcon(lightKingSel);
            } else if (cell == ChessConstants.BLACK_KNIGHT) {
                board_position[i][j].setIcon(darkKnightSel);
            } else if (cell == ChessConstants.WHITE_KNIGHT) {
                board_position[i][j].setIcon(lightKnightSel);
            } else if (cell == ChessConstants.BLACK_PAWN) {
                board_position[i][j].setIcon(darkPawnSel);
            } else if (cell == ChessConstants.WHITE_PAWN) {
                board_position[i][j].setIcon(lightPawnSel);
            } else if (cell == ChessConstants.BLACK_QUEEN) {
                board_position[i][j].setIcon(darkQueenSel);
            } else if (cell == ChessConstants.WHITE_QUEEN) {
                board_position[i][j].setIcon(lightQueenSel);
            } else if (cell == ChessConstants.BLACK_ROOK) {
                board_position[i][j].setIcon(darkRookSel);
            } else if (cell == ChessConstants.WHITE_ROOK) {
                board_position[i][j].setIcon(lightRookSel);
            }
        }
    }
}
