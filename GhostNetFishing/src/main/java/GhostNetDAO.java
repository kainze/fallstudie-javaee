import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

@Named
@ApplicationScoped
public class GhostNetDAO {

    private EntityManager entityManager;

    public GhostNetDAO() {
        entityManager = Persistence.createEntityManagerFactory("ghost_net_fishing").createEntityManager();
    }

    public List<GhostNet> getAllGhostNets() {
        return entityManager.createQuery("SELECT g FROM GhostNet g", GhostNet.class).getResultList();
    }

    public GhostNet getGhostNetById(int id) {
        return entityManager.find(GhostNet.class, id);
    }

    // Find ghost nets created by a specific user
    public List<GhostNet> findGhostNetsByCreator(int userId) {
        return entityManager.createQuery("SELECT g FROM GhostNet g WHERE g.notifier.userId = :userId", GhostNet.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    // Find ghost nets by state
    public List<GhostNet> findGhostNetsByState(String stateName) {
        return entityManager.createQuery("SELECT g FROM GhostNet g WHERE g.ghostnetState.name = :stateName", GhostNet.class)
                .setParameter("stateName", stateName)
                .getResultList();
    }

    // Find ghost nets assigned to a specific rescuer and state
    public List<GhostNet> findGhostNetsByRescuerAndState(int rescuerId, String stateName) {
        return entityManager.createQuery("SELECT g FROM GhostNet g WHERE g.rescuer.userId = :rescuerId AND g.ghostnetState.name = :stateName", GhostNet.class)
                .setParameter("rescuerId", rescuerId)
                .setParameter("stateName", stateName)
                .getResultList();
    }

    // Find ghost nets not assigned to a specific rescuer and state
    public List<GhostNet> findGhostNetsNotAssignedToRescuer(int rescuerId, String stateName) {
        return entityManager.createQuery("SELECT g FROM GhostNet g WHERE g.rescuer.userId != :rescuerId AND g.ghostnetState.name = :stateName", GhostNet.class)
                .setParameter("rescuerId", rescuerId)
                .setParameter("stateName", stateName)
                .getResultList();
    }

    // Find a state by its name
    public GhostNetState findStateByName(String stateName) {
        return entityManager.createQuery("SELECT s FROM GhostNetState s WHERE s.name = :stateName", GhostNetState.class)
                .setParameter("stateName", stateName)
                .getSingleResult();
    }

    public List<GhostNet> findGhostNetsByStates(List<String> states) {
        return entityManager.createQuery(
            "SELECT g FROM GhostNet g WHERE g.ghostnetState.name IN :states", GhostNet.class)
            .setParameter("states", states)
            .getResultList();
    }
    
    public List<String> findAllStates() {
        return entityManager.createQuery("SELECT DISTINCT s.name FROM GhostNetState s", String.class)
            .getResultList();
    }

    public void saveGhostNet(GhostNet ghostNet) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(ghostNet);
        transaction.commit();
    }

    public void updateGhostNet(GhostNet ghostNet) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(ghostNet);
        transaction.commit();
    }

    // Count all ghost nets
    public long countAllGhostNets() {
        return entityManager.createQuery("SELECT COUNT(g) FROM GhostNet g", Long.class)
                .getSingleResult();
    }

    // Count recovered ghost nets
    public long countRecoveredGhostNets() {
        return entityManager.createQuery("SELECT COUNT(g) FROM GhostNet g WHERE g.ghostnetState.name = 'Geborgen'", Long.class)
                .getSingleResult();
    }

    // Count lost ghost nets
    public long countLostGhostNets() {
        return entityManager.createQuery("SELECT COUNT(g) FROM GhostNet g WHERE g.ghostnetState.name = 'Verschollen'", Long.class)
                .getSingleResult();
    }
}
