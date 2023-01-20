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
                        int x = checkIfAdmin(usernameField.getText());
                        if(x==0){
                            this.dispose();
                           // new DoctorForm();
                        }
                      /*  if(x == 1)
                        {
                            this.dispose();
                            new AsistentForm(usernameField.getText());
                        }
                        if(x==2){
                            this.dispose();
                            new EmployeeForm();
                        }
                        if(x==3)
                        {
                            this.dispose();
                            new ReceptionerForm();
                        }
                        if(x==4){
                            this.dispose();
                            new Expert();
                        }*/
                        if(x==5){
                            this.dispose();
                            new AdminForm(usernameField.getText());
                        }



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

        return true;
    }

    public int checkIfAdmin(String name) throws SQLException {
        Database database = new Database();

        CallableStatement statement = database.createCallableStatement("{call check_if_doctor(?, ?)}");
        statement.setString(1, name);
        statement.registerOutParameter(2, Types.TINYINT);
        statement.execute();

        int result = statement.getInt(2);

        if (result == 1)
        {
            return 0;
        }
        statement = database.createCallableStatement("call_check_if_asistent(?,?)}");
        statement.setString(1,name);
        statement.registerOutParameter(2,Types.TINYINT);
        statement.execute();

        result = statement.getInt(2);

        if(result==1)
        {
            return 1;
        }

        statement = database.createCallableStatement("call_check_if_inspector(?,?)}");
        statement.setString(1,name);
        statement.registerOutParameter(2,Types.TINYINT);
        statement.execute();

        result = statement.getInt(2);

        if(result==1)
        {
            return 2;
        }

        statement = database.createCallableStatement("call_check_if_receptioner(?,?)}");
        statement.setString(1,name);
        statement.registerOutParameter(2,Types.TINYINT);
        statement.execute();

        result = statement.getInt(2);

        if(result==1)
        {
            return 3;
        }
        statement = database.createCallableStatement("call_check_if_expert(?,?)}");
        statement.setString(1,name);
        statement.registerOutParameter(2,Types.TINYINT);
        statement.execute();

        result = statement.getInt(2);

        if(result==1)
        {
            return 4;
        }
        return 5;
    }
}

