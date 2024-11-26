import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Date;

@Named
@ApplicationScoped
public class AuditLogController {

    @Inject
    private AuditLogDAO auditLogDAO;

    private LoginController loginController; // To get the current user
    
    public LoginController getLoginController() {
      if (loginController == null) {
          loginController = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(
                  FacesContext.getCurrentInstance(), "#{loginController}", LoginController.class);
      }
      return loginController;
  }

    public void createLogEntry(String entityName, String action, String description) {
        AuditLog log = new AuditLog();
        log.setEntityName(entityName);
        log.setAction(action);
        log.setDescription(description);

        // Set the user performing the action
        if (getLoginController().getLoggedInUser() != null) {
            log.setPerformedBy(getLoginController().getLoggedInUser());
        }

        // Set the timestamp
        log.setPerformedDate(new Date());

        // Save the log entry
        auditLogDAO.save(log);
    }
}
