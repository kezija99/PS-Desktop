package ps.frontend.desktop.models;

import java.math.BigDecimal;

public class TicketType {
    
    private String type;
    private Boolean inUse = true;
    private Integer amount;
    private Integer validFor;
    private String name;
    private Integer id;
    private String documentaionName;
    private Boolean needsDocumentaion;
    private BigDecimal cost;
    
    public TicketType(String type, Boolean inUse, Integer amount, Integer validFor, String name, Integer id,
            String documentaionName, Boolean needsDocumentaion, BigDecimal cost) {
        this.type = type;
        this.inUse = inUse;
        this.amount = amount;
        this.validFor = validFor;
        this.name = name;
        this.id = id;
        this.documentaionName = documentaionName;
        this.needsDocumentaion = needsDocumentaion;
        this.cost = cost;
    }

    public TicketType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getValidFor() {
        return validFor;
    }

    public void setValidFor(Integer validFor) {
        this.validFor = validFor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentaionName() {
        return documentaionName;
    }

    public void setDocumentaionName(String documentationName) {
        this.documentaionName = documentationName;
    }

    public Boolean getNeedsDocumentaion() {
        return needsDocumentaion;
    }

    public void setNeedsDocumentaion(Boolean needsDocumentation) {
        this.needsDocumentaion = needsDocumentation;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
