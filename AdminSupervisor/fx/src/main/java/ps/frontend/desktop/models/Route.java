package ps.frontend.desktop.models;

public class Route {
    
    private String name;
    private boolean isActive = true;
    private Integer transporterId;
    private Integer id;
    
    public Route(String name, boolean isActive, Integer transporterId, Integer id) {
        this.name = name;
        this.isActive = isActive;
        this.transporterId = transporterId;
        this.id = id;
    }

    public Route(String name, Integer transporterId) {
        this.name = name;
        this.transporterId = transporterId;
    }

    public Route() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
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
