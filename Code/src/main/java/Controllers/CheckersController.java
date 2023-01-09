package Controllers;

import Abstract.AbstractController;
import ClientServer.Client;
import Constants.CheckersConstants;
import Constants.FrameworkConstants;
import Models.CheckersModel;

import java.awt.Dimension;
import java.io.Serializable;

public class CheckersController extends AbstractController implements Serializable, Cloneable{

	public CheckersController(){
		position = new int[8][8];
		tempPosition = new int[8][8];
		pieceSelected = FrameworkConstants.BLANK_POSITION;
		selection = new Dimension();
		gameType = Client.CHECKERS;
		resetBoard();
	}
	
	public boolean moveSequence(Dimension move){
		int x = move.height;
		int y = move.width;
		boolean jumpCheck = CheckersModel.jumpPossible(position, tempPosition, turn);
//		System.out.println(jumpCheck);
		if(pieceSelected== FrameworkConstants.BLANK_POSITION){ // no piece already selected
			if(position[y][x]== FrameworkConstants.BLANK_POSITION){
				return false;
			}
			
			if((position[y][x]== FrameworkConstants.PLAYER_WHITE && turn== FrameworkConstants.PLAYER_WHITE) || (position[y][x]== FrameworkConstants.PLAYER_BLACK && turn== FrameworkConstants.PLAYER_BLACK)){
				if(!jumpCheck || (jumpCheck && CheckersModel.canJump(position, tempPosition, x,y))){ // if there is a jump possibility, check if the piece can jump
					pieceSelected = tempPosition[y][x];
					selection = move;
					return true;
				}
			}else{
				return false;
			}
		}else{ // piece already selected
			if(x-selection.height==0 && y-selection.width==0){
				pieceSelected= FrameworkConstants.BLANK_POSITION;
				return true;
			}if(position[y][x]!= FrameworkConstants.BLANK_POSITION){ // trying to select an already occupied space
				return false;
			}
			int moveType = CheckersModel.legitMove(pieceSelected, position, selection, turn, x, y, jumpCheck);
			if(moveType>0){ // check for legit move, apply move changes if legit
				boolean kinged = ((turn== FrameworkConstants.PLAYER_WHITE && y==0)||(turn== FrameworkConstants.PLAYER_BLACK && y==7));
				position[y][x]=turn;
				position[selection.width][selection.height]= FrameworkConstants.BLANK_POSITION;
				if(!kinged){
					tempPosition[y][x]=pieceSelected;
				}else{
					if(turn== FrameworkConstants.PLAYER_WHITE){
						tempPosition[y][x]= CheckersConstants.WHITE_KING;
					}else{
						tempPosition[y][x]= CheckersConstants.BLACK_KING;
					}
				}
				tempPosition[selection.width][selection.height]= FrameworkConstants.BLANK_POSITION;
				if(moveType==1){
					pieceSelected = FrameworkConstants.BLANK_POSITION;
					turn = (turn%2)+1;
				}
				else if(moveType==2){
					position[(selection.width+y)/2][(selection.height+x)/2]= FrameworkConstants.BLANK_POSITION;
					tempPosition[(selection.width+y)/2][(selection.height+x)/2]= FrameworkConstants.BLANK_POSITION;

					if(!kinged && CheckersModel.canJump(position, tempPosition, x, y)){
						selection= new Dimension(y, x);
					}else{
						pieceSelected = FrameworkConstants.BLANK_POSITION;
						turn = (turn%2)+1;
					}
				}
				return true;
			}
		}
		return false; // dummy value
	}


	public int checkWinner(){
		int numLight = 0;
		int numDark = 0;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(position[i][j]== FrameworkConstants.PLAYER_WHITE){
					numLight++;
				}
				if(position[i][j]== FrameworkConstants.PLAYER_BLACK){
					numDark++;
				}
			}
		}
		if(numLight==0){
			return FrameworkConstants.PLAYER_BLACK;
		}
		if(numDark==0){
			return FrameworkConstants.PLAYER_WHITE;
		}
		return FrameworkConstants.BLANK_POSITION;
	}

	public void noLegitMoves(){

	}
	
	public void resetBoard(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				position[i][j] = FrameworkConstants.BLANK_POSITION;
				tempPosition[i][j] = FrameworkConstants.BLANK_POSITION;
			}
		}
		for(int j=0; j<8; j+=2) {
			position[7][j] = FrameworkConstants.PLAYER_WHITE;
			tempPosition[7][j] = CheckersConstants.WHITE_MAN;
			position[6][j+1] = FrameworkConstants.PLAYER_WHITE;
			tempPosition[6][j+1] = CheckersConstants.WHITE_MAN;
			position[5][j] = FrameworkConstants.PLAYER_WHITE;
			tempPosition[5][j] = CheckersConstants.WHITE_MAN;
			position[0][j+1] = FrameworkConstants.PLAYER_BLACK;
			tempPosition[0][j+1] = CheckersConstants.BLACK_MAN;
			position[1][j] = FrameworkConstants.PLAYER_BLACK;
			tempPosition[1][j] = CheckersConstants.BLACK_MAN;
			position[2][j+1] = FrameworkConstants.PLAYER_BLACK;
			tempPosition[2][j+1] = CheckersConstants.BLACK_MAN;
		}
		
		turn = FrameworkConstants.PLAYER_BLACK;
	}
	
	public AbstractController clone(){
		AbstractController clonedBoard = new CheckersController();
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++) {
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
