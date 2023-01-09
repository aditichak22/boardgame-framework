package Models;

import Constants.FrameworkConstants;
import Controllers.ReversiController;

public class ReversiModel {

    private static int numDark; // the number of dark pieces
    private static int numLight; // the number of light pieces

    public static int getNumDark() {
        return numDark;
    }

    public static void setNumDark(int numDark) {
        ReversiModel.numDark = numDark;
    }

    public static int getNumLight() {
        return numLight;
    }

    public static void setNumLight(int numLight) {
        ReversiModel.numLight = numLight;
    }


    //Marks the cell and increments the turn.
    public static void markCell(ReversiController reversi_controller, int row, int col, int value){
        if ((row>=0) && (row<=7) && (col>=0) && (col<=7) ){
            if ((value == FrameworkConstants.PLAYER_WHITE) || (value == FrameworkConstants.PLAYER_BLACK)){
                reversi_controller.position[row][col] = value;
                reversi_controller.turn = (reversi_controller.turn % 2) + 1;
            }
        }
    }



    //counts the number of dark and light pieces
    public static void countPieces(int[][] position){
        numDark=0;
        numLight=0;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(position[i][j]== FrameworkConstants.PLAYER_BLACK){
                    numDark++;
                }else if(position[i][j]== FrameworkConstants.PLAYER_WHITE){
                    numLight++;
                }
            }
        }
    }

    //Checks if there are legit moves possible.
    public static boolean legitMove(ReversiController reversi_controller){
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                // cellRefresh();
                if(reversi_controller.position[i][j] == FrameworkConstants.BLANK_POSITION){
                    if(ReversiController.placeMove(reversi_controller, reversi_controller.tempPosition, i, j)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
