package modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Random;

public class Projet {

    private int proj_id;
    private String proj_contribution;
    private String proj_desc;
    private Utilisateur proj_responsable;

    private Collection<Utilisateur> Utilisateurs;
    private Collection<Application> Applications;
    Random rand = new Random();

    // Constructeurs

    public Projet(Utilisateur proj_responsable, Collection<Application> Application) {
        this.proj_id = rand.nextInt(100);//on va créer l'id du proj de façon aléatoire
        this.proj_responsable = proj_responsable;//qui est le résponsable du projet
        this.Applications = Application;//chaque projet contient une collection des applications
    }

    public Utilisateur getProj_responsable() {
        return proj_responsable;
    }
// Getters et setters

    public void setProj_desc(String proj_desc) {
        this.proj_desc = proj_desc;
    }

    public void setProj_responsable(Utilisateur proj_responsable) {
        this.proj_responsable = proj_responsable;
    }

    public int getProj_id() {
        return proj_id;
    }

    public void setProj_id(int proj_id) {
        this.proj_id = proj_id;
    }
}
