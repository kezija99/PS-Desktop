package ps.frontend.desktop.models;

import java.sql.Timestamp;

public class RouteHistoryKey {
    
    private Timestamp fromDateTime;
    private Integer routeId;
    private Integer terminalId;
    
    public RouteHistoryKey(Timestamp fromDateTime, Integer routeId, Integer terminalId) {
        this.fromDateTime = fromDateTime;
        this.routeId = routeId;
        this.terminalId = terminalId;
    }

    public RouteHistoryKey() {
    }

    public Timestamp getFromDateTime() {
        return fromDateTime;
    }

    public void setFromDateTime(Timestamp fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

}
