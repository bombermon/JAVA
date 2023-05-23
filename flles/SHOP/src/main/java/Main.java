
import Inteface.FrameApp;
import Shop.Management;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        Management management = new Management();
        FrameApp frameApp = new FrameApp(management);
        frameApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameApp.setSize(800, 500);
        frameApp.setLocation(230,100);
        frameApp.setVisible(true);
    }
}