package Controllers;

import Abstract.AbstractController;
import ClientServer.Client;
import Constants.ChessConstants;
import Constants.FrameworkConstants;
import Models.ChessModel;

import java.awt.Dimension;
import java.io.Serializable;

public class ChessController extends AbstractController implements Serializable, Cloneable {


	public ChessController() {
		position = new int[8][8];
		tempPosition = new int[8][8];
		pieceSelected = FrameworkConstants.BLANK_POSITION;
		selection = new Dimension();
		gameType = Client.CHESS;
		resetBoard();
	}
	

	/**
	 * Handles the sequence of checking a move and applying it if valid.
	 * @param move - the new location selected
	 * @return - if a change to the board was successfully made (piece selected or moved)
	 */	
	public boolean moveSequence(Dimension move){
		System.out.println("Checking move");
		ChessModel.setX(move.height);
		ChessModel.setY(move.width);
		
		if(pieceSelected== FrameworkConstants.BLANK_POSITION){ // no piece already selected
			if(position[ChessModel.getY()][ChessModel.getX()]== FrameworkConstants.BLANK_POSITION){
				return false;
			}

			if((position[ChessModel.getY()][ChessModel.getX()]== FrameworkConstants.PLAYER_WHITE && turn== FrameworkConstants.PLAYER_WHITE) || (position[ChessModel.getY()][ChessModel.getX()]== FrameworkConstants.PLAYER_BLACK && turn== FrameworkConstants.PLAYER_BLACK)){
				pieceSelected = tempPosition[ChessModel.getY()][ChessModel.getX()];
				selection = move;
				return true;
			}else{
				return false;
			}
		}else{ // piece already selected
			if(ChessModel.getX()-selection.height==0 && ChessModel.getY()-selection.width==0){ // undo selection
				pieceSelected= FrameworkConstants.BLANK_POSITION;
				return true;
			}
			
			if(position[ChessModel.getY()][ChessModel.getX()]==turn){ // new piece selected is one of the same side
				if(!((tempPosition[selection.width][selection.height]== ChessConstants.WHITE_KING &&
						tempPosition[ChessModel.getY()][ChessModel.getX()]== ChessConstants.WHITE_ROOK) ||
						(tempPosition[selection.width][selection.height]== ChessConstants.BLACK_KING &&
						tempPosition[ChessModel.getY()][ChessModel.getX()]== ChessConstants.BLACK_ROOK))){
					return false;
				}
			}
			
			if(ChessModel.legitMove(this)){ // check for legit move, apply move changes if legit
				if(ChessModel.isCastled()){
					int direction = ChessModel.getX()>selection.height ? 1 : -1;
					int kingside;
					int rookside;
					if(turn== FrameworkConstants.PLAYER_WHITE){
						kingside= ChessConstants.WHITE_KING;
						rookside= ChessConstants.WHITE_ROOK;
					}
					else{ // turn==DARK
						kingside= ChessConstants.BLACK_KING;
						rookside= ChessConstants.BLACK_ROOK;
					}
					tempPosition[selection.width][selection.height+(direction*2)]=kingside;
					tempPosition[selection.width][selection.height+direction]=rookside;
					position[selection.width][selection.height+(direction*2)]=turn;
					position[selection.width][selection.height+direction]=turn;
					tempPosition[selection.width][selection.height]= FrameworkConstants.BLANK_POSITION;
					tempPosition[ChessModel.getY()][ChessModel.getX()]= FrameworkConstants.BLANK_POSITION;
					position[selection.width][selection.height]= FrameworkConstants.BLANK_POSITION;
					position[ChessModel.getY()][ChessModel.getX()]= FrameworkConstants.BLANK_POSITION;
					ChessModel.setCastled(false);
					pieceSelected = FrameworkConstants.BLANK_POSITION;
					turn = (turn%2)+1;
					return true;
				}
				position[ChessModel.getY()][ChessModel.getX()] = turn;
				tempPosition[ChessModel.getY()][ChessModel.getX()] = pieceSelected;
				position[selection.width][selection.height] = FrameworkConstants.BLANK_POSITION;
				tempPosition[selection.width][selection.height] = FrameworkConstants.BLANK_POSITION;
				pieceSelected = FrameworkConstants.BLANK_POSITION;
				turn = (turn%2)+1;
				return true;
			}
			else{
				return false;
			}
		}		
	}


	/**
	 * Not used here.
	 */
	public void noLegitMoves()
	{
		
	}
	
	/**
	 * Determines the winner by looking at each King's health.
	 */
	public int checkWinner(){
		boolean lightKingAlive=false;
		boolean darkKingAlive=false;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(tempPosition[i][j]== ChessConstants.WHITE_KING){
					lightKingAlive=true;
				}

				if(tempPosition[i][j]== ChessConstants.BLACK_KING){
					darkKingAlive=true;
				}
			}
		}
		if(!lightKingAlive){
			return FrameworkConstants.PLAYER_BLACK;
		}else if(!darkKingAlive){
			return FrameworkConstants.PLAYER_WHITE;
		}
		return FrameworkConstants.BLANK_POSITION; // dummy value
	}
	
	public void resetBoard(){
		for(int i=2; i<6; i++){
			for(int j=0; j<8; j++){ // middle of board cleared
				position[i][j] = FrameworkConstants.BLANK_POSITION;
				tempPosition[i][j] = FrameworkConstants.BLANK_POSITION;
			}
		}

		// set pawns
		for(int j=0; j<8; j++){
			position[7][j] = FrameworkConstants.PLAYER_WHITE;
			position[6][j] = FrameworkConstants.PLAYER_WHITE;
			tempPosition[6][j] = ChessConstants.WHITE_PAWN;
		}

		for(int j=0; j<8; j++){
			position[0][j] = FrameworkConstants.PLAYER_BLACK;
			position[1][j] = FrameworkConstants.PLAYER_BLACK;
			tempPosition[1][j] = ChessConstants.BLACK_PAWN;
		}

		// get all the important (non-pawn) pieces set
		tempPosition[7][0] = tempPosition[7][7] = ChessConstants.WHITE_ROOK;
		tempPosition[0][0] = tempPosition[0][7] = ChessConstants.BLACK_ROOK;
		tempPosition[7][1] = tempPosition[7][6] = ChessConstants.WHITE_KNIGHT;
		tempPosition[0][1] = tempPosition[0][6] = ChessConstants.BLACK_KNIGHT;
		tempPosition[7][2] = tempPosition[7][5] = ChessConstants.WHITE_BISHOP;
		tempPosition[0][2] = tempPosition[0][5] = ChessConstants.BLACK_BISHOP;
		tempPosition[7][3] = ChessConstants.WHITE_QUEEN;
		tempPosition[0][3] = ChessConstants.BLACK_QUEEN;
		tempPosition[7][4] = ChessConstants.WHITE_KING;
		tempPosition[0][4] = ChessConstants.BLACK_KING;
		
		turn = FrameworkConstants.PLAYER_WHITE;
	}
	
	
	public AbstractController clone(){
		ChessController clonedBoard = new ChessController();
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				clonedBoard.position[i][j] = this.position[i][j];
				clonedBoard.tempPosition[i][j] = this.tempPosition[i][j];
			}
		}
		clonedBoard.turn = this.turn;  // turn is located in the parent class
	
		clonedBoard.pieceSelected = this.pieceSelected;
		clonedBoard.selection = this.selection;
		clonedBoard.gameType = this.gameType;
				
		// check for other stuff to copy		
		return clonedBoard;
	}
}
