package AppForms;

import org.example.Database;

import javax.swing.*;
import java.sql.*;

public class ScheduleForm extends JFrame{
    private JTextField startField;
    private JTextField endField;
    private JPanel schedulePanel;

    public ScheduleForm(String username) throws SQLException {


        Database database = new Database();
        Connection connection = database.getConnection();

        String query = "{call get_ore(?,?,?)};";

        CallableStatement statement = database.createCallableStatement(query);
        statement.setString(1, username);
        statement.registerOutParameter(2, Types.TIME);
        statement.registerOutParameter(3, Types.TIME);
        statement.execute();

        Time startDate = statement.getTime(2);
        Time endDate = statement.getTime(3);

        startField.setText(startDate.toString());
        endField.setText(endDate.toString());

        this.setContentPane(schedulePanel);
        this.setVisible(true);
        this.pack();
    }
}
