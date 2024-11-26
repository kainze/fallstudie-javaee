import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

@Named
@ViewScoped
public class GhostNetController implements Serializable {

    @Inject
    private GhostNetDAO ghostNetDAO;

    private GhostNet ghostNet = new GhostNet();

    public GhostNetController() {
        // Default no-arg constructor
    }

    public List<GhostNet> getGhostNets() {
        System.err.println("get all getGhostNets");
        return ghostNetDAO.getAllGhostNets();
    }

    public List<GhostNet> getAvailableGhostNets() {
        return ghostNetDAO.getAllGhostNets().stream()
                .filter(net -> !"Geborgen".equalsIgnoreCase(net.getGhostnetState().getName()))
                .collect(Collectors.toList());
    }

    public void saveGhostNet() {
        ghostNet.setInsertDate(new Date(0));
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
