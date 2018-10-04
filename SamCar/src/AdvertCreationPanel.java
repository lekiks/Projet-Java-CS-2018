
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;



public class AdvertCreationPanel extends JPanel implements ActionListener {
	JTable placeTableau;
	JButton creeButton;
	JLabel nbPlacesLabel;
	JTextField nbPlacesText;
	JPanel nbPlacePanel;
	Client client;

	AdvertCreationPanel(Client client){
		this.client = client;
		this.setLayout(new BorderLayout());

		//placeTableau=get Open Data

		nbPlacePanel = new JPanel();
		nbPlacesLabel = new JLabel("nb places");
		nbPlacePanel.add(nbPlacesLabel);
		nbPlacesText = new JTextField("write here");
		nbPlacePanel.add(nbPlacesText);


		this.add(nbPlacePanel,BorderLayout.CENTER);

		creeButton= new JButton("creation");
		creeButton.addActionListener(this);
		//associaiton à envoie advert to serveur
		this.add(creeButton,BorderLayout.NORTH);

	}

	@Override
	public void ActionListener(ActionEvent e) {
		if (e.getSource() == creeButton) {
			client.sendIDRequest(2);
			Advert advert = new Advert();
			//advert.setAdEvent(eventFromTable);
			//advert.setAdCreator();
			//advert.setCarSize();
			client.sendAdvert(advert);
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
