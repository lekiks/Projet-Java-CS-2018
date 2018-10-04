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

public class UserProfil implements Serializable{
    private String fullName;
    private String email;

    private String pseudo;

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    private String address;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
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
        this.username = tampon.username;
        this.input = tampon.input;
        this.output = tampon.output;
        this.password = tampon.password;
        this.profilPicture = tampon.profilPicture;
    }

    public String getIdentifiant() {
        return username;
    }

}
