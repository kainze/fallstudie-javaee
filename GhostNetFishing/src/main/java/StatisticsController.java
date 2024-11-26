import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class StatisticsController {

    @Inject
    private GhostNetDAO ghostNetDAO;

    // Properties to hold the statistics
    private long totalGhostNets;
    private long recoveredGhostNets;
    private long lostGhostNets;

    // Method to calculate statistics
    public void calculateStatistics() {
        totalGhostNets = ghostNetDAO.countAllGhostNets();
        recoveredGhostNets = ghostNetDAO.countRecoveredGhostNets();
        lostGhostNets = ghostNetDAO.countLostGhostNets();
    }

    // Getters for the statistics
    public long getTotalGhostNets() {
        calculateStatistics(); // Ensure the statistics are up-to-date
        return totalGhostNets;
    }

    public long getRecoveredGhostNets() {
        calculateStatistics();
        return recoveredGhostNets;
    }

    public long getLostGhostNets() {
        calculateStatistics();
        return lostGhostNets;
    }
}
