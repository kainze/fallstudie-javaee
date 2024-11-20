import java.io.Serializable;
import java.util.List;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.util.UUID;

@Named
@RequestScoped
public class GhostNetStateController implements Serializable {

    @Inject
    GhostNetStateDAO ghostNetStateDAO;

    public GhostNetStateController(GhostNetStateDAO ghostNetStateDAO) {
        this.ghostNetStateDAO = ghostNetStateDAO;
    }

    public List<GhostNetState> getGhostNetStates() {
        System.err.println("get all Artikel");
        return ghostNetStateDAO.findAll();
    }
}
