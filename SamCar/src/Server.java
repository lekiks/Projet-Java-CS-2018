/*
Server of Samcar
 */

/**
 *
 * @author hadrienjanicot
 */

//Imports list
import java.net.* ; // Sockets
import java.util.ArrayList;
import java.util.List;
import java.io.* ; // Streams
import java.util.logging.Level;
import java.util.logging.Logger;

//Creation d'un serveur Singleton

final public class Server {

	public static String VALIDATION= "VALIDATION";
	private static Server svr ; 
	private int port = 11002;
	ServerSocket sock;
	private final int maximumClientNumber = 10000;
	private int currentClientNumber = 0;

	private Server(){
		System.out.println("le serveur lancé sur port "+port);
		waitConnection();
	}

	private void waitConnection(){
		try {
			sock = new ServerSocket(port);
			while (currentClientNumber < maximumClientNumber) {
				Socket socket = sock.accept();
				currentClientNumber ++;
				Thread th = new Thread(() -> {clientFlow(socket);});
				th.start();
			}
		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}     
	}

	private void clientFlow(Socket socket){
		DataInputStream input;
		DataOutputStream output;
		try {
			//deal with states
			
			
			//Creation des flux entrées/sorties du client
			input = new DataInputStream(socket.getInputStream());
			String msgIn = input.readUTF();
			System.out.println(msgIn);
			output = new DataOutputStream(socket.getOutputStream());
			output.writeUTF(VALIDATION);

		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static Server getSvr () { 
		if (svr == null)
			svr = new Server() ; 
		return svr ;
	}

	public void setPort(int p){port = p;}
}
