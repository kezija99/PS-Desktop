package ps.frontend.desktop.models;

import java.math.BigDecimal;

public class User{

	private String documentName1;
	private String documentName2;
	private String documentName3;
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

	public User(String documentName1, String documentName2, String documentName3, String lastName, String firstName,
			String email, BigDecimal credit, int id) {
		this.documentName1 = documentName1;
		this.documentName2 = documentName2;
		this.documentName3 = documentName3;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.credit = credit;
		this.id = id;
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

	public String getDocumentName1() {
		return documentName1;
	}

	public void setDocumentName1(String documentName1) {
		this.documentName1 = documentName1;
	}

	public String getDocumentName2() {
		return documentName2;
	}

	public void setDocumentName2(String documentName2) {
		this.documentName2 = documentName2;
	}

	public String getDocumentName3() {
		return documentName3;
	}

	public void setDocumentName3(String documentName3) {
		this.documentName3 = documentName3;
	}
    

}
