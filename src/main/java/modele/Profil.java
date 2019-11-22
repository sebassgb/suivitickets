package modele;

import javax.persistence.Entity;
import javax.persistence.Id;

public class Profil {

    private String profile_id;//Cet profile_id va profile_identifier cette profile
    private String profile_aut;//cette variable va le donner l'autorisation pour accèedes ou non à les tickets
    private String profile_desc;//Ici on va décrire le but du profil


    public Profil(String profile_id, String profile_aut) {
        this.profile_id = profile_id;
        this.profile_aut = profile_aut;
    }

}
