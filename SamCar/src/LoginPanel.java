import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends JPanel implements ActionListener, MouseListener {

    private JButton connect, signUp;
    private LaunchGUI launchGUILocal;
    private JTextField usernameIn;
    private JPasswordField passwordIn;
    private Client clientLocal;
    private GUIClient guiClientLocal;

    LoginPanel(LaunchGUI launchGUI, Client client, GUIClient guiClient ){
        this.launchGUILocal = launchGUI;
        this.clientLocal = client;
        this.guiClientLocal = guiClient;

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
        add(picLabel, BorderLayout.CENTER);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == connect){
            //Call connection method

            //if connection => view3
            launchGUILocal.refreshPane(guiClientLocal.v3);
        }
        else if (source == signUp){
            launchGUILocal.refreshPane(guiClientLocal.v2);
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
            System.out.println("Error in mouse Listener");
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
