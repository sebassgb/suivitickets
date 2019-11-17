package modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Projet {
    @Id
    private String proj_id ;
    private String proj_contribution;
    private String proj_desc;
    private Utilisateur proj_responsable;
//    @ManyToOne
//    private Utilisateur dirigePar;
    @ManyToMany(mappedBy = "participe")
    private Collection<Utilisateur> Utilisateurs;
    private Collection<Application> Applications;


    // Constructeurs

    public Projet(Utilisateur proj_responsable, Collection<Application> Application) {
        this.proj_responsable = proj_responsable;
        this.Applications = Application;
    }


    // Getters et setters


    public String getProj_id() {
        return proj_id;
    }

    public void setProj_id(String proj_id) {
        this.proj_id = proj_id;
    }

    public String getProj_contribution() {
        return proj_contribution;
    }

    public void setProj_contribution(String proj_contribution) {
        this.proj_contribution = proj_contribution;
    }

    public String getProj_desc() {
        return proj_desc;
    }

    public void setProj_desc(String proj_desc) {
        this.proj_desc = proj_desc;
    }

    public Utilisateur getProj_responsable() {
        return proj_responsable;
    }

    public void setProj_responsable(Utilisateur proj_responsable) {
        this.proj_responsable = proj_responsable;
    }

    public Collection<Utilisateur> getUtilisateurs() {
        return Utilisateurs;
    }

    public void setUtilisateurs(Collection<Utilisateur> utilisateurs) {
        Utilisateurs = utilisateurs;
    }

    public Collection<Application> getApplications() {
        return Applications;
    }

    public void setApplications(Collection<Application> applications) {
        Applications = applications;
    }
}
