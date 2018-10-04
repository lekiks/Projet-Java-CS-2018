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
    
            int code = input.readInt();
            codeCheck(code, output);
            caseSelection(code, input, output);   
        } catch (IOException ex) {   
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void codeCheck(int code, DataOutputStream output) throws IOException {
    	if ((code >= 0)&&(code>6)) {
            output.writeBoolean(true);
    	}
        else{
            output.writeBoolean(false);
        }
    }
    
    private void caseSelection(int code,DataInputStream input, DataOutputStream output) throws IOException {
    switch(code) {
	case 0:{
            String pseudo,password;
            pseudo = input.readUTF();
            password = input.readUTF();
            acknowledgement(connection(pseudo,password),output);
        }
	case 1:{
            UserProfil newProfil = new UserProfil();
            byte[] b = null;
            int i = 0;
            i = input.read(b);
            try {
                newProfil.deserialize(b);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            acknowledgement(true,output);
        }
	case 2:
            Advert ad = new Advert();
            byte[] b = null;
            int i = 0;
            i = input.read(b);
            try {
                ad.deserialize(b);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            acknowledgement(true,output);
	case 3:
            output.writeInt(listAdverts.size());
            for(Advert a: listAdverts){
                if(a.checkSize()){
                    output.write(a.serialize(), 0, a.serialize().length);
                }
            }
            String eventChose = null;
            eventChose = input.readUTF();
            acknowledgement(true,output);
	case 4:
            
	case 5:
			//eventCreate();
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
    
    private boolean connection(String pseudo,String password){
        for(UserProfil p: listAllUsers){
            return (p.getPseudo().equals(pseudo))&&(p.getPassword().equals(password));
        }
        return false;
    }
    
    private boolean addUser(String adName){
        for(Advert a: listAdverts){
            if(a.getAdName() == adName){
                
            }
        }
        return false;
    }
    
    private void acknowledgement(boolean condition, DataOutputStream output){
        try{
            if (condition){
                output.writeBoolean(true);
                } 
            else{
                output.writeBoolean(false);
            }
        } catch (IOException ex) {   
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
