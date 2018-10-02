package client;
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

public class UserProfil {
	private String fullName;
    private String email;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    private int identifiant;
    private ImageIcon profilPicture;
    private boolean connected;
    private DataInputStream input;
    private DataOutputStream output;
	
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
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
	
}
