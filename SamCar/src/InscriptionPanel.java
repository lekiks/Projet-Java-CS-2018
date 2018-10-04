import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;

public class InscriptionPanel extends JPanel implements ActionListener, MouseListener {

	JTextField fullNameTextField;
	JTextField emailTextField;
	JTextField usernameTextField;
	JTextField passwordTextField;
	JTextField passwordConfirmTextField;

	private JButton signUpButton, cancelButton;

	private JPanel global, right, left;
	private JLabel fullNameLabel, emailLabel, passwordLabel, passwordConfirmLabel, usernameLabel;

	private Client clientLocal;
	private LaunchGUI launchGUILocal;
	private GUIClient guiClientLocal;


	InscriptionPanel(LaunchGUI launchGUI ,Client client, GUIClient guiClient){

		this.clientLocal = client;
		this.launchGUILocal = launchGUI;
		this.guiClientLocal = guiClient;

		this.setLayout(new BorderLayout());


		right = new JPanel();

		right.setLayout((LayoutManager) new BoxLayout(right, BoxLayout.Y_AXIS));

		//right panel

		fullNameLabel = new JLabel("fullName");
		right.add(fullNameLabel);
		fullNameTextField = new JTextField("write here");
		fullNameTextField.addMouseListener(this);
		right.add(fullNameTextField);

		emailLabel = new JLabel("email");
		right.add(emailLabel);
		emailTextField = new JTextField("write here");
		emailTextField.addMouseListener(this);
		right.add(emailTextField);

		usernameLabel = new JLabel("username");
		right.add(usernameLabel);
		usernameTextField = new JTextField("write here");
		usernameTextField.addMouseListener(this);
		right.add(usernameTextField);

		passwordLabel = new JLabel("password");
		right.add(passwordLabel);
		passwordTextField = new JTextField("write here");
		passwordTextField.addMouseListener(this);
		right.add(passwordTextField);

		passwordConfirmLabel = new JLabel("passwordConfirm");
		right.add(passwordConfirmLabel);
		passwordConfirmTextField = new JTextField("write here");
		passwordConfirmTextField.addMouseListener(this);
		right.add(passwordConfirmTextField);


		this.add(right,BorderLayout.EAST);


		left=new JPanel();

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("/logo_samcar.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel label = new JLabel(new ImageIcon(image));
		left.add(label);



		this.add(left, BorderLayout.WEST);

		//South Panel
		JPanel southPanel = new JPanel();

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		southPanel.add(cancelButton);

		signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(this);
		southPanel.add(signUpButton);

		this.add(southPanel,BorderLayout.SOUTH);




		//left panel

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String composant = e.getActionCommand();
		if( e.getSource() == signUpButton ) {
			String tempPassword = passwordTextField.getText();
			System.out.println(tempPassword);
			System.out.println(passwordConfirmTextField.getText());
			if (tempPassword.equals(passwordConfirmTextField.getText())) {
				System.out.println("bouton3 marche");
				clientLocal.sendIDRequest(1);
				UserProfil usrProfil = new UserProfil();
				usrProfil.setFullName(fullNameTextField.getText());
				usrProfil.setEmail(emailTextField.getText());
				usrProfil.setUsername(usernameTextField.getText());
				usrProfil.setPassword(tempPassword);
				clientLocal.sendUserProfile(usrProfil);
				
				if (!clientLocal.getValidation())
				{
					System.out.println("erreur inscription");
				}
				else {
					launchGUILocal.refreshPane(guiClientLocal.v1);
				}
				
			}
		}
		else if(e.getSource() == cancelButton){
			launchGUILocal.refreshPane(guiClientLocal.v1);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object mouseSource = e.getSource();

		if(mouseSource == fullNameTextField){
			fullNameTextField.setText("");
		}
		else if(mouseSource == emailTextField){
			emailTextField.setText("");
		}
		else if(mouseSource == usernameTextField){
			usernameTextField.setText("");
		}
		else if(mouseSource == passwordTextField){
			passwordTextField.setText("");
		}
		else if(mouseSource == passwordConfirmTextField){
			passwordConfirmTextField.setText("");
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

