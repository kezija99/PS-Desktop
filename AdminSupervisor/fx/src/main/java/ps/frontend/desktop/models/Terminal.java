package ps.frontend.desktop.models;

public class Terminal {
    
    private boolean isActive;
    private Integer activationRequestId;
    private Integer transporterId;
    private Integer id;

    public Terminal(boolean isActive, Integer activationRequestId, Integer transporterId, Integer id) {
        this.isActive = isActive;
        this.activationRequestId = activationRequestId;
        this.transporterId = transporterId;
        this.id = id;
    }

    public Terminal() {
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getActivationRequestId() {
        return activationRequestId;
    }

    public void setActivationRequestId(Integer activationRequestId) {
        this.activationRequestId = activationRequestId;
    }

    public Integer getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(Integer transporterId) {
        this.transporterId = transporterId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
