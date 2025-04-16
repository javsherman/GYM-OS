import java.sql.*;
import javax.swing.JOptionPane;

public class MembershipController {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/members";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    

    // Simplified validation - only checks email existence
    public boolean validateUser(String email) {
        String query = "SELECT email FROM members WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, email);
            return stmt.executeQuery().next(); // True if email exists
            
        } catch (SQLException e) {
            handleError("Validation Error", e);
            return false;
        }
    }

    // Verify referral code (unchanged)
    public boolean verifyReferralCode(String code) {
        String query = "SELECT expiration_date FROM referralcodes WHERE code = ? AND used = 0";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getDate("expiration_date").after(new Date(System.currentTimeMillis()));
            
        } catch (SQLException e) {
            handleError("Referral Code Error", e);
            return false;
        }
    }

    // Retrieve user data without status
    public Member retrieveUserData(String email) {
        String query = "SELECT name, email, password FROM members WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Member(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
                );
            }
            return null;
            
        } catch (SQLException e) {
            handleError("Data Retrieval Error", e);
            return null;
        }
    }

    // Update user data without status
    public boolean updateUserData(Member updatedMember) {
        String query = "UPDATE members SET name = ?, password = ? WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, updatedMember.getMemberName());
            stmt.setString(2, updatedMember.getMemberPassword());
            stmt.setString(3, updatedMember.getMemberEmail());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            handleError("Update Error", e);
            return false;
        }
    }

    // Simplified confirmation message
    public void sendConfirmationMessage(String email) {
        JOptionPane.showMessageDialog(null,
            "Membership updated for: " + email,
            "Confirmation",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private void handleError(String context, SQLException e) {
        System.err.println(context + ": " + e.getMessage());
        JOptionPane.showMessageDialog(null,
            "Database Error: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
}

// Simplified Member class without status