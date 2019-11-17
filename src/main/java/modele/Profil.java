package modele;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Profil {
    @Id
    private String profile_id;//Cet profile_id va profile_identifier cette profile
    private String profile_aut;//cette variable va le donner l'autorisation pour accèedes ou non à les tickets
    private String profile_desc;//Ici on va décrire le but du profil


    public Profil(String profile_id, String profile_aut) {
        this.profile_id = profile_id;
        this.profile_aut = profile_aut;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public String getProfile_aut() {
        return profile_aut;
    }

    public void setProfile_aut(String profile_aut) {
        this.profile_aut = profile_aut;
    }

    public String getProfileAut() {
        return profile_aut;
    }

    public void setProfileAut(String profile_aut) {
        this.profile_aut = profile_aut;
    }

    public String getProfilDesc() {
        return profile_desc;
    }

    public void setProfileDesc(String profile_desc) {
        this.profile_desc = profile_desc;
    }
}
