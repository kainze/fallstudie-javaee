import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public class GhostNetState implements Serializable {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    private UUID ghostnetStateId;

    private String name;

    private String description;
    
    public GhostNetState() {
    }

    public GhostNetState(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    // public UUID getGhostnetStateId() {
    //     return ghostnetStateId;
    // }

    // public void setGhostnetStateId(UUID ghostnetStateId) {
    //     this.ghostnetStateId = ghostnetStateId;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
