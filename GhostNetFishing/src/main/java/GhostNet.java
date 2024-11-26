import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Entity
public class GhostNet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for integers
    private int ghostnetId;

    private double gpsLatitude;

    private double gpsLongitude;

    private int size;

    @ManyToOne
    private GhostNetState ghostnetState;

    @ManyToOne
    private Users notifier;

    @ManyToOne
    private Users rescuer;

    @ManyToOne
    private Users insertBy;

    private Date insertDate;

    @ManyToOne
    private Users deleteBy;

    private Date deleteDate;
    
    // Getters and Setters
    public int getGhostnetId() {
        return ghostnetId;
    }

    public void setGhostnetId(int ghostnetId) {
        this.ghostnetId = ghostnetId;
    }

    public double getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(double gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    public double getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(double gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public GhostNetState getGhostnetState() {
        return ghostnetState;
    }

    public void setGhostnetState(GhostNetState ghostnetState) {
        this.ghostnetState = ghostnetState;
    }

    public Users getNotifier() {
        return notifier;
    }

    public void setNotifier(Users notifier) {
        this.notifier = notifier;
    }

    public Users getRescuer() {
        return rescuer;
    }

    public void setRescuer(Users rescuer) {
        this.rescuer = rescuer;
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

    public GhostNet() {
    }
}
