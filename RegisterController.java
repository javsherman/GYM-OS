
import javax.swing.JOptionPane;

public class RegisterController {
    private MemberFormVerification verifier = new MemberFormVerification();
    private DatabaseController dbController = new DatabaseController();
    
    public boolean createMemberRecord(String name, String email, String password) {
        if (verifier.verifyMemberDetails(name, email)) {
            String hashedPassword = hashPassword(password);
            Member member = new Member(name, email, hashedPassword);
            dbController.updateDatabase(member);
            return true;
        } else {
            JOptionPane.showMessageDialog(null,"Invalid member details");
            return false;
        }
    }
    
    private String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
