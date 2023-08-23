package ps.frontend.desktop.models;

import java.sql.Timestamp;

public class ScanInterractionKey {
    
    private Timestamp fromDateTime;
    private Integer routeHistoryRouteId;
    private Integer routeHistoryTerminalId;
    private Integer userId;
    private Timestamp time;
    
    public ScanInterractionKey(Timestamp fromDateTime, Integer routeHistoryRouteId, Integer routeHistoryTerminalId,
            Integer userId, Timestamp time) {
        this.fromDateTime = fromDateTime;
        this.routeHistoryRouteId = routeHistoryRouteId;
        this.routeHistoryTerminalId = routeHistoryTerminalId;
        this.userId = userId;
        this.time = time;
    }

    public ScanInterractionKey() {
    }

    public Timestamp getFromDateTime() {
        return fromDateTime;
    }

    public void setFromDateTime(Timestamp fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public Integer getRouteHistoryRouteId() {
        return routeHistoryRouteId;
    }

    public void setRouteHistoryRouteId(Integer routeHistoryRouteId) {
        this.routeHistoryRouteId = routeHistoryRouteId;
    }

    public Integer getRouteHistoryTerminalId() {
        return routeHistoryTerminalId;
    }

    public void setRouteHistoryTerminalId(Integer routeHistoryTerminalId) {
        this.routeHistoryTerminalId = routeHistoryTerminalId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

}
