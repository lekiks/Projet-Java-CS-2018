/*
Client of Samcar
 */

/**
 *
 * @author corentinbourlet
 */


package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.* ; 




public class Client {
	
	public static String VALIDATION = "VALIDATION" ;
	public static String ERREUR = "ERREUR";
	
	private String address;
	private int port;
	Socket clientSocket;
	DataInputStream input ;
	DataOutputStream output ;
	String msgIn;
	String msgOut;
	
	//UserProfil user;

	
	String getMessage() {
		try {
			input = new DataInputStream(clientSocket.getInputStream()) ;
			String msg = input.readUTF();
			return msg; 
		}
		catch (IOException e )
		{
			e.printStackTrace();
			return null;
		} 
	}
	
	void sendMessage(String msg) {
		try {
		output = new DataOutputStream(clientSocket.getOutputStream()) ;
		output.writeUTF(msg);
		}
		catch (IOException e )
		{
			e.printStackTrace();
		} 
	}

	void serverConnection() {
		try
		{
			clientSocket= new Socket(address,port);
			System.out.println("socket créée");
		}
		catch (IOException e )
		{
			e.printStackTrace();
		} 
	}
	
	
	boolean validation(String msg) {
		if (msg == VALIDATION) {
			return true;
		}
		else {
			return false;
		} 
	}


	boolean identification(String username,String password) {
		//envoie username 
		this.sendMessage(username+";"+password);
		return validation(this.getMessage());
	}

	//classe permettant de créé un compte
//	boolean profilCreation(UserProfil usr) {
		//this.sendMessage( user profil )
//		return validation(this.getMessage());
//	}
	
	//classe choississant une requete (choice from button)
	boolean requeteChoice(String choice) {
		this.sendMessage(choice);
		return validation(this.getMessage());
	}
	
	//validation du covoiturage
	boolean validationAd(String choice) {
		this.sendMessage(choice);
		return validation(this.getMessage());
	}

	//creation d'une proposition
	boolean adCreation(Proposition proposition) {
		this.sendMessage(proposition.nbPersonnes);
		return validation(this.getMessage());
	}


	// choix d'une proposition faite par un utilisateur
	void joinAd() {

	}

	Client(String address,int port) {
		this.address = address;
		this.port=port;
		this.serverConnection();

		//close connection

	}

}
