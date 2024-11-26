import jakarta.persistence.*;
import java.util.Date;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for integers
    private int logId;

    private String entityName;

    private String action;

    private String description;

    @ManyToOne
    private Users performedBy;

    private Date performedDate;

    @PrePersist
    protected void onCreate() {
        if (performedDate == null) {
            performedDate = new Date();
        }
    }

    // Getters and setters
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(Users performedBy) {
        this.performedBy = performedBy;
    }

    public Date getPerformedDate() {
        return performedDate;
    }

    public void setPerformedDate(Date performedDate) {
        this.performedDate = performedDate;
    }
}
