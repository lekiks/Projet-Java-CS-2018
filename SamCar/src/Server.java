/*
Server of Samcar
 */

/**
 *
 * @author hadrienjanicot
 */

//Imports list
import java.net.* ; // Sockets
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
    File fileUser = new File("/Users/hadrienjanicot/Documents/server/UserProfil.txt");
    File fileAd = new File("/Users/hadrienjanicot/Documents/server/Advert.txt");
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
            
            //Creation des flux entrées/sorties du clientLocal
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
       UserProfil user = new UserProfil();
    switch(code) {
	case 0:{
            String pseudo,password;
            pseudo = input.readUTF();
            password = input.readUTF();
            acknowledgement(connection(pseudo,password,user),output);
        }
	case 1:{
            UserProfil newProfil = new UserProfil();
            byte[] b = null;
            int i = 0;
            i = input.read(b);
            try {
                newProfil.deserialize(b);
                listAllUsers.add(newProfil);
                user = newProfil;
                
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
            acknowledgement(addUser(eventChose,user),output);
	case 4:
            List<Advert> userAdds = addsOwner(user);
            output.writeInt(userAdds.size());
            for(Advert a: userAdds){
                output.write(a.serialize(), 0, a.serialize().length);
            }
            acknowledgement(true,output);
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
    
    private boolean connection(String pseudo,String password, UserProfil user){
        for(UserProfil p: listAllUsers){
            user = p;
            return (p.getPseudo().equals(pseudo))&&(p.getPassword().equals(password));
        }
        return false;
    }
    
    private boolean addUser(String adName, UserProfil user){
        for(Advert a: listAdverts){
            if(a.getAdName().equals(adName)){
                a.addAdMember(user);
                return true;
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
    
    private List<Advert> addsOwner(UserProfil owner) {
        List<Advert> ownerAdList = null;
        listAdverts.stream().filter((a) -> (a.getAdCreator() == owner)).forEachOrdered(ownerAdList::add);
        return ownerAdList;
    }
    
    private void readUserFile(){
        FileInputStream f;
        try {
            f = new FileInputStream(fileUser);
            try (ObjectInputStream o = new ObjectInputStream(f)) {
                while(o.available() > 0){
                    listAllUsers.add((UserProfil) o.readObject());
                }
            }
           f.close();
        } catch (FileNotFoundException e) {
			System.out.println("File not found");
	} catch(EOFException e){
            System.out.println("Fin de fichier");
        } catch (IOException e) {
			System.out.println("Error initializing stream");
	} catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void readAdFile(){
        FileInputStream f;
        try {
            f = new FileInputStream(fileAd);
            try (ObjectInputStream o = new ObjectInputStream(f)) {
                while(o.available() > 0){
                    listAdverts.add((Advert) o.readObject());
                }
            }
           f.close();
        } catch (FileNotFoundException e) {
			System.out.println("File not found");
	} catch(EOFException e){
            System.out.println("Fin de fichier");
        } catch (IOException e) {
			System.out.println("Error initializing stream");
	} catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void writeUserFile(){
        FileOutputStream f;
        try {
            f = new FileOutputStream(fileUser);
            try (ObjectOutputStream o = new ObjectOutputStream(f)) {
                for(UserProfil u: listAllUsers){
                    o.writeObject(u);
                }
            }
            f.close();
           
       } catch (FileNotFoundException e) {
            System.out.println("File not found");
	}
        catch (IOException e) {
            System.out.println("Error initializing stream");
	}
    }
    
    private void writeAdFile(){
        FileOutputStream f;
        try {
            f = new FileOutputStream(fileAd);
            try (ObjectOutputStream o = new ObjectOutputStream(f)) {
                for(Advert a: listAdverts){
                    o.writeObject(a);
                }
                o.close();
            }
            f.close();
           
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
	} catch (IOException e) {
            System.out.println("Error initializing stream");
	}
    }
    
    private boolean existingFile(File f){
        return f.exists() && !f.isDirectory();
    }
}
