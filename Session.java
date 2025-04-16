

// Session.java
import java.util.Date;
import java.util.List;

public class Session {
    private String memberNameField;
    private String memberEmailField;
    private Date preferredTime;

    // Constructor
    public Session(String name, String email, Date time) {
        this.memberNameField = name;
        this.memberEmailField = email;
        this.preferredTime = time;
    }

    // Package-private getter for name
    String getMemberName() {
        return memberNameField;
    }

    public String getMemberEmail() {
        return memberEmailField;
    }

    public Date getPreferredTime() {
        return preferredTime;
    }

    public List<Date> getAvailableTimeslots() {
        // Return available timeslots (implementation omitted)
        return List.of();
    }

    public boolean isPreferredTimeAvailable(Date date, boolean flag) {
        // Check if the preferred time is available (implementation omitted)
        return false;
    }
}