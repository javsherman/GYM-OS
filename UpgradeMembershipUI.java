import java.awt.*;
import javax.swing.*;

public class UpgradeMembershipUI extends JFrame {

    private JTextField memberIdField;
    private JComboBox<String> planComboBox;

    public UpgradeMembershipUI() {
        setTitle("Upgrade Membership");
        setSize(360, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Member ID
        JLabel memberIdLabel = new JLabel("Member Email:");
        memberIdLabel.setBounds(20, 20, 100, 25);
        memberIdField = new JTextField();
        memberIdField.setBounds(120, 20, 200, 25);

        // Plan Selection
        JLabel planLabel = new JLabel("New Plan:");
        planLabel.setBounds(20, 60, 100, 25);
        String[] plans = {"Basic", "Premium", "Gold"};
        planComboBox = new JComboBox<>(plans);
        planComboBox.setBounds(120, 60, 200, 25);

        // Upgrade Button
        JButton upgradeButton = new JButton("Upgrade Membership");
        upgradeButton.setBounds(20, 110, 300, 30);
        upgradeButton.setBackground(Color.BLUE);
        upgradeButton.setForeground(Color.WHITE);
        upgradeButton.setFocusPainted(false);
        upgradeButton.addActionListener(e -> {
            String email = memberIdField.getText().trim();
    String plan = (String) planComboBox.getSelectedItem();

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
            "Membership upgraded for:\n" +
            member.getMemberName() + "\n" +
            "New Plan: " + plan + "\n" +
            "Confirmation will be sent to: " + email);
        
        memberIdField.setText("");
        planComboBox.setSelectedIndex(0);
        
        NotifyMember notifyMember = new NotifyMember(member);
        notifyMember.sendNotification();
    }
        });

        // Add components
        add(memberIdLabel);
        add(memberIdField);
        add(planLabel);
        add(planComboBox);
        add(upgradeButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new UpgradeMembershipUI();
    }
}
