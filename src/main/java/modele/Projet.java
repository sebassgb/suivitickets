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
    private String intituleP;
    private String descriptionP;
    @ManyToOne
    private Membre dirigePar;
    @ManyToMany(mappedBy = "participe")
    private Collection<Membre> contributionDe;
    @ManyToMany(mappedBy = "requisePour")
    private Collection<Competence> necessite;

    // Constructeurs
    public Projet() {
        this.contributionDe=new ArrayList<>();
        this.necessite=new ArrayList<>();
    }

    public Projet(String intituleP, String descriptionP) {
        this();
        this.intituleP = intituleP;
        this.descriptionP = descriptionP;
    }


    // Getters et setters

    public String getIntituleP() {
        return intituleP;
    }

    public void setIntituleP(String intituleP) {
        this.intituleP = intituleP;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    }

    public Membre getDirigePar() {
        return dirigePar;
    }

    public void setDirigePar(Membre dirigePar) {
        this.dirigePar = dirigePar;
    }

    public Collection<Membre> getContributionDe() {
        return contributionDe;
    }

    public void setContributionDe(Collection<Membre> contributionDe) {
        this.contributionDe = contributionDe;
    }

    public Collection<Competence> getNecessite() {
        return necessite;
    }

    public void setNecessite(Collection<Competence> necessite) {
        this.necessite = necessite;
    }
}
