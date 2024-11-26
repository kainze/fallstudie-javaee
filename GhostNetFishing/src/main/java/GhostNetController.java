import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;
import jakarta.persistence.EntityTransaction;

@Named
@ViewScoped
public class GhostNetController implements Serializable {

    @Inject
    private GhostNetDAO ghostNetDAO;

    private LoginController loginController;

    private GhostNet ghostNet = new GhostNet();

    public GhostNetController() {
        // Default no-arg constructor
        System.out.println("GhostNetController created");
    }

    public LoginController getLoginController() {
        if (loginController == null) {
            loginController = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(
                    FacesContext.getCurrentInstance(), "#{loginController}", LoginController.class);
        }
        return loginController;
    }

    public List<GhostNet> getGhostNets() {
        System.err.println("get all getGhostNets");
        return ghostNetDAO.getAllGhostNets();
    }

    // Fetch ghost nets created by the logged-in user
    public List<GhostNet> getUserCreatedGhostNets() {
        return ghostNetDAO.findGhostNetsByCreator(getLoginController().getLoggedInUser().getUserId());
    }

    // Fetch ghost nets with state "Gemeldet"
    public List<GhostNet> getAvailableGhostNets() {
        return ghostNetDAO.findGhostNetsByState("Gemeldet");
    }

    // Assign a ghost net to the logged-in user
    public void assignToSelf(GhostNet net) {
        net.setRescuer(getLoginController().getLoggedInUser());
        net.setGhostnetState(ghostNetDAO.findStateByName("Bergung bevorstehend"));
        ghostNetDAO.updateGhostNet(net);
    }

    // Fetch ghost nets assigned to the logged-in user with state "Bergung
    // bevorstehend"
    public List<GhostNet> getAssignedGhostNets() {
        return ghostNetDAO.findGhostNetsByRescuerAndState(getLoginController().getLoggedInUser().getUserId(),
                "Bergung bevorstehend");
    }

    // Mark a ghost net as recovered
    public String markAsRecovered(GhostNet net) {
        net.setGhostnetState(ghostNetDAO.findStateByName("Geborgen"));
        ghostNetDAO.updateGhostNet(net);
        return "";
    }

    // Mark a ghost net as lost
    public String markAsLost(GhostNet net) {
        net.setGhostnetState(ghostNetDAO.findStateByName("Verschollen"));
        ghostNetDAO.updateGhostNet(net);
        return "";
    }

    // Fetch ghost nets with state "Bergung bevorstehend" not assigned to the
    // logged-in user
    public List<GhostNet> getOtherAssignedGhostNets() {
        return ghostNetDAO.findGhostNetsNotAssignedToRescuer(getLoginController().getLoggedInUser().getUserId(),
                "Bergung bevorstehend");
    }

    // Request assignment for a ghost net
    public String requestAssignment(GhostNet net) {
        // Logic to handle requesting assignment (e.g., notification to the current
        // rescuer)
        // Placeholder: Example of changing state to "Requested"
        net.setGhostnetState(ghostNetDAO.findStateByName("Requested Assignment"));
        ghostNetDAO.updateGhostNet(net);
        return "";
    }

    public void createGhostNet() {
        ghostNet.setInsertDate(new Date());
        ghostNet.setNotifier(getLoginController().getLoggedInUser());
        ghostNet.setGhostnetState(ghostNetDAO.findStateByName("Gemeldet"));
        ghostNetDAO.saveGhostNet(ghostNet);
        ghostNet = new GhostNet(); // Reset the form
    }

    public void updateGhostNet() {
        ghostNetDAO.updateGhostNet(ghostNet);
    }

    public GhostNet getGhostNet() {
        return ghostNet;
    }

    public void setGhostNet(GhostNet ghostNet) {
        this.ghostNet = ghostNet;
    }
}
