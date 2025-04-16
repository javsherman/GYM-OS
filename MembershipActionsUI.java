import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MembershipActionsUI extends JFrame {
    
    public void displayMembershipActions() {
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        // Title configuration
        JLabel titleLabel = new JLabel("Membership Actions", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);

        // Button configuration
        buttonPanel.setLayout(new GridLayout(5, 1, 0, 10)); // 3 rows, 1 column, 10px vertical gap

        JButton renewalButton = createStyledButton("Renewal");
        JButton upgradeButton = createStyledButton("Upgrade");
        JButton cancellationButton = createStyledButton("Cancel");
        JButton bookSessionButton = createStyledButton("Book Session");
        JButton giveFeedbackButton = createStyledButton("Give FeedBack");
        

        // Button action listeners

        renewalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RenewMembershipUI renewMembershipUI = new RenewMembershipUI();
            }
        });

        upgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpgradeMembershipUI upgradeMembershipUI = new UpgradeMembershipUI();
            }
        });

        cancellationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CancelMembershipUI cancelMembershipUI = new CancelMembershipUI();
            }
        });

        bookSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookSessionUI bookSessionUI = new BookSessionUI();
            }
        });

        giveFeedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FeedbackUI feedbackUI = new FeedbackUI();
            }
        });


        buttonPanel.add(renewalButton);
        buttonPanel.add(upgradeButton);
        buttonPanel.add(cancellationButton);
        buttonPanel.add(bookSessionButton);
        buttonPanel.add(giveFeedbackButton);

        // Panel positioning
        titlePanel.setBounds(15, 10, 290, 40);
        buttonPanel.setBounds(20, 50, 290, 200);

        // Add components to frame
        add(titlePanel);
        add(buttonPanel);

        // Frame configuration
        setLayout(null);
        setTitle("Membership Management");
        setSize(350, 300);
        setLocation(700, 270);
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(295, 10));
        button.setBackground(Color.BLUE);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

}