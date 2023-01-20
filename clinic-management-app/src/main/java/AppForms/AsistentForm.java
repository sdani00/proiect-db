package AppForms;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class AsistentForm extends JFrame{

    private JButton button1;
    private JButton button2;
    private JPanel asistentPanel;
    private JButton logoutButton;

    public AsistentForm() throws IOException {

        ImageIcon womanIcon = new ImageIcon(buildImage("src/main/java/resources/btn-profile.png", 60, 70));
        button1.setIcon(womanIcon);

        ImageIcon button2Icon = new ImageIcon(buildImage("src/main/java/resources/btn-hr.png",60,70));
        button2.setIcon(button2Icon);


        this.setContentPane(asistentPanel);
        this.setVisible(true);
        this.pack();
        button1.addActionListener(event -> {
            if(event.getSource() == button1) {
                try {
                    new EmployeeForm();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        button2.addActionListener(event -> {
            if(event.getSource() == button2) {
                try {
                    new UsersForm();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        logoutButton.addActionListener(event-> {
            if(event.getSource() == logoutButton) {
                new LoginForm("Login");
                this.dispose();
            }
        });
    }

    public Image buildImage(String path, int width, int height) throws IOException {
        Image photo = ImageIO.read(new File(path));

        return photo.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }
}

