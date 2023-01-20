package AppForms;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class AdminForm extends JFrame{

    private JButton viewEmployeeButton;
    private JButton logoutButton;
    private JPanel homepagePanel;
    private JButton womanButton;
    private JButton button1Button;
    private JButton button2Button;

    public AdminForm(String username) throws IOException {

        Image photo = ImageIO.read(new File("src/main/java/resources/btn-mr.png"));
        Image image = photo.getScaledInstance(60, 70, Image.SCALE_DEFAULT);

        ImageIcon icon = new ImageIcon(image);

        this.setSize(400,400);
        this.setContentPane(homepagePanel);
        this.setVisible(true);


        ImageIcon womanIcon = new ImageIcon(buildImage("src/main/java/resources/btn-profile.png", 60, 70));
        womanButton.setIcon(womanIcon);
        womanButton.setHorizontalAlignment(JButton.CENTER);

        ImageIcon button1Icon = new ImageIcon(buildImage("src/main/java/resources/btn-fr.png", 60,70));
        button1Button.setIcon(button1Icon);


        ImageIcon button2Icon = new ImageIcon(buildImage("src/main/java/resources/btn-hr.png",60,70));
        button2Button.setIcon(button2Icon);

        viewEmployeeButton.setIcon(icon);
        viewEmployeeButton.addActionListener(event -> {
            if(event.getSource() == viewEmployeeButton) {
                try {
                    new EmployeeForm();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        logoutButton.addActionListener(event -> {
            if(event.getSource() == logoutButton) {
                this.dispose();
                new LoginForm("Login");
            }
        });

        this.pack();
        womanButton.addActionListener(event -> {
           if(event.getSource() == womanButton) {
               try {
                   new UsersForm();
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
           }
        });
    }

    public Image buildImage(String path, int width, int height) throws IOException {
        Image photo = ImageIO.read(new File(path));

        return photo.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }
}

