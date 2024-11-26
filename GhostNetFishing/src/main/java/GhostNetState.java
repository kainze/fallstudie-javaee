import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Entity
public class GhostNetState implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for integers
    private int ghostnetStateId;

    private String name = "testName";

    private String description;

    @ManyToOne
    private Users insertBy;

    private Date insertDate;

    @ManyToOne
    private Users deleteBy;

    private Date deleteDate;

    // Getters and Setters
    public int getGhostnetStateId() {
        return ghostnetStateId;
    }

    public void setGhostnetStateId(int ghostnetStateId) {
        this.ghostnetStateId = ghostnetStateId;
    }

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

    public Users getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(Users insertBy) {
        this.insertBy = insertBy;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Users getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(Users deleteBy) {
        this.deleteBy = deleteBy;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public GhostNetState() {
    }

    public GhostNetState(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
