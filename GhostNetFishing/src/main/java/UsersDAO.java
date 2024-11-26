import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

@Named
@ApplicationScoped
public class UsersDAO {

    private EntityManager entityManager;

    public UsersDAO() {
        entityManager = Persistence.createEntityManagerFactory("ghost_net_fishing").createEntityManager();
    }

    public List<Users> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM Users u", Users.class).getResultList();
    }

    public Users getUserById(int id) {
        return entityManager.find(Users.class, id);
    }

    public Users findUserByCredentials(String username, String hashedPassword) {
        try {
            return entityManager.createQuery("SELECT u FROM Users u WHERE u.username = :username AND u.password = :password", Users.class)
                    .setParameter("username", username)
                    .setParameter("password", hashedPassword)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null; // Return null if no matching user is found
        }
    }
    

    public void saveUser(Users user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
    }

    public void updateUser(Users user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(user);
        transaction.commit();
    }

    public void deleteUser(Users user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        transaction.commit();
    }
}
