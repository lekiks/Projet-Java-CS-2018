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
    
    private static Server svr ; 
    private int port = 11000;
    ServerSocket sock;
    private final int maximumClientNumber = 10000;
    private int currentClientNumber = 0;
    private List<UserProfil>  listAllUsers;
    private List<Event> listEvents;
    private List<Advert> listAdverts;
    
    private Server(){
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
        try {
            
            //Creation des flux entrées/sorties du client
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addAdvert(Advert ad){
        listAdverts.add(ad);
    }
    
    private void addUser(UserProfil user){
        listAllUsers.add(user);
    }
    
    private void addEvent(Event e){
        listEvents.add(e);
    }
    public static Server getSvr () { 
		if (svr == null)
			svr = new Server() ; 
		return svr ;
    }

    public void setPort(int p){
        port = p;
    }
}
