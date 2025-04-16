import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;

public class LoginSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginUI loginUI = new LoginUI();
            loginUI.setVisible(true);
        });
    }
}

class LoginUI extends JFrame {
    private WatermarkTextField emailField;
    private JPasswordField passwordField;
    private final MembershipController membershipController;

    public LoginUI() {
        membershipController = new MembershipController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("User Login");
        setSize(350, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(15, 10, 290, 40);
        JLabel titleLabel = new JLabel("Login", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.setBounds(20, 50, 290, 80);
        emailField = new WatermarkTextField("Enter your email i.e. example@example.com");
        passwordField = new JPasswordField(25);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(15, 140, 300, 40);
        JButton loginButton = createLoginButton();
        buttonPanel.add(loginButton);

        add(titlePanel);
        add(formPanel);
        add(buttonPanel);
    }

    private JButton createLoginButton() {
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(295, 25));
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusable(false);

        loginButton.addActionListener(this::handleLogin);
        return loginButton;
    }

    private void handleLogin(ActionEvent e) {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
    
        if (!validateInputs(email, password)) return;
    
        try {
            // 1. Retrieve user data
            Member member = membershipController.retrieveUserData(email);
            
            // 2. Validate locally
            if (member != null) {
                String hashedInput = sha1Hash(password);
                String storedHash = member.getMemberPassword();
                
                if (hashedInput.equals(storedHash)) {
                    handleSuccessfulLogin(member);
                } else {
                    showError("Invalid password");
                    System.err.println(hashedInput);
                }
            } else {
                showError("Account not found");
            }
        } catch (Exception ex) {
            showError("Authentication error: " + ex.getMessage());
        }
    }

    private boolean validateInputs(String email, String password) {
        if (email.isEmpty() || email.equals(emailField.getWatermark())) {
            showError("Please enter your email");
            return false;
        }
        if (password.isEmpty()) {
            showError("Please enter your password");
            return false;
        }
        return true;
    }

    private void handleSuccessfulLogin(Member member) {
        JOptionPane.showMessageDialog(this, "Login successful!");
        MembershipUI membershipUI = new MembershipUI(null);
        membershipUI.setVisible(true);
        dispose();
    }

    private String sha1Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hash = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }

    private static class WatermarkTextField extends JTextField {
        private final String watermark;

        public WatermarkTextField(String watermark) {
            super(watermark);
            this.watermark = watermark;
            setForeground(Color.GRAY);
            addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    if (getText().equals(watermark)) {
                        setText("");
                        setForeground(Color.BLACK);
                    }
                }

                public void focusLost(java.awt.event.FocusEvent evt) {
                    if (getText().isEmpty()) {
                        setText(watermark);
                        setForeground(Color.GRAY);
                    }
                }
            });
        }

        public String getWatermark() { return watermark; }
    }
}