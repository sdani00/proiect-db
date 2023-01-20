package AppForms;

import org.example.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UsersForm extends JFrame {
    private JTable table;
    private JPanel usersPanel;
    private JButton closeButton;

    public  UsersForm() throws SQLException {

        Database database = new Database();
        Connection connection = database.getConnection();

        String query = "select * from utilizator;";
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        int columnsNumber = resultSetMetaData.getColumnCount();

        String[] columnName = new String[columnsNumber];

        for(int i = 0; i < columnsNumber; i++) {
            columnName[i] = resultSetMetaData.getColumnName(i + 1);
            //System.out.println(resultSetMetaData.getColumnName(i + 1));
        }

        tableModel.setColumnIdentifiers(columnName);

        /*idUtil
                CNP
        nume
                prenume
        adresa
                nrTel
        email
                cont_iban
        nr_contract
                data_anganj
        parola*/

        String id,nume,prenume,adresa,nrTel,email, iBan, nr_contract,data_ang, parola;

        while(resultSet.next()) {
            id = resultSet.getString(1);
            nume = resultSet.getString(2);
            prenume = resultSet.getString(3);
            adresa = resultSet.getString(4);
            nrTel = resultSet.getString(5);
            email = resultSet.getString(6);
            iBan =resultSet.getString(7);;
            nr_contract = resultSet.getString(8);
            data_ang = resultSet.getString(9);
            parola = resultSet.getString(10);

            String[] row = {id ,nume, prenume, adresa, nrTel, email, iBan, nr_contract,data_ang, parola};
            tableModel.addRow(row);
        }

        this.setContentPane(usersPanel);
        this.setVisible(true);
        this.pack();
        closeButton.addActionListener( event -> {
            this.dispose();
        });
    }

}
