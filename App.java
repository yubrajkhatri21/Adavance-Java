import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;

public class App extends JFrame {
    public App() {
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.green);
        
        add(new UserForm());
        setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
