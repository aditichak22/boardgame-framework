package ClientServer;

import java.io.*;
import java.util.Hashtable;
import java.util.Vector;

public class Account implements Serializable, Cloneable{
	private String username;  // account username
	private String password;  // account password
	private Vector<int[]> records;  // vector containing an array of records for each gametype
	private static Hashtable<String, Account> database = new Hashtable<>();

	public Account(String inName, String inPassword){
		username = inName;
		password = inPassword;
	}

	public String getName(){
		return username;
	}

	public static void loadAccounts(){
		File extFile = new File("ClientServer/accounts.obj");
		if(extFile.exists()){
			FileInputStream inFile;
			try{
				inFile = new FileInputStream(extFile);
				ObjectInputStream inStream = new ObjectInputStream(inFile);
				database = (Hashtable)inStream.readObject();
			} catch (IOException | ClassNotFoundException e){
				e.printStackTrace();
			}
		}
	}

	public static void saveAccounts(){
		FileOutputStream outFile;  // the below is file saving
		try{
			outFile = new FileOutputStream("ClientServer/accounts.obj");
			ObjectOutputStream outStream = new ObjectOutputStream(outFile);
			outStream.writeObject(database);
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public static boolean validate(Account inAccount){
		Account temp = database.get(inAccount.getName());
		if(temp!=null){
			return temp.password.equals(inAccount.password);
		}
		return false;
	}

	public static void addAccount(Account inAccount){
		database.put(inAccount.username, inAccount);
	}

	public static boolean exists(Account inAccount){
		return (database.containsKey(inAccount.getName()));
	}
	
	public static Account retrieve(String key){
		return database.get(key);
	}

	public Account clone(){
		Account clonedAccount = new Account("temp", "temp");
		clonedAccount.username = this.username;
		clonedAccount.password = this.password;
		return clonedAccount;
	}
}
