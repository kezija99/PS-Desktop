package ps.frontend.desktop.models;

public class UserToLogin {
    
    private String email;
    private String passwordHash;
    
    public UserToLogin(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public UserToLogin() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
