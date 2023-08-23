package ps.frontend.desktop.models;

public class SupervisorWithPassword {
    
    private String email;
    private String firstName;
    private String lastName;
    private String passwordHash;
    private Integer transporterId;


    public SupervisorWithPassword(String email, String firstName, String lastName, String passwordHash,
            Integer transporterId) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.transporterId = transporterId;
    }

    public SupervisorWithPassword() {
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(Integer transporterId) {
        this.transporterId = transporterId;
    }

}
