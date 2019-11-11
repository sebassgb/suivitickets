package modele;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Entity
public class Membre {
    @Id
    private String login;
    private String motdepasse;
    private String surnom;//chargement depuis le depart
    @OneToMany(mappedBy = "dirigePar")
    private Collection<Projet> responsable;
    @ManyToMany
    private Collection<Projet> participe;
    @OneToMany(mappedBy = "membre")
    private Collection<CompetenceMembre> competences;


    // Constructeurs

    public Membre() {
        responsable=new ArrayList<>();
        participe=new ArrayList<>();
    }

    public Membre(String login, String motdepasse, String surnom) {
        this();
        this.login = login;
        this.motdepasse = motdepasse;
        this.surnom = surnom;
    }

    // Getters / setters

    public String getLogin() {
        return login;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public Collection<Projet> getResponsable() {
        return responsable;
    }

    public void setResponsable(Collection<Projet> responsable) {
        this.responsable = responsable;
    }

    public Collection<Projet> getParticipe() {
        return participe;
    }

    public void setParticipe(Collection<Projet> participe) {
        this.participe = participe;
    }
}
