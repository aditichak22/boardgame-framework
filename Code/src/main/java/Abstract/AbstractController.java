package Abstract;

import java.awt.Dimension;
import java.io.Serializable;

public abstract class AbstractController implements Serializable, Cloneable{

	public int[][] position;	//board position
	public int[][] tempPosition; //temporary board position storage
	public int gameType;	 //boardgame type
	public int turn; 	//current player turn value
	public int pieceSelected;
	public Dimension selection;

	public abstract void resetBoard();
	public abstract boolean moveSequence(Dimension move);
	public abstract void noLegitMoves();
	public abstract int checkWinner();
	public abstract AbstractController clone();

	public int getGameType(){
		return gameType;
	}

	public int getPieceSelected(){
		return pieceSelected;
	}

	public Dimension getSelection(){
		return selection;
	}

	public int getCell(int row, int col){
		return position[row][col];
	}

	public int[][] getTempPosition(){
		return tempPosition;
	}

	public int getTurn(){
		return turn;
	}
}
