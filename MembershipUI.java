import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class MembershipUI extends JFrame {
    private JButton manageMembershipOptions;
    private JButton membershipDetails;
    private MembershipController membershipController;
    
    public MembershipUI(MembershipController controller) {
        this.membershipController = controller;
        
        // Panels
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        
        // Title Label
        JLabel titleLabel = new JLabel("Manage Members", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Bigger title
        titlePanel.add(titleLabel);
        
        // Buttons
        manageMembershipOptions = new JButton("Manage Membership");
        manageMembershipOptions.setPreferredSize(new Dimension(250, 20));
        manageMembershipOptions.setBackground(Color.BLUE);
        manageMembershipOptions.setForeground(Color.WHITE);
        manageMembershipOptions.setFocusable(false);
        
        membershipDetails = new JButton("View Membership Details");
        membershipDetails.setPreferredSize(new Dimension(250, 20    ));
        membershipDetails.setBackground(Color.BLUE);
        membershipDetails.setForeground(Color.WHITE);
        membershipDetails.setFocusable(false);
        
        // Action Listeners
        manageMembershipOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayManageMembershipOptions();
            }
        });
        
        membershipDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMembershipDetails();
            }
        });
        
        // Add buttons to panel
        buttonPanel.add(manageMembershipOptions);
        buttonPanel.add(membershipDetails);
        
        // Adjust Panel Positioning
        titlePanel.setBounds(15, 10, 290, 40);
        buttonPanel.setBounds(15, 60, 300, 80);
        
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        
        // Add components to frame
        add(titlePanel);
        add(buttonPanel);
        
        // Frame settings
        setLayout(null);
        setTitle("Membership Management");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void displayManageMembershipOptions() {
        MembershipActionsUI membershipActionsUI = new MembershipActionsUI();
        membershipActionsUI.displayMembershipActions();
    }
    
    public void displayMembershipDetails() {
        Member member = membershipController.retrieveUserData();
        JOptionPane.showMessageDialog(null, "Member Name: " + member.getMemberName() +
                "\nEmail: " + member.getMemberEmail());
    }
    
    public void displayConfirmationMessage() {
        membershipController.sendConfirmationMessage();
    }
}
