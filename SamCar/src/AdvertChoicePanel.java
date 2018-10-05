
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;


public class AdvertChoicePanel extends JPanel implements ActionListener {
	int tabCompt=0;
	private JButton cancelButton;
	private Client clientLocal;
	private LaunchGUI launchGUILocal;
	private GUIClient guiClientLocal;
	private JPanel tableau;
	List<Advert> listAdvert = new ArrayList<Advert>();

	JButton next = new JButton("next");
	JButton previous = new JButton("previous");

	JButton button1 =new JButton("choice");
	JButton button2=new JButton("choice");
	JButton button3=new JButton("choice");
	JButton button4=new JButton("choice");
	JButton button5=new JButton("choice");



	AdvertChoicePanel(LaunchGUI launchGUI, Client client, GUIClient guiClient){

		this.launchGUILocal = launchGUI;
		this.clientLocal = client;
		this.guiClientLocal = guiClient;



		//request data event
		clientLocal.sendIDRequest(3);

		int size = 0;
		try {
			size = clientLocal.input.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i <size;i++) {
			listAdvert.add(clientLocal.getAdvert());
		}
		this.setLayout(new BorderLayout());




		//North Panel
		JPanel northPanel = new JPanel();

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		northPanel.add(cancelButton);



		this.add(northPanel,BorderLayout.NORTH);





		button1.setText(listAdvert.get(0).getAdName());
		button2.setText(listAdvert.get(1).getAdName());
		button3.setText(listAdvert.get(2).getAdName());
		button4.setText(listAdvert.get(3).getAdName());
		button5.setText(listAdvert.get(4).getAdName());

		this.add(button1,BorderLayout.CENTER);
		this.add(button2,BorderLayout.CENTER);
		this.add(button3,BorderLayout.CENTER);
		this.add(button4,BorderLayout.CENTER);
		this.add(button5,BorderLayout.CENTER);



		next.addActionListener(this);
		previous.addActionListener(this);
		this.add(next, BorderLayout.SOUTH);
		this.add(previous, BorderLayout.SOUTH);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//		if (e.getSource() == creeButton) {
		//			clientLocal.sendIDRequest(2);
		//			Advert advert = new Advert();
		//			//advert.setAdEvent(eventFromTable);
		//			//advert.setAdCreator();
		//			//advert.setCarSize();
		//			//clientLocal.sendAdvert(advert);
		//			if (!clientLocal.getValidation())
		//			{
		//				System.out.println("erreur création");
		//			}
		//			else {
		//				launchGUILocal.refreshPane(guiClientLocal.v3);
		//			}
		//		}
		if(e.getSource() == cancelButton){
			launchGUILocal.refreshPane(guiClientLocal.v3);
		}

		if (e.getSource() == button1) {
			try {
				clientLocal.output.writeUTF(button1.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (e.getSource() == button2) {
			try {
				clientLocal.output.writeUTF(button2.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getSource() == button3) {
			try {
				clientLocal.output.writeUTF(button3.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getSource() == button4) {
			try {
				clientLocal.output.writeUTF(button4.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getSource() == button5) {

			try {
				clientLocal.output.writeUTF(button5.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (e.getSource()== next) {
				tabCompt +=1;
				button1.setText(listAdvert.get(0+tabCompt).getAdName());
				button2.setText(listAdvert.get(1+tabCompt).getAdName());
				button3.setText(listAdvert.get(2+tabCompt).getAdName());
				button4.setText(listAdvert.get(3+tabCompt).getAdName());
				button5.setText(listAdvert.get(4+tabCompt).getAdName());
				this.repaint();}

		}
		if (e.getSource()== previous) {
			tabCompt -=1;
			button1.setText(listAdvert.get(0+tabCompt).getAdName());
			button2.setText(listAdvert.get(1+tabCompt).getAdName());
			button3.setText(listAdvert.get(2+tabCompt).getAdName());
			button4.setText(listAdvert.get(3+tabCompt).getAdName());
			button5.setText(listAdvert.get(4+tabCompt).getAdName());
			this.repaint();}
	}

}



