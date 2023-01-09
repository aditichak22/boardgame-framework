package Models;

import Constants.CheckersConstants;
import Constants.FrameworkConstants;

import java.awt.*;

public class CheckersModel {


    //Checks if the piece in the position can make a jump.
    public static boolean canJump(int[][] position, int[][] tempPosition, int xPos, int yPos){
        if(tempPosition[yPos][xPos]== CheckersConstants.WHITE_MAN){
            if(xPos>1 && yPos>1 && position[yPos-1][xPos-1]== FrameworkConstants.PLAYER_BLACK && position[yPos-2][xPos-2]== FrameworkConstants.BLANK_POSITION){ // check jump to the left
                return true;
            }

            if(xPos<6 && yPos>1 && position[yPos-1][xPos+1]== FrameworkConstants.PLAYER_BLACK && position[yPos-2][xPos+2]== FrameworkConstants.BLANK_POSITION){ // check jump to the right
                return true;
            }
        }else if(tempPosition[yPos][xPos]== CheckersConstants.BLACK_MAN){
            if(xPos>1 && yPos<6 && position[yPos+1][xPos-1]== FrameworkConstants.PLAYER_WHITE && position[yPos+2][xPos-2]== FrameworkConstants.BLANK_POSITION){ // check jump to the left
                return true;
            }
            if(xPos<6 && yPos<6 && position[yPos+1][xPos+1]== FrameworkConstants.PLAYER_WHITE && position[yPos+2][xPos+2]== FrameworkConstants.BLANK_POSITION){ // check jump to the right
                return true;
            }
        }
        else if(tempPosition[yPos][xPos]== CheckersConstants.WHITE_KING || tempPosition[yPos][xPos]== CheckersConstants.BLACK_KING) {
            int opponent;
            if(tempPosition[yPos][xPos]== CheckersConstants.WHITE_KING) {
                opponent = FrameworkConstants.PLAYER_BLACK;
            }
            else{ // DARKKING
                opponent = FrameworkConstants.PLAYER_WHITE;
            }
            if(xPos>1 && yPos>1 && position[yPos-1][xPos-1]==opponent && position[yPos-2][xPos-2]== FrameworkConstants.BLANK_POSITION){ // check jump to the left
                return true;
            }
            if(xPos<6 && yPos>1 && position[yPos-1][xPos+1]==opponent && position[yPos-2][xPos+2]== FrameworkConstants.BLANK_POSITION){ // check jump to the right
                return true;
            }
            if(xPos>1 && yPos<6 && position[yPos+1][xPos-1]== FrameworkConstants.PLAYER_WHITE && position[yPos+2][xPos-2]== FrameworkConstants.BLANK_POSITION){ // check jump to the left
                return true;
            }
            if(xPos<6 && yPos<6 && position[yPos+1][xPos+1]== FrameworkConstants.PLAYER_WHITE && position[yPos+2][xPos+2]== FrameworkConstants.BLANK_POSITION){ // check jump to the right
                return true;
            }
        }
        return false;
    }

    //Checks jump possibility of all pieces of the current turn's side.
    public static boolean jumpPossible(int[][] position, int[][] tempPosition, int turn){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(position[j][i]== turn){
                    if(canJump(position, tempPosition, i,j)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int legitMove(int pieceSelected, int[][] position, Dimension selection, int turn, int x, int y, boolean jumpRequired){
        int direction;
        int opponent;
        if(turn == FrameworkConstants.PLAYER_WHITE){
            direction=-1;
            opponent= FrameworkConstants.PLAYER_BLACK;
        }else{ // turn==DARK
            direction=1;
            opponent= FrameworkConstants.PLAYER_WHITE;
        }

        if(!jumpRequired){
            if(position[y][x]== FrameworkConstants.BLANK_POSITION && y == selection.width+direction && (x == selection.height+1 || x == selection.height-1)){
                return 1;
            }

            if(pieceSelected == CheckersConstants.WHITE_KING || pieceSelected == CheckersConstants.BLACK_KING){
                if(position[y][x]== FrameworkConstants.BLANK_POSITION && y == selection.width-direction && (x == selection.height+1 || x == selection.height-1)){
                    return 1;
                }
            }
        }

        if( position[y][x]== FrameworkConstants.BLANK_POSITION && y == selection.width+(direction*2) &&
           ((x == selection.height+2 && position[selection.width+direction][selection.height+1]==opponent) ||
            (x == selection.height-2 && position[selection.width+direction][selection.height-1]==opponent))){
            return 2;
        }

        if(pieceSelected == CheckersConstants.WHITE_KING || pieceSelected == CheckersConstants.BLACK_KING){
            if(position[y][x]== FrameworkConstants.BLANK_POSITION && y == selection.width-(direction*2) &&
              ((x == selection.height+2 && position[selection.width-direction][selection.height+1]==opponent) ||
              (x == selection.height-2 && position[selection.width-direction][selection.height-1]==opponent))){
                return 2;
            }
        }
        return 0;
    }
}
