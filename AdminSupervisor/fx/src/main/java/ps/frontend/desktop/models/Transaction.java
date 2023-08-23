package ps.frontend.desktop.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
    
    private BigDecimal amount;
    private Timestamp timestamp;
    private Integer userId;
    private Integer ticketRequestResponseId;
    private Integer terminalId;
    private Integer supervisorId;
    private Integer id;
    
    public Transaction(BigDecimal amount, Timestamp timestamp, Integer userId, Integer ticketRequestResponseId,
            Integer terminalId, Integer supervisorId, Integer id) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.userId = userId;
        this.ticketRequestResponseId = ticketRequestResponseId;
        this.terminalId = terminalId;
        this.supervisorId = supervisorId;
        this.id = id;
    }

    public Transaction() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTicketRequestResponseId() {
        return ticketRequestResponseId;
    }

    public void setTicketRequestResponseId(Integer ticketRequestResponseId) {
        this.ticketRequestResponseId = ticketRequestResponseId;
    }

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
