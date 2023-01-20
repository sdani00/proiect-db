package AppForms;

import org.example.Database;

import javax.swing.*;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegisterForm extends JFrame {
    private JPanel searchPanel;
    private JTextField idUtilField;
    private JTextField cnpField;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField adressField;
    private JTextField phoneNumber;
    private JTextField ibanAccount;
    private JPanel registerPanel;
    private JComboBox comboBox;
    private JButton newUserButton;
    private JTextField emailField;
    private JTextField contractNumber;
    private JTextField dataField;
    private JPasswordField passowrdField;

    public RegisterForm () throws SQLException {

        String[] positions = {"Medic", "Asistent", "Receptioner", "InspectorHR", "Contabil"};

        comboBox.addItem(positions[0]);
        comboBox.addItem(positions[1]);
        comboBox.addItem(positions[2]);
        comboBox.addItem(positions[3]);
        comboBox.addItem(positions[4]);

        this.setContentPane(registerPanel);
        this.setVisible(true);
        this.pack();


        // idUtil, CNP, nume, prenume, adresa, nrTel, email, cont_iban, nr_contract, data_anganj, parola

        newUserButton.addActionListener(event -> {
            if(event.getSource() == newUserButton) {
                try {
                    this.addUser();
                    JOptionPane.showMessageDialog(registerPanel, "Utilizator adaugat cu succes!");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void addUser() throws SQLException {
        Database database = new Database();
        String query = "{call insert_utilizator(?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = database.createCallableStatement(query);
        // INSERT INTO Utilizator(idUtil, CNP, nume, prenume, adresa, nrTel, email, cont_iban, nr_contract, data_anganj, parola)
        callableStatement.setInt(1, Integer.parseInt(idUtilField.getText()));
        callableStatement.setString(2, cnpField.getText());
        callableStatement.setString(3, nameField.getText());
        callableStatement.setString(4, surnameField.getText());
        callableStatement.setString(5,adressField.getText());
        callableStatement.setLong(6, Long.parseLong(phoneNumber.getText()));
        callableStatement.setString(7,  emailField.getText());
        callableStatement.setString(8, ibanAccount.getText());
        callableStatement.setLong(9, Long.parseLong(contractNumber.getText()));
        callableStatement.setDate(10, new Date(12,12,12));
        callableStatement.setString(11, passowrdField.getText());

        callableStatement.executeUpdate();
    }
}
