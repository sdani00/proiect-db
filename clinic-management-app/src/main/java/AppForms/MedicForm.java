package AppForms;

import org.example.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class MedicForm extends JFrame{
    private JPanel medicPanel;
    private JTextField ideMedicField;
    private JTextField idSpecField;
    private JTextField idAnField;
    private JTextField gradeField;
    private JTextField parafaField;
    private JTextField competenteField;
    private JTextField titlu;
    private JTextField post;
    private JTextField procentField;
    private JButton registerMedicButton;

    public MedicForm() {


        this.setContentPane(medicPanel);
        this.setVisible(true);
        this.pack();
        registerMedicButton.addActionListener(event -> {
            if(event.getSource() == registerMedicButton) {
                try {
                    this.addMedic();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void addMedic() throws SQLException {
        Database database = new Database();
        String query = "{call insert_medic(?,?,?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = database.createCallableStatement(query);
        //(IN idMedic INT, IN idSpec INT, IN idAn INT, IN gradul VARCHAR(15), IN parafa INT, IN competente VARCHAR(100), IN titlu VARCHAR(30), IN post VARCHAR(20), IN procent INT

        callableStatement.getInt(Integer.parseInt(ideMedicField.getText()));
        callableStatement.getInt(Integer.parseInt(idSpecField.getText()));
        callableStatement.getInt(Integer.parseInt(idAnField.getText()));
        callableStatement.getString(gradeField.getText());
        callableStatement.getInt(Integer.parseInt(parafaField.getText()));
        callableStatement.getString(competenteField.getText());
        callableStatement.getString(titlu.getText());
        callableStatement.getString(post.getText());
        callableStatement.getInt(Integer.parseInt(procentField.getText()));

        callableStatement.executeUpdate();
    }
}
