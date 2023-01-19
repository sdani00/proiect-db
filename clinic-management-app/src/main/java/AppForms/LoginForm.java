package AppForms;

import org.example.Database;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class LoginForm extends JFrame {
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
            if (event.getSource() == loginButton) {
                try {
                    if (checkCredentials()) {
                        this.dispose();

                        new EmployeeForm();

                      /*  if(checkIfAdmin(usernameField.getText())) {
                            new AdminForm(usernameField.getText());
                        }*/

                    } else {
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

        return true;
    }

    public boolean checkIfAdmin(String name) throws SQLException {
        Database database = new Database();

        CallableStatement statement = database.createCallableStatement("{call check_if_doctor(?, ?)}");
        statement.setString(1, name);
        statement.registerOutParameter(2, Types.TINYINT);
        statement.execute();

        int result = statement.getInt(2);

        if (result == 1)
            return true;

        return false;
    }
}

