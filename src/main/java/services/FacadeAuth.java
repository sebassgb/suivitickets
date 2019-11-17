package services;


import modele.Utilisateur;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;


@Service
public class FacadeAuth {
    private Collection<Utilisateur> utils;

    @PostConstruct
    public void peupler() {
        utils=new ArrayList<>();
        utils.add(new Utilisateur("u1","password","profil1"));
        utils.add(new Utilisateur("u2","password","profil2"));
    }

    public boolean exists(String id) {
        for (Utilisateur u:utils) {
            if (u.getUser_profil_id().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRole(String id, String role) {
        for (Utilisateur u:utils) {
            if (u.getUser_profil_id().equals(id)) {
                if (u.getUser_profil_id().equals(role)) {
                    return true;
                }
            }
        }
        return false;
    }



}
