import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentForm extends JFrame {

    // Declare components
    private JTextField txtName, txtRoll, txtMarksObtained, txtTotalMarks;
    private JButton btnSave;

    public StudentForm() {
        // Frame properties
        setTitle("Student Marks Entry");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Create labels and text fields
        JLabel lblName = new JLabel("Student Name:");
        txtName = new JTextField();

        JLabel lblRoll = new JLabel("Roll Number:");
        txtRoll = new JTextField();

        JLabel lblMarksObtained = new JLabel("Obtained:");
        txtMarksObtained = new JTextField();

        JLabel lblTotalMarks = new JLabel("Total:");
        txtTotalMarks = new JTextField();

        btnSave = new JButton("Save");

        // Add components to frame
        add(lblName); add(txtName);
        add(lblRoll); add(txtRoll);
        add(lblMarksObtained); add(txtMarksObtained);
        add(lblTotalMarks); add(txtTotalMarks);
        add(new JLabel(""));  // empty cell
        add(btnSave);

        // Add event listener to button
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateAndSave();
            }
        });
    }

    private void validateAndSave() {
        String name = txtName.getText().trim();
        String roll = txtRoll.getText().trim();
        String marksObtainedText = txtMarksObtained.getText().trim();
        String totalMarksText = txtTotalMarks.getText().trim();

        // Validation
        if (name.isEmpty() || roll.isEmpty() || marksObtainedText.isEmpty() || totalMarksText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double marksObtained = Double.parseDouble(marksObtainedText);
            double totalMarks = Double.parseDouble(totalMarksText);

            if (marksObtained < 0 || totalMarks <= 0 || marksObtained > totalMarks) {
                JOptionPane.showMessageDialog(this, "Invalid marks entered!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double percentage = (marksObtained / totalMarks) * 100;

            JOptionPane.showMessageDialog(this,
                    "Student Saved Successfully!\n\n" +
                            "Name: " + name + "\n" +
                            "Roll No: " + roll + "\n" +
                            "Percentage: " + String.format("%.2f", percentage) + "%",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Marks must be numeric values!", "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtRoll.setText("");
        txtMarksObtained.setText("");
        txtTotalMarks.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentForm().setVisible(true);
        });
    }
}
