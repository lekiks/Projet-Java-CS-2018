import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class GUIClient extends JPanel implements ActionListener, MouseListener {

	private Client client;
	String address;
	String username;
	String password;
	int port;



	private JButton connect, signUp;
	private LaunchGUI launchGUILocal;
	private JTextField usernameIn;
	private JPasswordField passwordIn;

	MenuPanel v3;
	LoginPanel v1;
	InscriptionPanel v2;
	AdvertCreationPanel v4;


	
	GUIClient(LaunchGUI launchGUI) {
		this.launchGUILocal = launchGUI;

		//connection data
		address = "192.168.0.43" ;
		port = 11000 ;
		System.out.println(address);
		System.out.println(port);
		//end of connection data

		client = new Client(address,port);
		client.serverConnection();

		/**
		 * On déclare ici toutes les views entre lesquels nous allons switcher
		 */
		v1 = new LoginPanel(launchGUILocal, client, this);
		v2 = new InscriptionPanel(launchGUILocal, client, this);
		v3 = new MenuPanel(launchGUILocal, client, this);
		v4 = new AdvertCreationPanel(launchGUILocal, client, this);


		/**
		 * IHM implementation (duplication de v1 pour le start de l'appli)
		 */

		//Layout
		setLayout(new BorderLayout());

		//Center Panel

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("/logo_samcar.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		add(picLabel);

		//North Panel
		JPanel northV1 = new JPanel();
		add(northV1, BorderLayout.NORTH);

		usernameIn = new JTextField("Username", 10);
		usernameIn.addMouseListener(this);
		northV1.add(usernameIn);

		passwordIn = new JPasswordField("Password", 10);
		passwordIn.addMouseListener(this);
		northV1.add(passwordIn);

		connect = new JButton("Connect");
		connect.addActionListener(this);
		northV1.add(connect);

		//South Panel
		JPanel southV1 = new JPanel();
		add(southV1, BorderLayout.SOUTH);

		signUp = new JButton("Sign Up");
		signUp.addActionListener(this);
		southV1.add(signUp);


		//deal with state
		

		

	}
	
	
	void inscriptionRequest(){
		username= "Dewey";
		password = "jeLeveTaMere";
		client.identification(username,password);
	}
	

	
	
	void identification() {
		
		//data from GUI
		username = "corentin";
		password = "bourlet";
		//end of Data from GUI
		
		client.identification(username,password);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == connect){
			//username = usernameIn.getText();
			//password = new String(passwordIn.getPassword());
			identification();
			//if connection => view3
			launchGUILocal.refreshPane(v3);
		}
		else if (source == signUp){
			launchGUILocal.refreshPane(v2);
		}

		else{
			System.out.println("Error Login page (GUI) page");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object mouseSource = e.getSource();

		if(mouseSource == usernameIn){
			usernameIn.setText("");
		}
		else if(mouseSource == passwordIn){
			passwordIn.setText("");
		}
		else{
			System.out.println("Error in mouse Listener (GUI)");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
