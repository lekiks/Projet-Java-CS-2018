/*
Class used to launch the singleton server
 */

/**
 *
 * @author hadrienjanicot
 */


public class LaunchSingletonServer {   
    public static void main (String [] args) {
        Server server = Server.getSvr();
        server.setPort(10000);  
    }
}
