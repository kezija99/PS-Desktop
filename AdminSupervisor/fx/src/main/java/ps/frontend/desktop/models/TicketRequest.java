package ps.frontend.desktop.models;

import java.sql.Timestamp;

public class TicketRequest {
    
    private Integer userId;
    private Integer ticketTypeId;
    private Integer id;
    private Timestamp dateTime;
    
    public TicketRequest(Integer userId, Integer ticketTypeId, Integer id, Timestamp dateTime) {
        this.userId = userId;
        this.ticketTypeId = ticketTypeId;
        this.id = id;
        this.dateTime = dateTime;
    }

    public TicketRequest() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Integer ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String toString(){
        return "Id zahtjeva " + id + "| " + "Id korisnika " + userId + "| Poslan " + dateTime;
    }
}
