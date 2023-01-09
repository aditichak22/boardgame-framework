package ClientServer;

import java.io.Serializable;

public class Communication implements Serializable{
	private int type;  // the type of message being sent
	private Object message;  // the message itself

	public Communication(int inType) throws IllegalArgumentException{
		type = inType;
	}

	public Communication(int inType, Object inMessage){
		type = inType;
		message = inMessage;
	}

	public int getType(){
		return type;
	}

	public Object getMessage(){
		return message;
	}
	
}
