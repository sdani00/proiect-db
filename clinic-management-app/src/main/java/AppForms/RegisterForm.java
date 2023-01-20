package AppForms;

import org.example.Database;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

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

    public RegisterForm () {

        String[] positions = {"Medic", "Asistent", "Receptioner", "InspectorHR", "Contabil"};

        comboBox.addItem(positions[0]);
        comboBox.addItem(positions[1]);
        comboBox.addItem(positions[2]);
        comboBox.addItem(positions[3]);
        comboBox.addItem(positions[4]);

        this.setContentPane(registerPanel);
        this.setVisible(true);
        this.pack();

        newUserButton.addActionListener(event -> {
            if(event.getSource() == newUserButton) {



            }
        });
    }
}
