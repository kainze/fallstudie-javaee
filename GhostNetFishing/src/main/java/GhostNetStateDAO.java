import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Named
@ApplicationScoped
public class GhostNetStateDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public GhostNetState save(GhostNetState state) {
        entityManager.persist(state);
        return state;
    }

    public GhostNetState findById(UUID id) {
        return entityManager.find(GhostNetState.class, id);
    }

    public List<GhostNetState> findAll() {
        return entityManager.createQuery("SELECT g FROM GhostNetState g", GhostNetState.class).getResultList();
    }
}
