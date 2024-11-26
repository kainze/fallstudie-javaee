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
