package modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Random;

public class Application {

    private int app_id;// ID pour identifier l'application
    private String app_nom;// cette variable pour savoir le nom
    private String app_proj_id;// cette variable pour savoir le nom
    private Utilisateur app_responsable;
    private Collection<Ticket> tickets;
    Random rand = new Random();

    public Application(String app_nom, String app_proj_id, Utilisateur app_responsable, Collection<Ticket> tickets) {
        this.app_id = rand.nextInt(100);
        this.tickets = tickets;
        this.app_proj_id = app_proj_id;
        this.app_responsable = app_responsable;
        this.app_nom = app_nom;
    }

    public String getApp_nom() {
        return app_nom;
    }

}
