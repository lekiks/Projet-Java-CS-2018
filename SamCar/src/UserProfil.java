/*
Data about each users
 */

/**
 *
 * @author hadrienjanicot
 */

// Imports list
import java.io.*;
import javax.swing.*;
import java.io.Serializable;

public class UserProfil implements SerializableSC, Serializable{
    
    private static final long serialVersionUID = 1L;
     
    private String fullName;
    private String email;
    private String pseudo;

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }
    private String password;
    private String address;
    private String identifier;
    private ImageIcon profilPicture;
    private boolean connected;
    private DataInputStream input;
    private DataOutputStream output;
    
    UserProfil(){
    	
    }

    @Override
    public byte[] serialize() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(this);
        return out.toByteArray();
    }

    @Override
    public void deserialize(byte[] data) throws IOException, ClassNotFoundException{
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        UserProfil tampon = new UserProfil();
        tampon = (UserProfil) is.readObject();
        this.address = tampon.address;
        this.connected = tampon.connected;
        this.email = tampon.email;
        this.fullName = tampon.fullName;
        this.identifier = tampon.identifier;
        this.input = tampon.input;
        this.output = tampon.output;
        this.password = tampon.password;
        this.profilPicture = tampon.profilPicture;
        this.pseudo = tampon.pseudo;
    }

    public String getIdentifiant() {
        return identifier;
    }

}
