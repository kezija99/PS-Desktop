package ps.frontend.desktop.models;

public class ScanInterraction {
    
    private ScanInterractionKey id;

    public ScanInterraction(ScanInterractionKey id) {
        this.id = id;
    }

    public ScanInterraction() {
    }

    public ScanInterractionKey getId() {
        return id;
    }

    public void setId(ScanInterractionKey id) {
        this.id = id;
    }

}
