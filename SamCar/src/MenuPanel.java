import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    private JButton addTrip, searchTrip, confirmTrip, disconnect;
    private LaunchGUI launchGUILocal; //Permet de garder une référence locale sur le launcher afin d'actualiser la View
    private Client clientLocal;
    private GUIClient guiClientLocal;




    MenuPanel(LaunchGUI launchGUI, Client client, GUIClient guiClient){
        this.launchGUILocal = launchGUI;
        this.clientLocal = client;
        this.guiClientLocal = guiClient;

        //Layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        this.add(Box.createVerticalStrut(20));//espace

        //Implémentation des boutons
        addTrip = new JButton("Add a Trip");
        addTrip.setAlignmentX(this.CENTER_ALIGNMENT);
        addTrip.addActionListener(this);
        this.add(addTrip);

        this.add(Box.createVerticalStrut(20));//espace

        searchTrip = new JButton("Search for a Trip");
        searchTrip.setAlignmentX(this.CENTER_ALIGNMENT);
        searchTrip.addActionListener(this);
        this.add(searchTrip);

        this.add(Box.createVerticalStrut(20));//espace

        confirmTrip = new JButton("Confirm a Trip");
        confirmTrip.setAlignmentX(this.CENTER_ALIGNMENT);
        confirmTrip.addActionListener(this);
        this.add(confirmTrip);

        this.add(Box.createVerticalStrut(20));//espace


        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        this.add(separator1);

        disconnect = new JButton("Deconnect");
        disconnect.setAlignmentX(this.CENTER_ALIGNMENT);
        disconnect.addActionListener(this);
        this.add(disconnect);

        this.add(Box.createVerticalStrut(20));//espace
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addTrip){
            launchGUILocal.refreshPane(guiClientLocal.v4);
        }
        else if(source == searchTrip){
            launchGUILocal.refreshPane(guiClientLocal.v5);
        }
        else if(source == confirmTrip){
            launchGUILocal.refreshPane(guiClientLocal.v6);
        }
        else if(source == disconnect){
            //clientLocal.serverDisconnect();
            launchGUILocal.refreshPane(guiClientLocal.v1);
        }
        else{
            System.out.println("Error in the MenuPanel file");
        }
    }
}
