package modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Random;

@Entity
public class Application {

    @Id
    private int app_id;//ID pour identifier l'application
    private String app_nom;//cette variable pour savoir le nom
    private String app_proj_id;//cette variable pour savoir le nom
    private Utilisateur app_responsable;
    private Collection<Ticket> tickets;
    Random rand = new Random();

    public Application(String app_nom, String app_proj_id, Utilisateur app_responsable,  Collection<Ticket> tickets) {
        this.app_id = rand.nextInt(100);
        this.tickets = tickets;
        this.app_proj_id= app_proj_id;
        this.app_responsable = app_responsable;
        this.app_nom = app_nom;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public String getApp_nom() {
        return app_nom;
    }

    public void setApp_nom(String app_nom) {
        this.app_nom = app_nom;
    }

    public String getApp_proj_id() {
        return app_proj_id;
    }

    public void setApp_proj_id(String app_proj_id) {
        this.app_proj_id = app_proj_id;
    }

    public Utilisateur getApp_responsable() {
        return app_responsable;
    }

    public void setApp_responsable(Utilisateur app_responsable) {
        this.app_responsable = app_responsable;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }
}
