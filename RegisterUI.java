import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * RegisterUI class provides a graphical user interface for new user registration
 * It extends JFrame to create a window for the registration form
 */
public class RegisterUI extends JFrame {
    // Form input fields for collecting user data
    private JTextField memberNameField, memberEmailField;
    private JPasswordField memberPasswordField;

    /**
     * Creates and displays the registration form with all necessary components
     */
    public void displayMembershipForm() {
        // Create panels to organize UI components
        JPanel wordPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel titlePanel = new JPanel();

        // Set font for options
        Font addOptFont = new Font("Arial", Font.HANGING_BASELINE, 12);

        // Create and configure the title label
        JLabel titleLabel = new JLabel("Register",SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Bigger title
        titlePanel.add(titleLabel);

        // Creation of Username JLabel and its corresponding JTextfield text box
        wordPanel.add(new JLabel("Name:"));
        memberNameField = new WatermarkTextField("Enter your name i.e John Brown");
        
        wordPanel.add(memberNameField);// Add Word and textbox to panel

        // Creation of Password JLabel and its corresponding JTextfield text box
        wordPanel.add(new JLabel("Email:"));
        memberEmailField = new WatermarkTextField("Enter your email i.e. example@example.com");
        
        wordPanel.add(memberEmailField);// Add password and its textbox to panel


        // Creation of Password JLabel and its corresponding JTextfield text box
        wordPanel.add(new JLabel("Password:"));
        memberPasswordField = new JPasswordField(25);
        wordPanel.add(memberPasswordField);// Add password and its textbox to panel


        // Creates the Register button
        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(295, 25));// sets the size of the button
        registerButton.setBackground(Color.BLUE);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusable(false);
        registerButton.addActionListener(new RegisterActionListener());// Action Listener for register button


        buttonPanel.add(registerButton);// Adds register button to Panel

        
        // Adjust the postioning of the panel that holds the components
        titlePanel.setBounds(15, 10, 290, 40);// Panel with the title "Register"
        //titlePanel.setBackground(Color.green);
        wordPanel.setBounds(20, 50, 290, 120);// Panel with all the textfields,textboxes (name etc.)
        //wordPanel.setBackground(Color.cyan);
        buttonPanel.setBounds(15, 178, 300, 40);// Panel with Register button
        //buttonPanel.setBackground(Color.red);

        wordPanel.setLayout(new GridLayout(6, 2));// Adjust how the textfield,textboxes fit on the frame(
                                                   // name etc.)

        add(wordPanel, BorderLayout.CENTER);// Adds the panel with the textfields,textboxes to the frame(Username,first
                                            // name etc.)
        add(buttonPanel);// Adds the Panel that holds the register button to the frame
        add(titlePanel);// Adds the panel that holds the title "Register" to the frame
        setLayout(null);// Removes the default layout Manager
        setTitle("Create new Login");// Sets title of the frame
        setSize(350, 270);// Set the size of thee frame
        setLocationRelativeTo(null);// Removes the default positioning of the frame when opened
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Sets the Close opertion of the fram
        setVisible(true);// SShows the frame on screen
    }

    /**
     * Inner class that extends JTextField to add watermark functionality
     * Displays hint text in gray when the field is empty
     */
    private class WatermarkTextField extends JTextField {
        private String watermark;

        /**
         * Constructor for WatermarkTextField
         * @param watermark The hint text to display when field is empty
         */
        public WatermarkTextField(String watermark) {
            super(watermark);
            this.watermark = watermark;
            setForeground(Color.GRAY);
            addFocusListener(new java.awt.event.FocusAdapter() {
                /**
                 * When field gains focus, clear watermark if it's showing
                 */
                public void focusGained(java.awt.event.FocusEvent evt) {
                    if (getText().equals(watermark)) {
                        setText("");
                        setForeground(Color.BLACK);
                    }  
                }

                /**
                 * When field loses focus, restore watermark if empty
                 */
                public void focusLost(java.awt.event.FocusEvent evt) {
                    if (getText().isEmpty()) {
                        setText(watermark);
                        setForeground(Color.GRAY);
                    }
                }
            });
        }
    } 


    /**
     * Inner class to handle registration button click events
     * Implements ActionListener interface
     */    
    private class RegisterActionListener implements ActionListener {
        
        /**
         * Called when the register button is clicked
         * Gets form data and passes it to the RegisterController
         * @param e The action event triggered by button click
         */
        public void actionPerformed(ActionEvent e) {
            String password = new String(memberPasswordField.getPassword());
                RegisterController controller = new RegisterController();
                
                
                if(controller.createMemberRecord(memberNameField.getText().trim(), memberEmailField.getText().trim(), password)){
                    // Reset form fields after successful registration
                    memberNameField.setText(new WatermarkTextField("Enter your name i.e John Brown").watermark);
                    memberNameField.setForeground(Color.GRAY);

                    memberEmailField.setText(new WatermarkTextField("Enter your email i.e. example@example.com").watermark);
                    memberEmailField.setForeground(Color.GRAY);

                    memberPasswordField.setText("");
                }

        }
        
    }
}