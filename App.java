import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class App extends JFrame {
    private UserForm userForm;
    public App() {
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.green);
        userForm = new UserForm();
        add(userForm);
        setVisible(true);

        userForm.showList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                getContentPane().remove(userForm);
                add(new UserDataList());
                setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new App();
    }
}
