package ps.frontend.desktop.models;

public class TerminalActivationRequest {
    
    private Integer transporterId;
    private Boolean processed;
    private Integer id;
    private String serialNumber;
    
    public TerminalActivationRequest(Integer transporterId, Boolean processed, Integer id, String serialNumber) {
        this.transporterId = transporterId;
        this.processed = processed;
        this.id = id;
        this.serialNumber = serialNumber;
    }

    public TerminalActivationRequest() {
    }

    public Integer getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(Integer transporterId) {
        this.transporterId = transporterId;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
