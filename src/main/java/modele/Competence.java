package modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Competence {

   @Id
    private String intituleC;
    private String descriptionC;
    @ManyToMany
    private Collection<Projet> requisePour;

    // Constructeurs

    public Competence() {
        this.requisePour=new ArrayList<>();
    }

    public Competence(String intituleC, String descriptionC) {
        this();
        this.intituleC = intituleC;
        this.descriptionC = descriptionC;
    }

    // Getters et setters

    public String getIntituleC() {
        return intituleC;
    }

    public void setIntituleC(String intituleC) {
        this.intituleC = intituleC;
    }

    public String getDescriptionC() {
        return descriptionC;
    }

    public void setDescriptionC(String descriptionC) {
        this.descriptionC = descriptionC;
    }

    public Collection<Projet> getRequisePour() {
        return requisePour;
    }

    public void setRequisePour(Collection<Projet> requisePour) {
        this.requisePour = requisePour;
    }
}
