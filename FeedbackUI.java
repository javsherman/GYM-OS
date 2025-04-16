import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class FeedbackUI extends JFrame {
    private JTextField emailField;
    private JComboBox<Integer> ratingCombo;
    private JTextArea commentArea;

    public FeedbackUI() {
        setTitle("Submit Feedback");
        setSize(400, 400); // Increased height
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Feedback", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        titlePanel.setBounds(15, 10, 290, 40);

        // Form Panel (email + rating)
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        formPanel.setBounds(20, 50, 340, 100);

        emailField = new JTextField();
        Integer[] ratings = {1, 2, 3, 4, 5};
        ratingCombo = new JComboBox<>(ratings);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Rating (1-5):"));
        formPanel.add(ratingCombo);

        // Comment Panel (separate and larger)
        JPanel commentPanel = new JPanel(new BorderLayout());
        commentPanel.setBounds(20, 160, 340, 120);

        commentArea = new JTextArea(6, 20); // Larger height
        JScrollPane scrollPane = new JScrollPane(commentArea);
        commentPanel.add(new JLabel("Comments:"), BorderLayout.NORTH);
        commentPanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(15, 300, 350, 40);

        JButton submitButton = new JButton("Submit Feedback");
        submitButton.setPreferredSize(new Dimension(295, 25));
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusable(false);
        submitButton.addActionListener(new SubmitAction());

        buttonPanel.add(submitButton);

        // Add panels to frame
        add(titlePanel);
        add(formPanel);
        add(commentPanel);
        add(buttonPanel);

        setVisible(true);
    }

    private class SubmitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText().trim();
            int rating = (int) ratingCombo.getSelectedItem();
            String comments = commentArea.getText().trim();

            // Basic validation
            if (email.isEmpty() || comments.isEmpty()) {
                showError("Please fill in all fields");
                return;
            }

            if (comments.length() > 500) {
                showError("Comments must be less than 500 characters");
                return;
            }

            try {
                // Create member with email (other fields as placeholders)
                MembershipController membershipController = new MembershipController();
                Member member = membershipController.retrieveUserData(email);
                
                FeedbackController controller = new FeedbackController(member);

               

                if (member == null) {
                    showError("No account found with this email");
                    return;
                }
                
                // Set feedback details
                controller.setFeedbackDetails(comments, rating);
                
                // Store to database
                if (controller.storeChangesToDatabase()) {
                    // Send notifications
                    controller.sendNotificationMessage();
                    controller.sendConfirmationMessage();
                    
                    JOptionPane.showMessageDialog(FeedbackUI.this,
                        "Thank you for your feedback!");
                    clearForm();
                } else {
                    showError("Failed to save feedback");
                }
            } catch (Exception ex) {
                showError("Error processing feedback: " + ex.getMessage());
            }
        }

        private void showError(String message) {
            JOptionPane.showMessageDialog(FeedbackUI.this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }

        private void clearForm() {
            emailField.setText("");
            ratingCombo.setSelectedIndex(0);
            commentArea.setText("");
        }
    }
}