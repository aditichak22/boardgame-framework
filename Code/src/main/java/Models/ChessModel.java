package Models;

import Constants.ChessConstants;
import Constants.FrameworkConstants;
import Controllers.ChessController;
import java.lang.Math;
import java.awt.*;

public class ChessModel {

    private static int x;
    private static int y;
    private static boolean castled = false;

    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        ChessModel.x = x;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int y) {
        ChessModel.y = y;
    }

    public static boolean isCastled() {
        return castled;
    }

    public static void setCastled(boolean castled) {
        ChessModel.castled = castled;
    }


    public static boolean pawnMove(int[][] position, Dimension selection, int turn, int x, int y) {
        int direction;
        int startY;
        if (turn == FrameworkConstants.PLAYER_WHITE) {
            direction = -1;
            startY = 6;
        } else { // turn==DARK
            direction = 1;
            startY = 1;
        }

        if ((x == selection.height + 1 || x == selection.height - 1)
                && y == selection.width + direction && position[y][x] != FrameworkConstants.BLANK_POSITION) { // pawn capture
            return true;
        } else if (x == selection.height && y == selection.width + direction && position[y][x] == FrameworkConstants.BLANK_POSITION) {// pawn going straight
            return true;
        } else if (selection.width == startY && x == selection.height &&
                y == selection.width + (direction * 2) && position[y][x] == FrameworkConstants.BLANK_POSITION) { // special case for first pawn move being two jumps
            return true;
        }
        return false;
    }


    public static boolean collisionDetection(int[][] position, Dimension selection, int x, int y, int xDirection, int yDirection) {
        int i, j;
        for ( i = selection.height + xDirection, j = selection.width + yDirection; ;
              i += xDirection, j += yDirection ) {
            if (i == x && j == y) {
                return true;
            }

            if (position[j][i] != FrameworkConstants.BLANK_POSITION) {
                return false;
            }
        }
    }


    public static boolean rookMove(int[][] position, Dimension selection, int x, int y) {
        if (x != selection.height && y == selection.width) {
            if (x > selection.height) {
                return collisionDetection(position, selection, x, y, 1, 0);
            } else {
                return collisionDetection(position, selection, x, y, -1, 0);
            }
        } else if (x == selection.height && y != selection.width) {
            if (y > selection.width) {
                return collisionDetection(position, selection, x, y, 0, 1);
            } else {
                return collisionDetection(position, selection, x, y, 0, -1);
            }
        }
        return false;
    }

    //Checks if the move is legit for a knight.
    public static boolean knightMove(Dimension selection, int x, int y) {
        if ((x == selection.height + 1 && y == selection.width + 2) ||
                (x == selection.height + 1 && y == selection.width - 2) ||
                (x == selection.height - 1 && y == selection.width + 2) ||
                (x == selection.height - 1 && y == selection.width - 2) ||
                (x == selection.height + 2 && y == selection.width + 1) ||
                (x == selection.height + 2 && y == selection.width - 1) ||
                (x == selection.height - 2 && y == selection.width + 1) ||
                (x == selection.height - 2 && y == selection.width - 1)) {
            return true;
        }
        return false;
    }

    //Checks if the move is legit for a bishop.
    public static boolean bishopMove(ChessController chess_controller) {
        if (Math.abs(x - chess_controller.selection.height) == Math.abs(y - chess_controller.selection.width)) {
            int xDirection = x > chess_controller.selection.height ? 1 : -1;
            int yDirection = y > chess_controller.selection.width ? 1 : -1;
            return collisionDetection(chess_controller.position, chess_controller.selection, x, y, xDirection, yDirection);
        }
        return false;
    }


    //Checks if the move is legit for a king.
     public static boolean kingMove(ChessController chess_controller) {
        if(x == chess_controller.selection.height + 1 && y == chess_controller.selection.width + 1
        || x == chess_controller.selection.height - 1 && y == chess_controller.selection.width + 1
        || x == chess_controller.selection.height - 1 && y == chess_controller.selection.width - 1
        || x == chess_controller.selection.height + 1 && y == chess_controller.selection.width - 1){
            return true;
        }else if((chess_controller.turn == FrameworkConstants.PLAYER_WHITE && chess_controller.tempPosition[y][x]== ChessConstants.WHITE_ROOK)||
                (chess_controller.turn == FrameworkConstants.PLAYER_BLACK && chess_controller.tempPosition[y][x]== ChessConstants.BLACK_ROOK)){

            int direction = x > chess_controller.selection.height ? 1 : -1;

            if(collisionDetection(chess_controller.position, chess_controller.selection, x, y, direction, 0)){
                castled = true;
                return true;
            }
        }

        return false;
    }

    //Checks if the move is legit for a queen.
    public static boolean queenMove(ChessController chess_controller){
        return (rookMove(chess_controller.position, chess_controller.selection, x, y) || bishopMove(chess_controller));
    }

    public static boolean legitMove(ChessController chess_controller){
        if(chess_controller.position[chess_controller.selection.width][chess_controller.selection.height]== FrameworkConstants.PLAYER_WHITE){
            if(chess_controller.pieceSelected == ChessConstants.WHITE_PAWN){
                return pawnMove(chess_controller.position, chess_controller.selection, chess_controller.turn, getX(), getY());
            }else if(chess_controller.pieceSelected == ChessConstants.WHITE_ROOK){
                return rookMove(chess_controller.position, chess_controller.selection, getX(), getY());
            }else if(chess_controller.pieceSelected == ChessConstants.WHITE_KNIGHT){
                return knightMove(chess_controller.selection, getX(), getY());
            }else if(chess_controller.pieceSelected == ChessConstants.WHITE_BISHOP) {
                return bishopMove(chess_controller);
            }else if(chess_controller.pieceSelected == ChessConstants.WHITE_QUEEN){
                return queenMove(chess_controller);
            }else if(chess_controller.pieceSelected == ChessConstants.WHITE_KING){
                return kingMove(chess_controller);
            }
        }else if(chess_controller.position[chess_controller.selection.width][chess_controller.selection.height]== FrameworkConstants.PLAYER_BLACK){
            if(chess_controller.pieceSelected == ChessConstants.BLACK_PAWN){
                return pawnMove(chess_controller.position, chess_controller.selection, chess_controller.turn, getX(), getY());
            }else if(chess_controller.pieceSelected == ChessConstants.BLACK_ROOK){
                return rookMove(chess_controller.position, chess_controller.selection, getX(), getY());
            }else if(chess_controller.pieceSelected == ChessConstants.BLACK_KNIGHT){
                return knightMove(chess_controller.selection, getX(), getY());
            }else if(chess_controller.pieceSelected == ChessConstants.BLACK_BISHOP){
                return bishopMove(chess_controller);
            }else if(chess_controller.pieceSelected == ChessConstants.BLACK_QUEEN){
                return queenMove(chess_controller);
            }else if(chess_controller.pieceSelected == ChessConstants.BLACK_KING){
                return kingMove(chess_controller);
            }
        }else{
            System.out.println("Somehow a blank space has been selected!");
        }
        return false;
    }
}