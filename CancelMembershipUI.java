import java.awt.*;
import javax.swing.*;

public class CancelMembershipUI extends JFrame {

    private JTextField memberIdField;
    private JTextArea reasonArea;

    public CancelMembershipUI() {
        setTitle("Cancel Membership");
        setSize(360, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Member ID Label and Field
        JLabel memberIdLabel = new JLabel("Member Email:");
        memberIdLabel.setBounds(20, 20, 100, 25);
        memberIdField = new JTextField();
        memberIdField.setBounds(120, 20, 200, 25);

        // Reason Label and Text Area
        JLabel reasonLabel = new JLabel("Reason (Optional):");
        reasonLabel.setBounds(20, 60, 150, 25);
        reasonArea = new JTextArea(4, 20);
        reasonArea.setLineWrap(true);
        reasonArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(reasonArea);
        scrollPane.setBounds(20, 85, 300, 70);

        // Cancel Button
        JButton cancelButton = new JButton("Cancel Membership");
        cancelButton.setBounds(20, 170, 300, 30);
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> {
            String email = memberIdField.getText().trim();
            String reason = reasonArea.getText().trim();

            // Email format validation
            if (email.isEmpty() || !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter a valid email.", 
                    "Invalid Email", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            MembershipController controller = new MembershipController();
            Member member = controller.retrieveUserData(email);

            if (member == null) {
                JOptionPane.showMessageDialog(this, 
                    "No member found with this email.", 
                    "Not Found", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel membership for:\n" +
                member.getMemberName() + " (" + email + ")?\n" +
                "Reason: " + reason,
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Removed cancelMembership call
                JOptionPane.showMessageDialog(this,
                    "Cancellation request processed for:\n" +
                    member.getMemberName() + "\n" +
                    "A confirmation will be sent to: " + email);
                
                memberIdField.setText("");
                reasonArea.setText("");
                }
                NotifyMember notifyMember = new NotifyMember(member);
                notifyMember.sendNotification();
    });

        // Add components
        add(memberIdLabel);
        add(memberIdField);
        add(reasonLabel);
        add(scrollPane);
        add(cancelButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new CancelMembershipUI();
    }
}
