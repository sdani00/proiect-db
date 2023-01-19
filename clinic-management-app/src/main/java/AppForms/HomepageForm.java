package AppForms;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HomepageForm extends JFrame{

    private JButton viewEmployeeButton;
    private JButton logoutButton;
    private JPanel homepagePanel;

    public HomepageForm() throws IOException {

        Image photo = ImageIO.read(new File("H:\\colab_bd\\proiect-db\\clinic-management-app\\src\\main\\java\\resources\\btn-mr.png"));
        Image image = photo.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        ImageIcon icon = new ImageIcon(image);

        this.setSize(400,400);
        this.setContentPane(homepagePanel);
        this.setBackground(new Color(255255255));
        this.setVisible(true);

        viewEmployeeButton.setBounds(0,30, 100,100);

        viewEmployeeButton.setIcon(icon);

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
