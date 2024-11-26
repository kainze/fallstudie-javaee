import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {

    @Inject
    private UsersDAO usersDAO;

    private Users loggedInUser;

    // Text fields for username and password
    private String username;
    private String password;

    private String failureMessage = "";

    // Salt for password hashing (should be stored securely)
    private static final String SALT = "vXsia8c04PhBtnG3isvjlemj7Bm6rAhBR8JRkf2z";

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

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public Users getLoggedInUser() {
        System.out.println("LoggedIn User: " + loggedInUser);
        System.out.println("LoggedIn Username: " + username);
        return loggedInUser;
    }

    // Check if the user is logged in; redirect if not
    public void checkLogin() {
        if (loggedInUser == null) {
            failureMessage = "Bitte loggen Sie sich ein.";
            FacesContext fc = FacesContext.getCurrentInstance();
            NavigationHandler nh = fc.getApplication().getNavigationHandler();
            nh.handleNavigation(fc, null, "login.xhtml?faces-redirect=true");
        }
    }

    // Log the user out and redirect to the login page
    public String logout() {
        loggedInUser = null; // Clear the logged-in user
        username = null;
        password = null;
        System.out.println("User Logged Out");
        return "index.xhtml?faces-redirect=true"; // Redirect to the login page
    }
    
    public String login() {
        System.out.println("Login method called with username: " + username);
        try {
            Users user = usersDAO.findUserByCredentials(username, hashPassword(username, password, SALT));
            if (user != null) {
                loggedInUser = user; // Set the logged-in user
                failureMessage = "";
                System.out.println("Login successful: " + loggedInUser.getUsername());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Willkommen, " + username + "!"));
                return "index.xhtml?faces-redirect=true";// Stay on the current page and update the UI
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Benutzername oder Passwort falsch."));
                return ""; // Stay on the current page
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Ein Fehler ist aufgetreten. Bitte versuchen Sie es erneut."));
            System.err.println("Exception occurred during login: " + e.getMessage());
            return ""; // Stay on the current page
        }
    }
    
    
    // Helper method to hash passwords
    private static String hashPassword(String username, String password, String salt) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest((username + password + salt).getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return java.util.Base64.getEncoder().encodeToString(hashedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Hashing des Passworts", e);
        }
    }
}
