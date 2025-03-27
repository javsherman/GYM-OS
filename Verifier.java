import java.util.regex.Pattern;

class Verifier {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    
    public boolean verifyMemberDetails(String name, String email) {
        return !name.isEmpty() && EMAIL_PATTERN.matcher(email).matches();
    }
}