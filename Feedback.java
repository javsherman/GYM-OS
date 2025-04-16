public class Feedback {
    private String email;
    private int rating;
    private String comments;

    public Feedback(String email, int rating, String comments) {
        this.email = email;
        this.rating = rating;
        this.comments = comments;
    }

    // Getters
    public String getEmail() { return email; }
    public int getRating() { return rating; }
    public String getComments() { return comments; }
}