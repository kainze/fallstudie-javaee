import java.io.Console;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

import javax.swing.text.html.parser.Entity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

@Named
@ApplicationScoped
public class test implements Serializable {

    @Inject
    GhostNetStateDAO ghostNetStateDAO;

    static final List<GhostNetState> baseSortiment = Arrays.asList(new GhostNetState[]{
            new GhostNetState("Pantoffeln \"Rudolph\"",
                    "Wunderschöne Filzpantoffeln, in beige Farbe mit einem braunen und schwarzen Kringel. Sehr angenehm für kalte Wintertage."),
            new GhostNetState("Handtasche \"Cosmopolita\"",
                    "Modische Filz-Handtasche mit praktischer Cocktailglas-Halterung. Irgendwie kommen wir nie aus dem Haus ohne solchen nützlichen Accessoire."),
            new GhostNetState("Filz-Hase \"Moe\"", "Ein putziger Hase aus Filz zur Dekoration. Er lässt sich gern am Rand seines Büros stellen, um Mut zu geben.")
    });

    EntityManager entityManager;
    
    public test() {
        entityManager = Persistence.createEntityManagerFactory("ghost_net_fishing").createEntityManager();

        long count = 0;

        count = entityManager.createQuery("SELECT Count(*) FROM GhostNetState").getFirstResult();

        System.err.print("Anzahl GhostNetState: ");
        System.err.println(count);

        if(count == 0 ) {
            for(GhostNetState gns : baseSortiment) {
                entityManager.persist(gns);
            }
        }
    }

    public List<GhostNetState> getNetStates() {
        return (List<GhostNetState>) entityManager.createQuery("SELECT g FROM GhostNetState g").getResultList();
    }

    static String hashPassword(String name, String pass, String salt) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = digester.digest((name + pass + salt)
                    .getBytes(StandardCharsets.UTF_8));
            return new String(Base64.getEncoder().encode(hashBytes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
