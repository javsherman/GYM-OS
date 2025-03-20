class Member {
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    
    public Member(String name, String email, String password) {
        this.memberName = name;
        this.memberEmail = email;
        this.memberPassword = password;
    }
    
    public String getMemberName() {
        return memberName;
    }
    
    public String getMemberEmail() {
        return memberEmail;
    }
    
    public String getMemberPassword() {
        return memberPassword;
    }
}