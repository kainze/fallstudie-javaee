import java.io.Serializable;
import java.util.List;
import java.util.*;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.ApplicationScoped;

@Named
@ViewScoped
public class GhostNetStateController implements Serializable {

    @Inject
    GhostNetStateDAO ghostNetStateDAO;
    private GhostNetState ghostNetState = new GhostNetState();

    public GhostNetStateController() {
        // Default no-arg constructor
    }

    public List<GhostNetState> getGhostNetStates() {
        System.err.println("get all getGhostNetStates");
        return ghostNetStateDAO.findAll();
    }

    public String getName() {
        return " test 1";
    }

    public void saveGhostNetState() {
        System.out.println("Saving GhostNetState: " + ghostNetState.getName());
        // Persist the state
        ghostNetStateDAO.save(ghostNetState);
        // Reset the field
        ghostNetState = new GhostNetState();
    }
}
