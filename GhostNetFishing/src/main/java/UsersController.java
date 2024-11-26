import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Date;

@Named
@ViewScoped
public class UsersController implements Serializable {

    @Inject
    private UsersDAO userDAO;

    private Users user = new Users();

    public UsersController() {
        // Default no-arg constructor
    }

    public List<Users> getAllUsers() {
        return userDAO.getAllUsers();
    }
    
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
