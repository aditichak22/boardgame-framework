package ClientServer;

import Abstract.AbstractController;
import Constants.CommunicationConstants;
import Constants.FrameworkConstants;

import java.awt.*;
import java.net.*;
import java.util.Enumeration;
import java.util.Vector;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Server extends Thread implements Cloneable{

	private ObjectOutputStream output_stream = null;

	private ObjectInputStream input_stream = null;

	private int player_number;

	private int match_number;

	private boolean is_playing;

	private int game_type = -1;

	private int client_number;

	AbstractController game_board = null;


	private static Vector<AbstractController> board_games = new Vector(10,10);

	private static Vector<Server> lobby = new Vector(0,1);

	private static Vector<Boolean> lobby_state = new Vector(0,1);

	private static Vector<Server> white_players = new Vector(10,10);

	private static Vector<Server> black_players = new Vector(10,10);

	public Server(Socket incoming_connection, int player_number){
		this.player_number = player_number;
		try{
			output_stream = new ObjectOutputStream(incoming_connection.getOutputStream());
			input_stream = new ObjectInputStream(incoming_connection.getInputStream());
			is_playing = incoming_connection.isConnected();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

	public void run(){
		while(is_playing){
			try{
				Communication incoming = (Communication) input_stream.readObject();
				Communication outgoing;

				if(board_games.size()> match_number){
					game_board = board_games.get(match_number);
				}

				Account account;
				if(incoming.getType() == CommunicationConstants.HANDLE_EVENT_FROM_CLIENT){
					if (player_number == game_board.getTurn()){
						if(game_board.moveSequence((Dimension)incoming.getMessage())){
							game_board = game_board.clone();
							outgoing = new Communication(CommunicationConstants.GAMEBOARD, game_board);
							sendPacketToBoth(outgoing);
						}
					} 
				}else if(incoming.getType() == CommunicationConstants.EXISTING_ACCOUNT_FOUND){
					account = (Account)incoming.getMessage();
					if(Account.validate(account)){
						account = Account.retrieve(account.getName());
						outgoing = new Communication(CommunicationConstants.ACCOUNT_IS_VALID, account);
						sendPacket(outgoing);
						System.out.println("Valid Account");
					}else{
						outgoing = new Communication(CommunicationConstants.ACCOUNT_IS_INVALID);
						sendPacket(outgoing);
						System.out.println("Invalid Account!");
					}
				}else if(incoming.getType() == CommunicationConstants.NEW_ACCOUNT_CREATED){
					account = (Account)incoming.getMessage();
					if(Account.exists(account)){
						outgoing = new Communication(CommunicationConstants.ACCOUNT_IS_INVALID);
						sendPacket(outgoing);
						System.out.println("Account already exists!");
					}else{
						Account.addAccount(account);
//						Account.saveAccounts();
						outgoing = new Communication(CommunicationConstants.ACCOUNT_IS_VALID);
						sendPacket(outgoing);
						System.out.println("Account added!");
					}
				}else if(incoming.getType() == CommunicationConstants.GAMEBOARD){
					game_board = (AbstractController)incoming.getMessage();
					game_type = game_board.getGameType();
					lobby_state.set(client_number, true);
					lobbyCheck(client_number);
				}


				if(board_games.size()> match_number){
					int winner = game_board.checkWinner();
					if(winner!= FrameworkConstants.BLANK_POSITION){
						if(winner== FrameworkConstants.PLAYER_BLACK){
							String message = "The Winner is Player Black!";
							outgoing = new Communication(CommunicationConstants.GAME_ENDED, message);
							sendPacketToBoth(outgoing);
						}
						if(winner== FrameworkConstants.PLAYER_WHITE){
							String message = "The Winner is Player White!";
							outgoing = new Communication(CommunicationConstants.GAME_ENDED, message);
							sendPacketToBoth(outgoing);
						}
						if(winner== FrameworkConstants.GAME_DRAW){
							String message = "Game Draw!";
							outgoing = new Communication(CommunicationConstants.GAME_ENDED, message);
							sendPacketToBoth(outgoing);
						}
					}
				}

			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
				is_playing = false;
				System.out.println("Seems like clients disconnected!");
				Communication interrupted = new Communication(CommunicationConstants.CLIENT_INTERRUPTED_FOR_DISCONNECT);
				sendPacketToBoth(interrupted);
				lobby.set(client_number, null);
				lobby_state.set(client_number, false);
			}
		}		
	}

	public void sendPacket(Communication message){

		try{
			output_stream.writeObject(message);
		}catch(IOException ioe){
			System.out.println("Message could not be passed. Client Disconnected!");
			is_playing = false;
		}
	}
	
	public void sendPacketToBoth(Communication message){

		if(black_players.size()> match_number){
			black_players.get(match_number).sendPacket(message);
		}

		if(white_players.size()> match_number){
			white_players.get(match_number).sendPacket(message);
		}

	}

	private static void lobbyCheck(int client){

		for(int players_waiting = 0; players_waiting<lobby.size()-1; players_waiting++){
			if(players_waiting!=client && lobby_state.get(players_waiting) && lobby.get(client).game_type ==lobby.get(players_waiting).game_type){ 	// match up those two
				int match = white_players.size();

				white_players.add(match, lobby.get(players_waiting));
				black_players.add(match, lobby.get(client));

				lobby_state.set(players_waiting, false);
				lobby_state.set(client, false);

				white_players.get(white_players.size()-1).match_number = match;
				black_players.get(black_players.size()-1).match_number = match;

				white_players.get(white_players.size()-1).player_number = FrameworkConstants.PLAYER_WHITE;
				black_players.get(black_players.size()-1).player_number = FrameworkConstants.PLAYER_BLACK;

				if(match >= board_games.size()){
					System.out.println("New board added");
					board_games.add(match, white_players.get(match).game_board);
				}else{
					System.out.println("Board already exists");
					board_games.set(match, white_players.get(match).game_board);
				}

				AbstractController board = board_games.get(match).clone();
				Communication message = new Communication(CommunicationConstants.GAMEBOARD, board);
				white_players.get(match).sendPacketToBoth(message);
				message = new Communication(CommunicationConstants.CLIENT_START_GAME);
				white_players.get(match).sendPacketToBoth(message);
				return;
			}
		}
	}

	 //Starts the server and listens for connections from clients.
	 public static void main(String[] args){
		Account.loadAccounts();
		boolean canAcceptConnections = true;
		ServerSocket server = null;

		try{
			System.out.println("Trying to start the server....");
			server = new ServerSocket(8080);
		}catch(IOException e){
			System.out.println("Failed to connect to the server!");
			e.printStackTrace();
			canAcceptConnections = false;
		}

		while(canAcceptConnections){
			try{
				try{

					Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();

					while( networkInterfaceEnumeration.hasMoreElements()){
						for ( InterfaceAddress interfaceAddress : networkInterfaceEnumeration.nextElement().getInterfaceAddresses())
							if (interfaceAddress.getAddress().isSiteLocalAddress()){
								System.out.println(interfaceAddress.getAddress().getHostAddress());
							}
					}

				}catch(SocketException e){
					e.printStackTrace();
				}

				Server thread = new Server(server.accept(), 0);
				thread.start();
				thread.client_number = lobby.size();

				lobby.add(thread.client_number, thread);
				lobby_state.add(thread.client_number, false);

			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
