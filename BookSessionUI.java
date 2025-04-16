import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BookSessionUI extends JFrame {
    private JTextField memberNameField;
    private JTextField memberEmailField;
    private JTextField preferredTimeField;
    private JComboBox<String> trainerComboBox;
    private JButton bookSessionButton;

    public BookSessionUI() {
        setTitle("Book Training Session");
        setSize(360, 300); // Increased height
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Title Label
        JLabel titleLabel = new JLabel("Training Session Booking");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(20, 10, 300, 25);
        add(titleLabel);

        // Labels and Fields
        JLabel nameLabel = new JLabel("Member Name:");
        nameLabel.setBounds(20, 50, 100, 25);
        memberNameField = new JTextField();
        memberNameField.setBounds(130, 50, 180, 25);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 85, 100, 25);
        memberEmailField = new JTextField();
        memberEmailField.setBounds(130, 85, 180, 25);

        JLabel timeLabel = new JLabel("Preferred Time:");
        timeLabel.setBounds(20, 120, 100, 25);
        preferredTimeField = new JTextField();
        preferredTimeField.setBounds(130, 120, 180, 25);

        // Trainer Selection
        JLabel trainerLabel = new JLabel("Select Trainer:");
        trainerLabel.setBounds(20, 155, 100, 25);
        String[] trainers = {"Alex Levinson", "Trevil Harris", "Jordan Crown", "Sarah Wilson", "Cavil Martin"};
        trainerComboBox = new JComboBox<>(trainers);
        trainerComboBox.setBounds(130, 155, 180, 25);

        // Book Button
        bookSessionButton = new JButton("Book Session");
        bookSessionButton.setBounds(80, 210, 180, 30); // Moved down
        bookSessionButton.setBackground(Color.BLUE);
        bookSessionButton.setForeground(Color.WHITE);
        bookSessionButton.setFocusPainted(false);
        bookSessionButton.addActionListener(new BookAction());

        // Add components to frame
        add(nameLabel);
        add(memberNameField);
        add(emailLabel);
        add(memberEmailField);
        add(timeLabel);
        add(preferredTimeField);
        add(trainerLabel);
        add(trainerComboBox);
        add(bookSessionButton);

        setVisible(true);
    }

    public void displayBookSessionForm() {
        setVisible(true);
    }

    private class BookAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = memberNameField.getText().trim();
            String email = memberEmailField.getText().trim();
            String time = preferredTimeField.getText().trim();
            String trainer = (String) trainerComboBox.getSelectedItem();

            // ... [existing validation code] ...

            JOptionPane.showMessageDialog(BookSessionUI.this,
                "Booking successful!\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Time: " + time + "\n" +
                "Trainer: " + trainer);
            
            clearForm();
        }

        private boolean isValidEmail(String email) {
            return email.contains("@") && email.contains(".");
        }

        private boolean isValidTimeFormat(String time) {
            return time.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$");
        }

        private void showError(String message) {
            JOptionPane.showMessageDialog(BookSessionUI.this,
                message,
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
        }

        private void clearForm() {
            memberNameField.setText("");
            memberEmailField.setText("");
            preferredTimeField.setText("");
            trainerComboBox.setSelectedIndex(0); // Reset to first trainer
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookSessionUI().displayBookSessionForm());
    }
}
