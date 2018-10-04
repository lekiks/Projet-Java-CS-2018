import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;


public class AdvertChoicePanel extends JPanel implements ActionListener {
	JTable tableau;
	Client client;
	JButton validateButton;
	JButton chargeDataButton;
	private List<Advert> listAdverts;
	
	AdvertChoicePanel(Client client){
		this.client = client;
		Object[][] donnees = {};
		String[] entetes = {"nom","nbre de place","horaire"};
		tableau = new JTable(donnees, entetes);
		this.add (tableau);
		//envoie du choix

		validateButton.setText("validate");
		validateButton.addActionListener(this);
		
		
		chargeDataButton.setText("chargement des donnes");
		chargeDataButton.addActionListener(this);
		
		this.add(chargeDataButton);
		this.add(validateButton);
	}


	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==validateButton) {
			client.sendIDRequest(3);

			int tailleListAdvert = client.getInt();
			
			for (int i =0; i<tailleListAdvert; i++) {
				listAdverts.add(client.getAdvert());
			}
			if (!client.getValidation())
			{
				System.out.println("erreur inscription");
			}
			else {
				//aller à page connexion
			}
		}
		
		if (e.getSource()==validateButton) {
			client.sendMessage(advert);
		}
	}
}