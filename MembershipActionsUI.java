import java.awt.*;
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
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 10)); // 3 rows, 1 column, 10px vertical gap

        JButton renewalButton = createStyledButton("Renewal");
        JButton upgradeButton = createStyledButton("Upgrade");
        JButton cancellationButton = createStyledButton("Cancellation");

        buttonPanel.add(renewalButton);
        buttonPanel.add(upgradeButton);
        buttonPanel.add(cancellationButton);

        // Panel positioning
        titlePanel.setBounds(15, 10, 290, 40);
        buttonPanel.setBounds(20, 50, 290, 100);

        // Add components to frame
        add(titlePanel);
        add(buttonPanel);

        // Frame configuration
        setLayout(null);
        setTitle("Membership Management");
        setSize(350, 200);
        setLocation(700, 270);
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(295, 10));
        button.setBackground(Color.BLUE);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Add your action listeners here
        button.addActionListener(e -> {
            // Handle button actions
            System.out.println(text + " button clicked");
        });
        
        return button;
    }

}