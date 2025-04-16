import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

class DatabaseController {

    // This method adds a new member's information into the MySQL database
    public void updateDatabase(Member member) {
        // Connection details for MySQL (adjust if port or credentials are different)
        String url = "jdbc:mysql://localhost:3306/members";  // Database URL
        String user = "root";                                // Username
        String password = "";                                // XAMPP default: no password

        try {
            // Load MySQL JDBC driver (required to establish DB connection)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Try-with-resources ensures connection is automatically closed
            try (Connection conn = DriverManager.getConnection(url, user, password)) {

                // SQL statement to insert member data into the Members table
                String query = "INSERT INTO Members (name, email, password) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);

                // Set values from the member object into the SQL query
                stmt.setString(1, member.getMemberName());
                stmt.setString(2, member.getMemberEmail());
                stmt.setString(3, member.getMemberPassword());

                // Execute the SQL insert
                stmt.executeUpdate();

                // Notify the user of success
                JOptionPane.showMessageDialog(null, "Member successfully registered");
            }

        } catch (ClassNotFoundException e) {
            // If the JDBC driver is not found
            System.err.println("JDBC Driver not found: " + e.getMessage());

        } catch (SQLException e) {
            // If any SQL or connection error occurs
            JOptionPane.showMessageDialog(null, "Error connecting to server.");
            e.printStackTrace();  // Print detailed SQL error in the console for debugging
        }
    }
}
