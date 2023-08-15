package ps.frontend.desktop.models;

import java.math.BigDecimal;

public class Driver{

    private String LastName;
    private String Name;
    private String Status;
    int Pin;
    int JMB;
    int SupervisorID;

    public Driver(String name, String lastName, int pin, int jmb, int supervisorID, String status) {
        LastName = lastName;
        Name = name;
        Pin = pin;
        JMB = jmb;
        SupervisorID = supervisorID;
        Status = status;
    }

    public String getLastName() {
		return LastName;
	}
	public String getName() {
		return Name;
	}
	public String getStatus() {
		return Status;
	}
	public int getPin() {
		return Pin;
	}
	public int getJMB() {
		return JMB;
	}
    public int getSupervisorID() {
        return SupervisorID;
    }
}
