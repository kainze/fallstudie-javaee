import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.*;
import jakarta.persistence.Persistence;

@Named
@ApplicationScoped
public class GhostNetStateDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public GhostNetStateDAO() {
        this.entityManager = Persistence.createEntityManagerFactory("ghost_net_fishing").createEntityManager();
    }

    @Transactional
    public GhostNetState save(GhostNetState state) {
        entityManager.persist(state);
        return state;
    }

    static final List<GhostNetState> defaultSortiment = Arrays.asList(new GhostNetState[]{
        new GhostNetState("Pantoffeln1 \"Rudolph\"",
                "Wunderschöne Filzpantoffeln, in beige Farbe mit einem braunen und schwarzen Kringel. Sehr angenehm für kalte Wintertage."),
        new GhostNetState("Handtasche \"Cosmopolita\"",
                "Modische Filz-Handtasche mit praktischer Cocktailglas-Halterung. Irgendwie kommen wir nie aus dem Haus ohne solchen nützlichen Accessoire."),
        new GhostNetState("Filz-Hase \"Moe\"", "Ein putziger Hase aus Filz zur Dekoration. Er lässt sich gern am Rand seines Büros stellen, um Mut zu geben.")
    });

    public GhostNetState findById(int id) {
        return entityManager.find(GhostNetState.class, id);
    }

    public List<GhostNetState> findAll() {
        return entityManager.createQuery("SELECT g FROM GhostNetState g", GhostNetState.class).getResultList();
    }
}
