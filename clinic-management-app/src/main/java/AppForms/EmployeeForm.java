package AppForms;

import org.example.Database;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EmployeeForm extends JFrame{
    private JPanel employeePanel;
    private JTextField searchTextField;
    private JButton dataButton;
    private JButton scheduleButton;
    private JButton holydayButton;


    public EmployeeForm() throws IOException {

        Image photo = ImageIO.read(new File("src/main/java/resources/btn-mr.png"));
        Image image = photo.getScaledInstance(60, 70, Image.SCALE_DEFAULT);

        ImageIcon icon = new ImageIcon(image);

        this.setSize(400,400);
        this.setContentPane(employeePanel);
        this.setVisible(true);


        ImageIcon womanIcon = new ImageIcon(buildImage("src/main/java/resources/btn-profile.png", 50, 50));
        dataButton.setIcon(womanIcon);
        dataButton.setHorizontalAlignment(JButton.CENTER);

      /*  ImageIcon button1Icon = new ImageIcon(buildImage("src/main/java/resources/btn-fr.png", 60,70));
        button1Button.setIcon(button1Icon);


        ImageIcon button2Icon = new ImageIcon(buildImage("src/main/java/resources/btn-hr.png",60,70));
        button2Button.setIcon(button2Icon);*/



        this.setSize(400, 400);
        this.setContentPane(employeePanel);
        this.setVisible(true);

    }


    public Image buildImage(String path, int width, int height) throws IOException {
        Image photo = ImageIO.read(new File(path));

        return photo.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }


   /* public ResultSet getResultSet() throws SQLException {

        Database database = new Database();
        String query = "Select * from utilizator where nume='Szollosi';";
        PreparedStatement statement = database.createPreparedStatement(query);

        return statement.executeQuery();
    }*/

}
