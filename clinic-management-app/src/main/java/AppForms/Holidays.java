package AppForms;

import org.example.Database;

import javax.swing.*;
import java.sql.*;

public class Holidays extends JFrame{
    private JPanel holidaysPanel;
    private JTextField startConcediu;
    private JTextField sfarsitConcediu;

    public Holidays(String username) throws SQLException {

        Database database = new Database();
        Connection connection = database.getConnection();

        String query = "{call get_concediu_dates(?,?,?)};";

        CallableStatement statement = database.createCallableStatement(query);
        statement.setString(1, username);
        statement.registerOutParameter(2, Types.DATE);
        statement.registerOutParameter(3, Types.DATE);
        statement.execute();

        Date startDate = statement.getDate(2);
        Date endDate = statement.getDate(3);

        startConcediu.setText(startDate.toString());
        sfarsitConcediu.setText(endDate.toString());

        this.setContentPane(holidaysPanel);
        this.setVisible(true);
        this.pack();
    }
}
