
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;



public class AdvertCreationPanel extends JPanel implements ActionListener {
	JTable placeTableau;
	private JButton creeButton, cancelButton;
	private JLabel nbPlacesLabel;
	private JTextField nbPlacesText;
	private JPanel nbPlacePanel;
	private Client clientLocal;
	private LaunchGUI launchGUILocal;
	private GUIClient guiClientLocal;

	AdvertCreationPanel(LaunchGUI launchGUI, Client client, GUIClient guiClient){
		this.launchGUILocal = launchGUI;
		this.clientLocal = client;
		this.guiClientLocal = guiClient;
		this.setLayout(new BorderLayout());

		//placeTableau=get Open Data

		nbPlacePanel = new JPanel();
		nbPlacesLabel = new JLabel("nb places");
		nbPlacePanel.add(nbPlacesLabel);
		nbPlacesText = new JTextField("write here");
		nbPlacePanel.add(nbPlacesText);


		this.add(nbPlacePanel,BorderLayout.CENTER);

		//North Panel
		JPanel northPanel = new JPanel();

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		northPanel.add(cancelButton);

		creeButton= new JButton("creation");
		creeButton.addActionListener(this);
		northPanel.add(creeButton);
		//associaiton à envoie advert to serveur
		this.add(northPanel,BorderLayout.NORTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == creeButton) {
			clientLocal.sendIDRequest(2);
			Advert advert = new Advert();
			//advert.setAdEvent(eventFromTable);
			//advert.setAdCreator();
			//advert.setCarSize();
			clientLocal.sendAdvert(advert);
			if (!clientLocal.getValidation())
			{
				System.out.println("erreur création");
			}
			else {
				launchGUILocal.refreshPane(guiClientLocal.v3);
			}
		}
		else if(e.getSource() == cancelButton){
			launchGUILocal.refreshPane(guiClientLocal.v3);
		}
	}


}
