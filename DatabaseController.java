import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

class DatabaseController {
    public void updateDatabase(Member member) {
        String url = "jdbc:mysql://localhost:3306/members";  // Change to 3307 if needed
        String user = "root";
        String password = "";  // XAMPP default has no password

        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                String query = "INSERT INTO Members (name, email, password) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, member.getMemberName());
                stmt.setString(2, member.getMemberEmail());
                stmt.setString(3, member.getMemberPassword());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Member successfully registered");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connection to server.");
            e.printStackTrace();  // Prints full SQL error details
        }
    }
}
