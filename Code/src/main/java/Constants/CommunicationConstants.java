package Constants;

public class CommunicationConstants {

    //Marking the start of the game after clients matched up
    public static final int CLIENT_START_GAME = 1;

    //Waiting for another player to connect
    public static final int CLIENT_WAITING_FOR_GAME = 2;

    //Informing client if the other client has disconnected.
    public static final int CLIENT_INTERRUPTED_FOR_DISCONNECT = 3;

    public static final int ACCOUNT_IS_INVALID = 5;

    public static final int ACCOUNT_IS_VALID = 6;

    //Indicates the game board is being sent between the client and the server.
    public static final int GAMEBOARD = 11;

    //server to take action for the event on the client side
    public static final int HANDLE_EVENT_FROM_CLIENT = 12;

    public static final int GAME_ENDED = 13;

    //Message from client send to server then to both clients.
    public static final int MESSAGE_OTHER_CLIENT = 14;

    public static final int EXISTING_ACCOUNT_FOUND = 16;

    public static final int NEW_ACCOUNT_CREATED = 17;

}
