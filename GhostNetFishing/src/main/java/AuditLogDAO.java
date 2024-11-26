import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Named
@ApplicationScoped
public class AuditLogDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(AuditLog auditLog) {
        entityManager.persist(auditLog);
    }

    public List<AuditLog> findAll() {
        return entityManager.createQuery("SELECT a FROM AuditLog a", AuditLog.class).getResultList();
    }
}
