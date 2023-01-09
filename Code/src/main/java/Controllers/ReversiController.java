package Controllers;

import Abstract.AbstractController;
import ClientServer.Client;
import Constants.FrameworkConstants;
import Models.ReversiModel;

import java.awt.Dimension;
import java.io.Serializable;

public class ReversiController extends AbstractController implements Serializable, Cloneable{

	public ReversiController(){
		position = new int[8][8];
		tempPosition = new int[8][8];
		gameType = Client.REVERSI;
		resetBoard();
	}

    //Makes all board changes when a move is placed.
    public static boolean placeMove(ReversiController reversi_controller, int[][] inCells, int i, int j){
        cellRefresh(reversi_controller.position, reversi_controller.tempPosition);

        if(inCells[i][j] != FrameworkConstants.BLANK_POSITION){
            return false;
        }

        boolean retVal = false;
        boolean[] retTemp = new boolean[8];
        retTemp[0] = eachLine(reversi_controller.turn, inCells, i, j, 1, 0);
        retTemp[1] = eachLine(reversi_controller.turn, inCells, i, j, 1, 1);
        retTemp[2] = eachLine(reversi_controller.turn, inCells, i, j, 0, 1);
        retTemp[3] = eachLine(reversi_controller.turn, inCells, i, j, -1, 1);
        retTemp[4] = eachLine(reversi_controller.turn, inCells, i, j, -1, 0);
        retTemp[5] = eachLine(reversi_controller.turn, inCells, i, j, -1, -1);
        retTemp[6] = eachLine(reversi_controller.turn, inCells, i, j, 0, -1);
        retTemp[7] = eachLine(reversi_controller.turn, inCells, i, j, 1, -1);
        for(int x=0; x<8; x++){
            if(retTemp[x]==true){
                retVal=true;
            }
        }
        return retVal;
    }

	public static boolean eachLine(int turn, int[][] inCells, int i, int j, int x, int y){
		int g=i;
		int h=j;
		int steps=0;
		int opponentTurn=(turn %2 +1);

		while((g+x)>=0 && (g+x)<8 && (h+y)>=0 && (h+y)<8){
			g+=x;
			h+=y;
			if(inCells[g][h]==opponentTurn){
				steps++;
			}else{
				break;
			}
		}

		if(steps>0 && inCells[g][h]== turn){
			//change=true;
			while(steps>0){
				i+=x;
				j+=y;
				inCells[i][j]= turn;
				steps--;
			}
			return true;
		}
		return false;
	}

	//Helper function that refreshes the state of tempCells.
	public static void cellRefresh(int[][] position, int[][] tempPosition){
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				tempPosition[i][j]= position[i][j];
			}
		}
	}

	public boolean moveSequence(Dimension move){
		if(ReversiModel.legitMove(this)){  // proceed if a legit move is possible
			if(placeMove(this, tempPosition, move.width, move.height)){
				placeMove(this, position, move.width, move.height);
				ReversiModel.markCell(this, move.width,move.height,getTurn());
				return true;
			}
		}
		
		else{
			noLegitMoves();
		}
		return false;
	}

	public void resetBoard(){
		ReversiModel.setNumDark(0);
		ReversiModel.setNumLight(0);
		turn = FrameworkConstants.PLAYER_BLACK;
		for (int i=0; i<position.length; i++){
			for (int j=0; j<position.length; j++){
				position[i][j] = FrameworkConstants.BLANK_POSITION;
			}
		}
		position[3][3]= FrameworkConstants.PLAYER_WHITE;
		position[4][3]= FrameworkConstants.PLAYER_BLACK;
		position[3][4]= FrameworkConstants.PLAYER_BLACK;
		position[4][4]= FrameworkConstants.PLAYER_WHITE;
	}


	//Checks if there is a winner
	public int checkWinner(){
		ReversiModel.countPieces(position);
		if((ReversiModel.getNumDark()+ ReversiModel.getNumLight()) >= (8*8)){
			if(ReversiModel.getNumDark() > ReversiModel.getNumLight()){
				return FrameworkConstants.PLAYER_BLACK;
			}
			else if(ReversiModel.getNumDark() < ReversiModel.getNumLight()){
				return FrameworkConstants.PLAYER_WHITE;
			}
			else if(ReversiModel.getNumLight() == ReversiModel.getNumDark()){
				return 3;
			}
		}else if(ReversiModel.getNumDark()==0){
			return FrameworkConstants.PLAYER_WHITE;
		}else if(ReversiModel.getNumLight()==0){
			return FrameworkConstants.PLAYER_BLACK;
		}
		return FrameworkConstants.BLANK_POSITION;
	}
	
	/**
	 * Abstract method - implements the cloneable interface to make a copy of the gameboard.
	 * The plugin will determine what board information needs to be saved.
	 */
	public AbstractController clone(){
		ReversiController clonedBoard = new ReversiController();
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++){
				clonedBoard.position[i][j] = this.position[i][j];
			}
		}
		clonedBoard.turn = this.turn;  // turn is located in the parent class
		clonedBoard.gameType = this.gameType;
		return clonedBoard;
	}

	//Skips a turn if there are no legit moves for a player.
	public void noLegitMoves(){
		turn = (turn % 2) + 1;
		System.out.println("No legit move, skipping turn");
	}

}
