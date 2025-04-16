import java.awt.*;
import javax.swing.*;

public class RenewMembershipUI extends JFrame {

    public RenewMembershipUI() {
        setTitle("Renew Membership");
        setSize(360, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Title
        JLabel titleLabel = new JLabel("Renew Membership");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(20, 10, 300, 30);
        add(titleLabel);

        // Member ID Label and Field
        JLabel memberIdLabel = new JLabel("Member Email:");
        memberIdLabel.setBounds(20, 50, 100, 20);
        JTextField memberIdField = new JTextField();
        memberIdField.setBounds(20, 70, 300, 25);
        memberIdField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(memberIdLabel);
        add(memberIdField);

        // Renewal Period Label and ComboBox
        JLabel periodLabel = new JLabel("Renewal Period:");
        periodLabel.setBounds(20, 105, 150, 20);
        String[] periods = {"1 Month", "3 Months", "6 Months", "1 Year"};
        JComboBox<String> periodComboBox = new JComboBox<>(periods);
        periodComboBox.setBounds(20, 125, 300, 25);
        periodComboBox.setFocusable(false);
        add(periodLabel);
        add(periodComboBox);

        // Renew Button
        JButton renewButton = new JButton("Renew Membership");
        renewButton.setBounds(20, 165, 300, 30);
        renewButton.setBackground(new Color(0, 120, 215));
        renewButton.setForeground(Color.WHITE);
        renewButton.setFocusPainted(false);
        renewButton.setFont(new Font("Arial", Font.BOLD, 12));

        renewButton.addActionListener(e -> {
            String email = memberIdField.getText().trim();
    String period = (String) periodComboBox.getSelectedItem();

    // Email format validation
    if (email.isEmpty() || !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
        JOptionPane.showMessageDialog(this, 
            "Please enter a valid email address", 
            "Invalid Email", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    MembershipController controller = new MembershipController();
    Member member = controller.retrieveUserData(email);

    if (member == null) {
        JOptionPane.showMessageDialog(this, 
            "No member found with this email", 
            "Not Found", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }else{
        JOptionPane.showMessageDialog(this,
            "Renewal processed for:\n" +
            member.getMemberName() + "\n" +
            "Duration: " + period + "\n" +
            "Confirmation will be sent to: " + email);
        
        memberIdField.setText("");
        periodComboBox.setSelectedIndex(0);
        
        NotifyMember notifyMember = new NotifyMember(member);
        notifyMember.sendNotification();
    }
        });

        add(renewButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RenewMembershipUI::new);
    }
}
