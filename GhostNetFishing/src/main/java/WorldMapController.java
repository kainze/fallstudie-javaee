import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class WorldMapController implements Serializable {

    private List<String> selectedStates;
    private List<String> availableStates;
    private List<GhostNet> filteredGhostNets;

    @Inject
    private GhostNetDAO ghostNetDAO;

    @PostConstruct
    public void init() {
        // Load available states (e.g., from the database)
        availableStates = ghostNetDAO.findAllStates();
        selectedStates = new ArrayList<>(availableStates); // Default: all states selected
        updateFilteredGhostNets();
    }

    public List<String> getSelectedStates() {
        return selectedStates;
    }

    public void setSelectedStates(List<String> selectedStates) {
        this.selectedStates = selectedStates;
    }

    public List<String> getAvailableStates() {
        return availableStates;
    }

    public List<GhostNet> getFilteredGhostNets() {
        return filteredGhostNets;
    }

    public String getFilteredGhostNetsAsJSON() {
        StringBuilder json = new StringBuilder("[");
        for (GhostNet net : filteredGhostNets) {
            json.append("{")
                .append("\"gpsLatitude\":").append(net.getGpsLatitude()).append(",")
                .append("\"gpsLongitude\":").append(net.getGpsLongitude()).append(",")
                //.append("\"notifier\":{\"username\":\"").append(net.getNotifier().getUsername()).append("\"},")
                .append("\"ghostnetState\":{\"name\":\"").append(net.getGhostnetState().getName()).append("\"},")
                .append("\"size\":").append(net.getSize())
                .append("},");
        }
        if (json.length() > 1) json.deleteCharAt(json.length() - 1); // Remove trailing comma
        json.append("]");
        System.out.println(json.toString());
        return json.toString();
    }

    public void updateMap() {
        updateFilteredGhostNets();
    }

    private void updateFilteredGhostNets() {
        filteredGhostNets = ghostNetDAO.findGhostNetsByStates(selectedStates);
    }
}
