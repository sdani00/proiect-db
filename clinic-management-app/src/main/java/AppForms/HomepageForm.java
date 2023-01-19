package AppForms;

import javax.swing.*;
import java.awt.*;

public class HomepageForm extends JFrame{

    private JTextField textField1;
    private JButton viewEmployeeButton;
    private JButton logoutButton;
    private JPanel homepagePanel;

    public HomepageForm() {

        this.setSize(400,400);
        this.setContentPane(homepagePanel);
        this.setBackground(new Color(255255255));
        this.setVisible(true);
        this.pack();
        viewEmployeeButton.addActionListener(event -> {
            if(event.getSource() == viewEmployeeButton) {
                //
                new EmployeeForm();
                dispose();
            }
        });

        logoutButton.addActionListener(event -> {
            if(event.getSource() == logoutButton) {
                this.dispose();
                new LoginForm("Login");
            }
        });
    }
}
