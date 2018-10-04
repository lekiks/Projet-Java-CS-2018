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
    public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public ImageIcon getProfilPicture() {
		return profilPicture;
	}

	public void setProfilPicture(ImageIcon profilPicture) {
		this.profilPicture = profilPicture;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public DataInputStream getInput() {
		return input;
	}

	public void setInput(DataInputStream input) {
		this.input = input;
	}

	public DataOutputStream getOutput() {
		return output;
	}

	public void setOutput(DataOutputStream output) {
		this.output = output;
	}

	public void setUsername(String pseudo) {
		this.username = pseudo;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	private String email;
    private String username;

    public String getPseudo() {
        return username;
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
        this.username = tampon.username;
    }

    public String getIdentifiant() {
        return identifier;
    }

}
