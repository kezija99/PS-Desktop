package ps.frontend.desktop.models;

import java.math.BigDecimal;

public class User {

    private String LastName;
    private String Name;
    private String Email;
    BigDecimal Credit;
    int Id;
    
	public User(String lastName, String name, String email, BigDecimal credit, int id) {
		LastName = lastName;
		Name = name;
		Email = email;
		Credit = credit;
		Id = id;
	}
	public String getLastName() {
		return LastName;
	}
	public String getName() {
		return Name;
	}
	public String getEmail() {
		return Email;
	}
	public BigDecimal getCredit() {
		return Credit;
	}
	public int getId() {
		return Id;
	}
}
