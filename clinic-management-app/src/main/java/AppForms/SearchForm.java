package AppForms;

import org.example.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchForm extends JFrame {

    private JTextField idUtilField;
    private JTextField cnpField;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField adressField;
    private JTextField phoneNumber;
    private JTextField ibanAccount;
    private JPanel searchPanel;

    public SearchForm(String username) throws SQLException {


        this.setContentPane(searchPanel);
        this.setVisible(true);
        this.pack();

                try {
                    Database database = new Database();
                    Connection connection = database.getConnection();

                    String query = "Select * from utilizator where nume = ?;";

                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, username);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        this.idUtilField.setText(resultSet.getString("idUtil"));
                        this.cnpField.setText(resultSet.getString("cnp"));
                        this.nameField.setText(resultSet.getString("nume"));
                        this.surnameField.setText(resultSet.getString("prenume"));
                        this.adressField.setText(resultSet.getString("adresa"));
                        this.phoneNumber.setText(resultSet.getString("nrTel").toString());
                        this.ibanAccount.setText(resultSet.getString("cont_iban").toString());
                    }
                } catch (Exception ex) {
                        ex.printStackTrace();
                }
    }
}
