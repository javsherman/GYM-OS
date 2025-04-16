import java.security.SecureRandom;

public class ReferralCodeGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789"; // Excluded ambiguous characters
    private static final int CODE_LENGTH = 8;
    private static final SecureRandom random = new SecureRandom();

    public static String generateSimpleCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}