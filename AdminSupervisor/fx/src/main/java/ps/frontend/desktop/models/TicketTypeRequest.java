package ps.frontend.desktop.models;

public class TicketTypeRequest {
    
    private TicketType ticketType;
    private Integer [] transporterIds;
    
    public TicketTypeRequest(TicketType ticketType, Integer[] transporterIds) {
        this.ticketType = ticketType;
        this.transporterIds = transporterIds;
    }

    public TicketTypeRequest() {
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Integer[] getTransporterIds() {
        return transporterIds;
    }

    public void setTransporterIds(Integer[] transporterIds) {
        this.transporterIds = transporterIds;
    }
}
