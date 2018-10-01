package client;

public class GUIClient {
	Client client;
	String address;
	String username;
	String password;
	int port;
	
	GUIClient() {
		
		//deal with state
		
		//data from GUI
		address = "172.20.10.2" ;
		port = 11002 ;
		System.out.println(address);
		System.out.println(port);
		//end of data from GUI
		
		client = new Client (address,port);
		
		
		identification();
	}
	
	
	void inscriptionRequest(){
		username= "inscription";
		password = "inscription";
		client.identification(username,password);
	}
	

	
	
	void identification() {
		
		//data from GUI
		username = "corentin";
		password = "bourlet";
		//end of Data from GUI
		
		client.identification(username,password);
		
	}
}
