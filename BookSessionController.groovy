import java.time.LocalDateTime;
import java.util.Objects;

public class BookSessionController {
    private Member member;
    private LocalDateTime sessionTime;
    private final DatabaseController dbController;
    private final NotificationService notificationService;

    public BookSessionController(DatabaseController dbController, 
                                NotificationService notificationService) {
        this.dbController = Objects.requireNonNull(dbController);
        this.notificationService = Objects.requireNonNull(notificationService);
    }

    public boolean validateUser(String email) {
        try {
            int userId = dbController.getUserIdByEmail(email);
            if (userId == -1) return false;
            
            // Additional validation could check membership status
            return dbController.isMembershipActive(userId);
        } catch (Exception e) {
            System.err.println("Validation error: " + e.getMessage());
            return false;
        }
    }

    public boolean createSessionRecord(String email, LocalDateTime preferredTime) {
        try {
            int userId = dbController.getUserIdByEmail(email);
            if (userId == -1) return false;

            return dbController.bookTrainingSession(userId, preferredTime);
        } catch (Exception e) {
            System.err.println("Session creation failed: " + e.getMessage());
            return false;
        }
    }

    public void sendConfirmation(String email, LocalDateTime sessionTime) {
        String message = String.format(
            "Your training session is booked for: %tF %tR",
            sessionTime, sessionTime
        );
        
        notificationService.sendConfirmation(
            email,
            "Training Session Confirmation",
            message
        );
    }

    public void processBooking(String email, LocalDateTime preferredTime) {
        if (!validateUser(email)) {
            throw new IllegalStateException("User validation failed");
        }
        
        if (createSessionRecord(email, preferredTime)) {
            sendConfirmation(email, preferredTime);
        } else {
            throw new IllegalStateException("Failed to create session record");
        }
    }
}