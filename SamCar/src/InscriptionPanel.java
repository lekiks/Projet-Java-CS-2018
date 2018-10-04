import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.TextField;
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

public class InscriptionPanel extends JPanel implements ActionListener {

	JTextField fullNameTextField;
	JTextField emailTextField;
	JTextField usernameTextField;
	JTextField passwordTextField;
	JTextField passwordConfirmTextField;

	JButton signUpButton;

	JPanel global;
	JPanel right;
	JPanel left;
	JLabel fullNameLabel;
	JLabel emailLabel;
	JLabel passwordLabel;
	JLabel passwordConfirmLabel;
	JLabel usernameLabel;

	Client client;

	InscriptionPanel(Client client){

		this.client = client;

		this.setLayout(new BorderLayout());


		right = new JPanel();

		right.setLayout((LayoutManager) new BoxLayout(right, BoxLayout.Y_AXIS));

		//right panel

		fullNameLabel = new JLabel("fullName");
		right.add(fullNameLabel);
		fullNameTextField = new JTextField("write here");
		right.add(fullNameTextField);

		emailLabel = new JLabel("email");
		right.add(emailLabel);
		emailTextField = new JTextField("write here");
		right.add(emailTextField);

		usernameLabel = new JLabel("username");
		right.add(usernameLabel);
		usernameTextField = new JTextField("write here");
		right.add(usernameTextField);

		passwordLabel = new JLabel("password");
		right.add(passwordLabel);
		passwordTextField = new JTextField("write here");
		right.add(passwordTextField);

		passwordConfirmLabel = new JLabel("passwordConfirm");
		right.add(passwordConfirmLabel);
		passwordConfirmTextField = new JTextField("write here");
		right.add(passwordConfirmTextField);


		this.add(right,BorderLayout.EAST);


		left=new JPanel();

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("/Users/corentinbourlet/Downloads/Portugal-Map-Flag.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel label = new JLabel(new ImageIcon(image));
		left.add(label);



		this.add(left, BorderLayout.WEST);

		signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(this);
		this.add(signUpButton,BorderLayout.SOUTH);




		//left panel


	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String composant = e.getActionCommand();
		System.out.println("bouton1 marche");
		if( e.getSource() == signUpButton ) {
			System.out.println("bouton2 marche");
			String tempPassword = passwordTextField.getText();
			if (tempPassword == passwordConfirmTextField.getText()) {
				System.out.println("bouton3 marche");
				client.sendIDRequest(1);
				UserProfil usrProfil = new UserProfil();
				usrProfil.setFullName(fullNameTextField.getText());
				usrProfil.setEmail(emailTextField.getText());
				usrProfil.setFullName(usernameTextField.getText());
				usrProfil.setPassword(tempPassword);
				client.sendUserProfile(usrProfil);
				
				if (!client.getValidation())
				{
					System.out.println("erreur inscription");
				}
				else {
					//aller à page connexion
				}
				
			}
		}
	}
}

