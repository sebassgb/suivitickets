package modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Utilisateur {


    @Id
    private String username;//unique
    private String password;
    private String user_profil_id;//Profil de l'utilisateur  que va le donner les autorisations

//    @OneToMany(mappedBy = "proj_responsable")
    private Collection<Utilisateur> responsable;

//    @ManyToMany
    private Collection<Projet> contribution;

    // Constructeurs



    public Utilisateur(String username, String password, String user_profil_id) {
        this.username=username;
        this.password=password;
        this.user_profil_id=user_profil_id;
    }
    // Getters / setters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_profil_id() {
        return user_profil_id;
    }

    public void setUser_profil_id(String user_profil_id) {
        this.user_profil_id = user_profil_id;
    }

    public Collection<Utilisateur> getResponsable() {
        return responsable;
    }

    public void setResponsable(Collection<Utilisateur> responsable) {
        this.responsable = responsable;
    }

    public Collection<Projet> getContribution() {
        return contribution;
    }

    public void setContribution(Collection<Projet> contribution) {
        this.contribution = contribution;
    }


}//Fin class

