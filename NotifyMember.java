public class NotifyMember {
    private Member memberdata;

    // Constructor
    public NotifyMember(Member data) {
        this.memberdata = data;
    }

    // Validate the user data with a boolean flag
    public boolean validateUser(Member d, boolean condition) {
        if (d == null || d.getMemberEmail() == null || d.getMemberEmail().isEmpty()) {
            return false;
        }
        return condition; // Additional validation logic can go here
    }

    // Send notification to user
    public void sendNotification() {
        if (memberdata != null) {
            System.out.println("Notification sent to: " + memberdata.getMemberName() +
                               " at " + memberdata.getMemberEmail());
        } else {
            System.out.println("No member data found.");
        }
    }
}
