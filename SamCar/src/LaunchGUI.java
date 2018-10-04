import javax.swing.*;

public class LaunchGUI extends JFrame {
    GUIClient guiClient;


    LaunchGUI(){
        //IHM
        guiClient = new GUIClient(this);
        setContentPane(guiClient);
        setTitle("SamCar");
        pack();
        //this.setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void refreshPane(JPanel newPanel){
        this.getContentPane().removeAll();
        this.getContentPane().add(newPanel);
        this.getContentPane().revalidate();
        this.repaint();
    }

    public static void main (String [] args) {
        LaunchGUI clientLaunch = new LaunchGUI();
        //server.setPort(10000);  

    }
}
