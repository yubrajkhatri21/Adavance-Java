import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class UserDataList extends JPanel {
    private UserFormController controller;
    public UserDataList(){
        setLayout(new BorderLayout());
        controller = new UserFormController();

        JTable table = new JTable();
        table.setModel (controller.retrieveUsers());

        JScrollPane scrollPane = new JScrollPane(table);
        add( scrollPane, BorderLayout.WEST);

        JTable table1 = new JTable();
        table1.setModel (controller.retrieveUsers());

        JScrollPane scrollPane1 = new JScrollPane(table1);
        add( scrollPane1, BorderLayout.EAST);
    }
}
