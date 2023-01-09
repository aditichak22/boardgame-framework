package ClientServer;

import Abstract.AbstractClient;
import Abstract.AbstractController;
import Constants.CommunicationConstants;
import Controllers.CheckersController;
import Controllers.ChessController;
import Controllers.ReversiController;
import Views.CheckersView;
import Views.ChessView;
import Views.ReversiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URI;

public class Client extends AbstractClient{

	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Image frameIcon = tk.getImage("src/main/resources/Reversi/othelloLight.GIF");
	private final JPanel boardPanel = new JPanel();
	private JPanel hudPanel = new JPanel();  				// Addition
	private JPanel rulePanel = new JPanel();
	private JLabel showLoginPlayer = new JLabel("");
	private JLabel showRulesLabel = new JLabel("Show Rules");
	private JButton showRules = new JButton("Rules");
	private JPanel winlossPanel = new JPanel();
	private JPanel timerPanel = new JPanel();
	private JLabel winlossLabel = new JLabel("Win-Loss Record: GAH!");
	private JLabel timerLabel = new JLabel("Timer");
	private JLabel statusLabel = new JLabel("Status");
//	private JTextArea textArea = new JTextArea();

	public static final int REVERSI = 0;
	public static final int CHECKERS = 1;
	public static final int CHESS = 2;

	private int game_type = -1;

	public int getGametype(){
		return game_type;
	}

	public void setGametype(int inGameType){
		game_type = inGameType;
	}

	public Client(String host, int port){
		super.setTitle("[8x8] Board Game Framework");
		this.setSize(600,675);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setupGUI();
		connect(host, port);
	}


	@Override
	public void updateTurn(int turn){
		showRulesLabel.setText("Turn "+ (turn == 1 ? "White" : "Black"));
	}
	@Override
	public void updateLogin(String name){
		showLoginPlayer.setText("Player: "+name);
	}

	public void setClock(String updatedClock){
		timerLabel.setText(updatedClock);
	}

	public void setStatus (String status){
		statusLabel.setText(status);
	}

	public void setupGUI(){
		setIconImage(frameIcon);
		Container cP = getContentPane();
		cP.add(boardPanel, BorderLayout.CENTER);
		timerPanel.add(timerLabel);
		timerPanel.add(statusLabel);
		hudPanel.add(winlossPanel, BorderLayout.WEST);
		hudPanel.add(timerPanel, BorderLayout.EAST);
		rulePanel.add(showLoginPlayer);
		rulePanel.add(showRulesLabel);
		showRules.setActionCommand("show_rules");
		showRules.addActionListener(this);
		rulePanel.add(showRules);
		cP.add(hudPanel, BorderLayout.SOUTH);
		cP.add(rulePanel, BorderLayout.NORTH);
		boardPanel.setLayout(new GridLayout(8, 8));
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				board_position[i][j] = new JButton("");
				board_position[i][j].addActionListener(this);
				board_position[i][j].setIcon(ReversiView.emptyIcon);
				board_position[i][j].setActionCommand("" + i + "" + j);
				boardPanel.add(board_position[i][j]);
			}
		}
	}
	
	public void actionPerformed(ActionEvent ae){
		String command = ae.getActionCommand();

		if(command.equals("show_rules")) {
			try {
				String rulesURL = this.getGametype() == REVERSI ? "https://en.wikipedia.org/wiki/Reversi#Rules"
						: this.getGametype() == CHECKERS ? "https://en.wikipedia.org/wiki/Checkers"
						: "https://en.wikipedia.org/wiki/Rules_of_chess";
				URI uri = new URI(rulesURL);
				Desktop dt = Desktop.getDesktop();
				dt.browse(uri.toURL().toURI());
			} catch (Exception e) {
				System.out.println("Can't able to open the rule!");
			}

		}

		if(game_started && !command.equals("show_rules")){
			int row = Integer.parseInt("" + command.charAt(0));
			int col = Integer.parseInt("" + command.charAt(1));
			Dimension dim = new Dimension(row,col);
			Communication move = new Communication(CommunicationConstants.HANDLE_EVENT_FROM_CLIENT, dim);
			try{
				output_stream.writeObject(move);
				System.out.println("Move sent.");
			}catch (IOException ioe){
				JOptionPane.showMessageDialog(this, "\nConnection to Server Lost - Closing");
				System.exit(0);
			}
		}
	}

	public boolean endGameSequence(String endMessage){
		Object[] options = {"Yes", "No"};
		int selection = JOptionPane.showOptionDialog(null, endMessage +
				"\nWould you like to play again?", 
				"Match has ended", JOptionPane.DEFAULT_OPTION,	
				JOptionPane.WARNING_MESSAGE, null, options, options[0]);

		return selection == 0;
	}

	public AbstractController selectGame(){
		Object[] options = {"Reversi", "Chess", "Checkers"};
		String message = "Please select a game:";
		String title = "Game selection";
		int selection = JOptionPane.showOptionDialog(null,message ,title,
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		if(selection==0){
			setGametype(REVERSI);
			showRulesLabel.setText("Playing: REVERSI");
			return new ReversiController();
		}else if(selection==1){
			setGametype(CHESS);
			showRulesLabel.setText("Playing: CHESS");
			return new ChessController();
		}else if(selection==2){
			setGametype(CHECKERS);
			showRulesLabel.setText("Playing: CHECKERS");
			return new CheckersController();
		}
		showRulesLabel.setText("Playing: ");
		return null;
	}


	public void updateBoard(AbstractController updatedBoard){
		if(this.getGametype()==REVERSI){
			ReversiView.setupBoard(updatedBoard);
		}else if(this.getGametype()==CHESS){
			ChessView.setupBoard(updatedBoard);
		}else if(this.getGametype()==CHECKERS){
			CheckersView.setupBoard(updatedBoard);
		}
	}

	public void connect(String host, int port){
		try{
			Socket connection = new Socket(host, port);
			output_stream = new ObjectOutputStream(connection.getOutputStream());
			input_stream = new ObjectInputStream(connection.getInputStream());
			start();
		}catch(IOException ioe) {
			String message = "Can't able to reach the server.\n Please contact server admin.";
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);
		}
	}

	public void start(){
		Thread runner = new Thread(this);
		runner.start();
	}

	public static void main(String[] args){
		String host = JOptionPane.showInputDialog("Enter Server IP Address:");
		int port=8080;
		new Client(host, port);
	}

}
