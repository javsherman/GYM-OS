import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MembershipUI {
    private JButton manageMembershipOptions;
    private JButton membershipDetails;
    private MembershipController membershipController;
    
    public MembershipUI(MembershipController controller) {
        this.membershipController = controller;
        
        JFrame frame = new JFrame("Membership UI");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10)); // Added spacing between buttons
        
        manageMembershipOptions = new JButton("Manage Membership");
        manageMembershipOptions.setPreferredSize(new Dimension(295, 25));
        manageMembershipOptions.setBackground(Color.BLUE);
        manageMembershipOptions.setForeground(Color.WHITE);
        manageMembershipOptions.setFocusable(false);
        
        membershipDetails = new JButton("View Membership Details");
        membershipDetails.setPreferredSize(new Dimension(295, 25));
        membershipDetails.setBackground(Color.BLUE);
        membershipDetails.setForeground(Color.WHITE);
        membershipDetails.setFocusable(false);
        
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
        
        panel.add(manageMembershipOptions);
        panel.add(membershipDetails);
        
        frame.add(panel);
        frame.setVisible(true);
    }
    
    public void displayManageMembershipOptions() {
        JOptionPane.showMessageDialog(null, "Manage membership options coming soon...");
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