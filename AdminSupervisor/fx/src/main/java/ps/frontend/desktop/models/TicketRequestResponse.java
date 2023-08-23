package ps.frontend.desktop.models;

public class TicketRequestResponse {
    
    private Integer id;
    private String comment;
    private Integer ticketRequestId;
    private Boolean approved;
    private Integer supervisorId;
    
    public TicketRequestResponse(Integer id, String comment, Integer ticketRequestId, Boolean approved,
            Integer supervisorId) {
        this.id = id;
        this.comment = comment;
        this.ticketRequestId = ticketRequestId;
        this.approved = approved;
        this.supervisorId = supervisorId;
    }

    public TicketRequestResponse(String comment, Integer ticketRequestId, Boolean approved, Integer supervisorId) {
        this.comment = comment;
        this.ticketRequestId = ticketRequestId;
        this.approved = approved;
        this.supervisorId = supervisorId;
    }

    public TicketRequestResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getTicketRequestId() {
        return ticketRequestId;
    }

    public void setTicketRequestId(Integer ticketRequestId) {
        this.ticketRequestId = ticketRequestId;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }
}
