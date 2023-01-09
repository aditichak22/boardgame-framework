package Abstract;

import ClientServer.Account;
import ClientServer.Communication;
import ClientServer.Timer;
import Constants.CommunicationConstants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class AbstractClient extends JFrame implements ActionListener, Runnable {

	protected ObjectOutputStream output_stream = null;
	protected ObjectInputStream input_stream = null;
	protected boolean game_started = false;
	boolean is_playing =true;
	public static JButton[][] board_position = new JButton[8][8];

	protected Account account;

	public abstract AbstractController selectGame();
	public abstract void setStatus(String status);
	public abstract void updateBoard(AbstractController updatedBoard);
	public abstract boolean endGameSequence(String endMessage);
	public abstract void setupGUI();
	public abstract void actionPerformed(ActionEvent ae);
	public abstract void setClock(String updatedClock);


	public void updateTurn(int turn) {

	}

	public void updateLogin(String name) {

	}

	//Implementing Runnable Interface
	public void run(){
		Communication outgoing;
		login();
		
		ClientServer.Timer timer = null;
		AbstractController gameBoard;
		
		while(is_playing){
			
			try{
				Communication incoming = (Communication) input_stream.readObject();
				if(incoming.getType() == CommunicationConstants.CLIENT_START_GAME){
					game_started = true;
					timer = new Timer();
					timer.setClient(this);
					timer.setRunning(true);
					timer.start();
					setStatus("Game in progress");
				}else if(incoming.getType() == CommunicationConstants.ACCOUNT_IS_INVALID){
					login();
				}else if(incoming.getType() == CommunicationConstants.ACCOUNT_IS_VALID){
					account = (Account)incoming.getMessage();
					updateLogin(account.getName());
					gameBoard = selectGame();
					setVisible(true);
					gameBoard = gameBoard.clone();
					outgoing = new Communication(CommunicationConstants.GAMEBOARD, gameBoard);
					sendMessage(outgoing);
					setStatus("Waiting for opponent to connect");
				}else if(incoming.getType() == CommunicationConstants.GAMEBOARD){
					AbstractController updatedBoard = (AbstractController)incoming.getMessage();
					updateBoard(updatedBoard);
					updateTurn(updatedBoard.getTurn());
				}else if(incoming.getType() == CommunicationConstants.GAME_ENDED){
					game_started = false;
					String endMessage = (String)incoming.getMessage();
					timer.setRunning(false);
					setStatus("Match ended");
					if(endGameSequence(endMessage)){
						gameBoard = selectGame();
						setVisible(true);
						gameBoard = gameBoard.clone();
						outgoing = new Communication(CommunicationConstants.GAMEBOARD, gameBoard);
						sendMessage(outgoing);
						setStatus("Waiting for Opponent to connect");
					}else{
						System.exit(0);
					}
				}else if(incoming.getType() == CommunicationConstants.CLIENT_INTERRUPTED_FOR_DISCONNECT){
					game_started = false;
					timer.setRunning(false);
					setStatus("Match ended");
					if(endGameSequence("Opponent has forfeited the match!")){
						gameBoard = selectGame();
						setVisible(true);
						gameBoard = gameBoard.clone();
						outgoing = new Communication(CommunicationConstants.GAMEBOARD, gameBoard);
						sendMessage(outgoing);
						setStatus("Waiting for opponent to connect");
					}else{
						System.exit(0);
					}
				}
			}

			catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
			}catch(IOException ioe) {
				is_playing = false;
				JOptionPane.showMessageDialog(this, "Server Disconnected!");
			}
		}
	}


	public void login(){
		boolean messagePassed = false;
		while(!messagePassed){
			Object[] options = {"Existing Account", "New Account"};
			String message = "Select an account type:\n" + "(if this is coming up again, the previous login failed)";
			String title = "Account Selection";

			int selection = JOptionPane.showOptionDialog(null, message ,title,
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

			if(selection==0){
				String user = JOptionPane.showInputDialog("Enter your username:");
				String pass = JOptionPane.showInputDialog("Enter your password:");
				account = new Account(user, pass);
				Communication outgoing = new Communication(CommunicationConstants.EXISTING_ACCOUNT_FOUND, account);
				sendMessage(outgoing);
				messagePassed=true;
			}else if(selection==1){
				String newuser = JOptionPane.showInputDialog("Enter a new username:");
				String newpass1 = JOptionPane.showInputDialog("Enter a password:");
				String newpass2 = JOptionPane.showInputDialog("Retype the password:");
				if(newpass1.equals(newpass2)){
					account = new Account(newuser, newpass1);
					Communication outgoing = new Communication(CommunicationConstants.NEW_ACCOUNT_CREATED, account);
					sendMessage(outgoing);
					messagePassed=true;
				}else{
					JOptionPane.showMessageDialog(this, "Your passwords doesn't match up!");
				}
			}
		}
	}

	public void sendMessage(Communication message){
		try{
			output_stream.writeObject(message);
		}catch(IOException ioe){
			System.out.println("Server Disconnected!");
			is_playing = false;
		}
	}


}
