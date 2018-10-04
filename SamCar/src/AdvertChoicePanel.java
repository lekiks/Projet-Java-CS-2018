import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;


public class AdvertChoicePanel extends JPanel implements ActionListener {
	JTable tableau;
	private JButton validateButton ,chargeDataButton, cancelButton;
	private List<Advert> listAdverts;

	private Client clientLocal;
	private LaunchGUI launchGUILocal;
	private GUIClient guiClientLocal;
	
	AdvertChoicePanel(LaunchGUI launchGUI, Client client, GUIClient guiClient){
		this.launchGUILocal = launchGUI;
		this.clientLocal = client;
		this.guiClientLocal = guiClient;

		//Layout
		setLayout(new BorderLayout());

		//North Panel
		JPanel northPanel = new JPanel();
		//envoie du choix
		chargeDataButton = new JButton("chargement des donnes");
		chargeDataButton.addActionListener(this);
		northPanel.add(chargeDataButton);

		validateButton = new JButton("Confirm");
		validateButton.addActionListener(this);
		northPanel.add(validateButton);

		add(northPanel, BorderLayout.NORTH);


		//Center Panel
		JPanel centerPanel = new JPanel();
		Object[][] donnees = {};
		String[] entetes = {"nom","nbre de place","horaire"};


		tableau = new JTable(donnees, entetes);
		centerPanel.add (tableau);
		add(centerPanel, BorderLayout.CENTER);

		//South Panel
		JPanel southPanel = new JPanel();
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		southPanel.add(cancelButton);

		add(southPanel, BorderLayout.SOUTH);


	}


	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==validateButton) {
			clientLocal.sendIDRequest(3);

			int tailleListAdvert = clientLocal.getInt();
			
			for (int i =0; i<tailleListAdvert; i++) {
				//listAdverts.add(clientLocal.getAdvert());
			}
			if (!clientLocal.getValidation())
			{
				System.out.println("erreur inscription");
			}
			else {
				launchGUILocal.refreshPane(guiClientLocal.v3);
			}
		}
		else if(e.getSource() == cancelButton){
			launchGUILocal.refreshPane(guiClientLocal.v3);
		}
		
		if (e.getSource()==validateButton) {
			//clientLocal.sendMessage(advert);
		}
	}
}