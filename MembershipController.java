import javax.swing.JOptionPane;

class MembershipController {
    private Member memberData;

    public MembershipController(Member member) {
        this.memberData = member;
    }

    public void sendConfirmationMessage() {
        JOptionPane.showMessageDialog(null, "Welcome " + memberData.getMemberName() + "! Your membership is confirmed.");
    }

    public boolean validateUser(Member member, boolean isValid) {
        // Basic validation logic
        if (member.getMemberEmail().contains("@") && !member.getMemberPassword().isEmpty()) {
            return isValid;
        }
        return false;
    }

    public Member retrieveUserData() {
        return memberData;
    }

    public void updateUserData(String newName, String newEmail, String newPassword) {
        this.memberData = new Member(newName, newEmail, newPassword);
        
    }

    public void storeChangesToDatabase() {
        DatabaseController dbController = new DatabaseController();
        dbController.updateDatabase(memberData);
    }
}
