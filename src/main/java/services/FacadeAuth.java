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
        utils.add(new Utilisateur("u1","password","gestionaire"));
        utils.add(new Utilisateur("u2","password","agent"));
        utils.add(new Utilisateur("u3","password","client"));
        utils.add(new Utilisateur("u4","password","admin"));


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
