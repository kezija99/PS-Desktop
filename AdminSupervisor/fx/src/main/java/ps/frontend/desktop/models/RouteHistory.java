package ps.frontend.desktop.models;

import java.sql.Timestamp;

public class RouteHistory {
    
    private RouteHistoryKey primaryKey;
    private Integer driverId;
    private Timestamp toDateTime;
    
    public RouteHistory(RouteHistoryKey primaryKey, Integer driverId, Timestamp toDateTime) {
        this.primaryKey = primaryKey;
        this.driverId = driverId;
        this.toDateTime = toDateTime;
    }

    public RouteHistory() {
    }

    public RouteHistoryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(RouteHistoryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Timestamp getToDateTime() {
        return toDateTime;
    }

    public void setToDateTime(Timestamp toDateTime) {
        this.toDateTime = toDateTime;
    }

}
