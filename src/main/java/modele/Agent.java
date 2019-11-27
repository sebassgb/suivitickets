package modele;

import java.util.Collection;

public class Agent extends Utilisateur {
    public Agent(String username, String password, String user_profil_id) {
        super(username, password, user_profil_id);
    }

    private Collection<Ticket> responsable_ticket;
        //fonction pour obtenir qui a pris en charge le ticket
    public Collection<Ticket> getResponsable_ticket() {
        return responsable_ticket;
    }
    //fonction pour assigner le person qui prendrera le ticket
    public void setResponsable_ticket(Collection<Ticket> responsable_ticket) {
        this.responsable_ticket = responsable_ticket;
    }
}
