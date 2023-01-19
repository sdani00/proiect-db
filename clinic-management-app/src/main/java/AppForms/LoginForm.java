package AppForms;

import org.example.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm extends JFrame
{
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passowrdLabel;

    public LoginForm(String title) {
        super(title);

        this.setSize(200, 250);
        this.setBounds(0, 300, 200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
        this.setContentPane(loginPanel);

        this.pack();



        loginButton.addActionListener(event -> {
            if(event.getSource() == loginButton) {
                try {
                    if(checkCredentials()) {
                        this.dispose();
                        new HomepageForm();


                    }
                    else {
                        JOptionPane.showMessageDialog(loginPanel, "Wrong credentials");
                    }

                } catch (SQLException | IOException e) {

                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new LoginForm("Login");
    }

    public boolean checkCredentials() throws SQLException {
//        Database database = new Database();
//        Connection connection = database.getConnection();
//
//        String query = "SELECT nume,parola FROM clinica.utilizator";
//        PreparedStatement statement = database.createPreparedStatement(query);
//        ResultSet resultSet = statement.executeQuery();
//
//        if(resultSet.next()){
//            System.out.println(resultSet.getString("nume"));
//            System.out.println(resultSet.getString("parola"));
//
//            if(resultSet.getString("nume").equals(usernameField.getText()))
//            {
//                return true;
//            }
//
//        }
//        //check db  fields
        return true;
    }
}
