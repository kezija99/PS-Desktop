package ps.frontend.desktop.models;

import java.sql.Timestamp;

public class UserTicket{
 
    TicketType type;
    private Integer transaction_Id;
    private Integer userid;
    private Timestamp validUntilDate;
    private Integer usage;
    
    public UserTicket(TicketType type, Integer transaction_Id, Integer userid, Timestamp validUntilDate,
            Integer usage) {
        this.type = type;
        this.transaction_Id = transaction_Id;
        this.userid = userid;
        this.validUntilDate = validUntilDate;
        this.usage = usage;
    }

    public UserTicket() {
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Integer getTransaction_Id() {
        return transaction_Id;
    }

    public void setTransaction_Id(Integer transaction_Id) {
        this.transaction_Id = transaction_Id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Timestamp getValidUntilDate() {
        return validUntilDate;
    }

    public void setValidUntilDate(Timestamp validUntilDate) {
        this.validUntilDate = validUntilDate;
    }

    public Integer getUsage() {
        return usage;
    }

    public void setUsage(Integer usage) {
        this.usage = usage;
    }
}
