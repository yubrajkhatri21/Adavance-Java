import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserForm extends JPanel{

    public JLabel title,username,email,height;
    public JTextField tname,temail,theight;
    private UserFormController controller;
    public JButton submit;

    public UserForm() {
        setLayout(null);

        title = new JLabel("User form Entry");
        Font font = new Font(Font.SANS_SERIF,Font.BOLD,20);
        title.setFont(font);
        title.setBounds(300, 200, 300, 40);

        username = new JLabel("Username");
        username.setBounds(200, 250, 100, 30);

        email = new JLabel("Email");
        email.setBounds(200,300,100,30);

        height = new JLabel("Height");
        height.setBounds(200,350,100,30);

        tname = new JTextField();
        tname.setBounds(300, 250, 150, 30);

        temail = new JTextField();
        temail.setBounds(300, 300, 150, 30);

        theight = new JTextField();
        theight.setBounds(300, 350, 150, 30);

        submit = new JButton("Submit");
        submit.setBounds(300, 400, 100, 30);


        add(title); add(username); add(email); add(height); add(tname); add(temail); add(theight); add(submit);
        controller= new UserFormController();
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = tname.getText().toString();
                String email = temail.getText().toString();
                String height = theight.getText().toString();

                if(uname.isEmpty()) {
                    tname.setBackground(Color.red);
                } else if(email.isEmpty()){
                    temail.setBackground(Color.red);
                } else if(height.isEmpty()) {
                    theight.setBackground(Color.red);
                }else {
                    float value = Float.parseFloat(height);
                    UserValues user = new UserValues(uname, email, value);
                    
                    controller.submitUserData(user, new UserFormCallback() {

                        @Override
                        public void onSucess() {
                            // TODO Auto-generated method stub
                            JOptionPane.showMessageDialog(submit, "User data entered");
                        }

                        @Override
                        public void onFailure(String error) {
                            // TODO Auto-generated method stub
                            JOptionPane.showConfirmDialog(submit, error);
                        }
                        
                    });
                }
            }
        });
    }
    
}
