import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends JPanel implements ActionListener {

    private JButton connect, signUp;
    private LaunchGUI launchGUILocal;
    private JTextField usernameIn;
    private JPasswordField passwordIn;

    LoginPanel(LaunchGUI launchGUI){
        this.launchGUILocal = launchGUI;

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
        add(picLabel);

        //North Panel
        JPanel northV1 = new JPanel();
        add(northV1, BorderLayout.NORTH);

        usernameIn = new JTextField("Username", 10);
        usernameIn.addActionListener(this);
        northV1.add(usernameIn);

        passwordIn = new JPasswordField("Password", 10);
        passwordIn.addActionListener(this);
        northV1.add(passwordIn);

        connect = new JButton("Connect");
        connect.addActionListener(this);
        northV1.add(connect);

        //South Panel
        JPanel southV1 = new JPanel();
        add(southV1, BorderLayout.SOUTH);

        signUp = new JButton("Sign Up");
        connect.addActionListener(this);
        southV1.add(signUp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == connect){
            //Call connection method

            //if connection => view3
            launchGUILocal.refreshPane(launchGUILocal.v3);
        }
        else if (source == signUp){
            //Aller vers view2
        }
    }
}
