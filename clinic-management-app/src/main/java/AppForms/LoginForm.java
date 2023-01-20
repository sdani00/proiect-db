package AppForms;

import org.example.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class LoginForm extends JFrame {
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passowrdLabel;
    private JButton registerButton;
    private JButton pacientButton;

    public LoginForm(String title) {
        super(title);

        this.setSize(200, 250);
        this.setBounds(0, 300, 200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
        this.setContentPane(loginPanel);

        this.pack();

        loginButton.addActionListener(event -> {
            if (event.getSource() == loginButton) {
                try {
                    if (checkCredentials()) {
                        this.dispose();
                        if(checkAdmin(usernameField.getText())) {
                            new AdminForm(usernameField.getText());
                        }
                        else if(!checkAdmin(usernameField.getText())) {
                            new AsistentForm();
                        }

                    } else {
                        JOptionPane.showMessageDialog(loginPanel, "Wrong credentials");
                    }

                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
        registerButton.addActionListener(event -> {
            if(event.getSource() == registerButton) {
                try {
                    new RegisterForm();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        new LoginForm("Login");
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public boolean checkCredentials() throws SQLException {
        Database database = new Database();
        Connection connection = database.getConnection();

        String query = "select nume,parola from clinica.utilizator where nume = ?;";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, usernameField.getText());

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            if (resultSet.getString("parola").equals(passwordField.getText())) {
                return true;
            }
        }

        return false;
    }

    public boolean checkAdmin(String name) throws SQLException {

        if(name.equals("Szollosi")) {
            return true;
        }

       return false;
    }
}

