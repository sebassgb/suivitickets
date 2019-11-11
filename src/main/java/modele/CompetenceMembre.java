package modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CompetenceMembre {
    @Id @GeneratedValue
    private int id;
    @ManyToOne
    private Membre membre;
    @ManyToOne
    private Competence competence;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }
}
