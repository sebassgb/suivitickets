package modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

public class Utilisateur {

    private String username;// unique
    private String password;
    private String user_profil_id;// Profil de l'utilisateur que va le donner des autorisations

    private Collection<Utilisateur> responsable;

    private Collection<Projet> contribution;

    // Constructeurs
    public Utilisateur(String username, String password, String user_profil_id) {
        this.username = username;
        this.password = password;
        this.user_profil_id = user_profil_id;
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


}// Fin class
