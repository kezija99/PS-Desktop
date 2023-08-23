package ps.frontend.desktop.models;

import java.math.BigDecimal;

public class User{

    private String lastName;
    private String firstName;
    private String email;
    BigDecimal credit;
    int id;
	
	public User(String lastName, String firstName, String email, BigDecimal credit, int id) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.credit = credit;
		this.id = id;
	}

	public User(String firstName, String lastName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public User(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    

}
