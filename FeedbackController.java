import java.sql.*;
import javax.swing.JOptionPane;

public class FeedbackController {
    private Member member;  // Assuming "data" refers to a Member type
    private Connection connection;
    private String feedbackContent;
    private int feedbackRating;
    
    public FeedbackController() {
        try {
            String url = "jdbc:mysql://localhost:3306/members";
            String user = "root";
            String pass = "";
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }
    
    public FeedbackController(Member member) {
        this();
        this.member = member;
    }
    
    public void setFeedbackDetails(String content, int rating) {
        this.feedbackContent = content;
        this.feedbackRating = rating;
    }
    
    public boolean validateUser(Member userData) {
        if (userData == null || userData.getMemberEmail() == null || userData.getMemberEmail().isEmpty()) {
            return false;
        }
        
        try {
            String query = "SELECT * FROM members WHERE email = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, userData.getMemberEmail());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String status = rs.getString("status");
                return status != null && status.equalsIgnoreCase("active");
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error validating user: " + e.getMessage());
            return false;
        }
    }
    
    public boolean storeChangesToDatabase() {
        if (member == null || feedbackContent == null) {
            return false;
        }
        
        try {
            String query = "INSERT INTO Feedback (member_id, content, rating, submission_date) VALUES (?, ?, ?, NOW())";
            PreparedStatement stmt = connection.prepareStatement(query);
            
            stmt.setString(1, member.getMemberEmail());
            stmt.setString(2, feedbackContent);
            stmt.setInt(3, feedbackRating);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error storing feedback: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Failed to save feedback. Please try again later.", 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void sendNotificationMessage() {
        try {
            String query = "SELECT email FROM Administrators WHERE notifications_enabled = 1";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                String adminEmail = rs.getString("email");
                System.out.println("Notification email would be sent to: " + adminEmail);
                System.out.println("Subject: New Feedback Submission");
                System.out.println("Body: A new feedback has been submitted by " + 
                                  member.getMemberName() + " with rating " + feedbackRating);
            }
        } catch (SQLException e) {
            System.err.println("Error sending notification: " + e.getMessage());
        }
    }
    
    public void sendConfirmationMessage() {
        if (member == null || member.getMemberEmail() == null) {
            return;
        }
        
        System.out.println("Confirmation email would be sent to: " + member.getMemberEmail());
        System.out.println("Subject: Feedback Received - Thank You");
        System.out.println("Body: Dear " + member.getMemberName() + 
                          ",\n\nThank you for submitting your feedback. " +
                          "We appreciate your input and will use it to improve our services.\n\n" +
                          "Best regards,\nThe Membership Team");
        
        try {
            String query = "UPDATE Feedback SET confirmation_sent = 1 WHERE member_id = ? ORDER BY submission_date DESC LIMIT 1";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, member.getMemberEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error logging confirmation: " + e.getMessage());
        }
    }
    
    public void cleanup() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}