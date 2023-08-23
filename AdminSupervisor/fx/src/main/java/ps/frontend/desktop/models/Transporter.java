package ps.frontend.desktop.models;

public class Transporter {
    
    private String name;
    private Integer id;

    public Transporter(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
    
    public Transporter(String name) {
        this.name = name;
    }
    public Transporter() {
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
}
