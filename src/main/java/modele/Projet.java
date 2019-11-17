package modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Projet {
    @Id
    private String projet_id;
    private String projet_desc;
    @ManyToOne
    private Utilisateur dirigePar;
    @ManyToMany(mappedBy = "participe")
    private Collection<Utilisateur> proj_contribution;


    // Constructeurs
    public Projet() {
        this.proj_contribution=new ArrayList<>();
    }

    public Projet(String intituleP, String descriptionP) {
        this();
        this.projet_id = intituleP;
        this.projet_desc = descriptionP;
    }


    // Getters et setters

    public String getIntituleP() {
        return projet_id;
    }

    public void setIntituleP(String intituleP) {
        this.projet_id = intituleP;
    }

    public String getDescriptionP() {
        return projet_desc;
    }

    public void setDescriptionP(String descriptionP) {
        this.projet_desc = descriptionP;
    }

    public Utilisateur getDirigePar() {
        return dirigePar;
    }

    public void setDirigePar(Utilisateur dirigePar) {
        this.dirigePar = dirigePar;
    }

    public Collection<Utilisateur> getContributionDe() {
        return proj_contribution;
    }

    public void setContributionDe(Collection<Utilisateur> contributionDe) {
        this.proj_contribution = contributionDe;
    }

}
