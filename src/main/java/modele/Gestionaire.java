package modele;

import java.util.Collection;

public class Gestionaire extends Utilisateur {
    public Gestionaire(String username, String password, String user_profil_id) {
        super(username, password, user_profil_id);
    }

    public Collection<Utilisateur> agent_responsable;

    public Collection<Utilisateur> getAgent_responsable() {
        return agent_responsable;
    }

    public void setAgent_responsable(Collection<Utilisateur> agent_responsable) {
        this.agent_responsable = agent_responsable;
    }
}
