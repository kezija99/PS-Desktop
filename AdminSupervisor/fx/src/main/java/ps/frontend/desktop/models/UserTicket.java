package ps.frontend.desktop.models;

public class UserTicket {
 
    private String transporter;
    private String name;

	public UserTicket(String transporter, String name) {
		this.transporter = transporter;
		this.name = name;
	}

    public String getTransporter() {
        return transporter;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
