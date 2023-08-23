package ps.frontend.desktop.models;

public class Controller {
    
    private String pin;
    private Boolean isActive;
    private Integer id;
    private String password;
    private String lastname;
    private String jmb;
    private String firstname;

    public Controller(String pin, Boolean isActive, Integer id, String password, String lastname, String jmb,
            String firstname) {
        this.pin = pin;
        this.isActive = isActive;
        this.id = id;
        this.password = password;
        this.lastname = lastname;
        this.jmb = jmb;
        this.firstname = firstname;
    }

    
    public Controller(String pin, String firstname, String lastname, String jmb) {
        this.pin = pin;
        this.lastname = lastname;
        this.jmb = jmb;
        this.firstname = firstname;
    }


    public Controller() {
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getJmb() {
        return jmb;
    }

    public void setJmb(String jmb) {
        this.jmb = jmb;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

}
