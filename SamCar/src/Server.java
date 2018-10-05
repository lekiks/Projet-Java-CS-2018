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
    private List<UserProfil>  listAllUsers = new ArrayList<UserProfil>();
    private List<Event> listEvents = new ArrayList<Event>();
    private List<Advert> listAdverts = new ArrayList<Advert>();
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
                System.out.println("client co");
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
            while(!true == !true) {
            	int code = input.readInt();
            	codeCheck(code, output);
            	caseSelection(code, input, output);
            }
              
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
	case 0:
            String pseudo,password;
            pseudo = input.readUTF();
            password = input.readUTF();
            acknowledgement(connection(pseudo,password,user),output);
            break;
	case 1:
            UserProfil newProfil = new UserProfil();
            int length = input.readInt();
            byte[] b = new byte[length];
            int i = 0;
            i = input.read(b, 0, b.length);
            try {
                newProfil.deserialize(b);
                listAllUsers.add(newProfil);
                user = newProfil;
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            output.writeBoolean(true);
            output.flush();
            System.out.println("cc ça marche");
        /* acknowledgement(true,output); */
            break;
	case 2:
	        output.writeInt(listEvents.size());
            for(Event e: listEvents){
                output.writeInt(e.serialize().length);
                output.write(e.serialize(), 0, e.serialize().length);
             }
            Advert ad = new Advert();
            int length2 = input.readInt();
            byte[] b2 = new byte[length2];
            int i2 = 0;
            i2 = input.read(b2, 0, b2.length);
            try {
                ad.deserialize(b2);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            listAdverts.add(ad);
            break;
	case 3:
            output.writeInt(listAdverts.size());
            for(Advert a: listAdverts){
                if(a.checkSize()){
                    output.writeInt(a.serialize().length);
                    output.write(a.serialize(), 0, a.serialize().length);
                }
            }
            Advert ad3 = new Advert();
            int length3 = input.readInt();
            byte[] b3 = new byte[length3];
            int i3 = 0;
            i3 = input.read(b3, 0, b3.length);
            try {
                ad3.deserialize(b3);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            addUser(ad3,user);
            break;
	case 4:
            List<Advert> userAdds = addsOwner(user);
            output.writeInt(userAdds.size());
            for(Advert a: userAdds){
                output.write(a.serialize(), 0, a.serialize().length);
            }
            acknowledgement(true,output);
            break;

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
            boolean test = p.getPseudo().equals(pseudo);
            boolean test2 = p.getPassword().equals(password);
            boolean test3 = test&&test2;
            return (p.getPseudo().equals(pseudo))&&(p.getPassword().equals(password));
        }
        return false;
    }
    
    private void addUser(Advert ad, UserProfil user){
        for(Advert a: listAdverts){
            if(a == ad){
                a.addAdMember(user);
            }
        }
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
