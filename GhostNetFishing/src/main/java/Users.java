import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Entity
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for integers
    private int userId;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private boolean rescuer;

    private boolean userDisabled;

    @ManyToOne
    private Users insertBy;

    private Date insertDate;

    @ManyToOne
    private Users deleteBy;

    private Date deleteDate;


    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isRescuer() {
        return rescuer;
    }

    public void setRescuer(boolean rescuer) {
        this.rescuer = rescuer;
    }

    public boolean isUserDisabled() {
        return userDisabled;
    }

    public void setUserDisabled(boolean userDisabled) {
        this.userDisabled = userDisabled;
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


    public Users() {
    }
}
