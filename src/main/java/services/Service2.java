package services;

import modele.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class Service2 {
    @Autowired
    private Facade facade;

    public Collection<Projet> filtrer (String motif) {
        Collection<Projet> res=new ArrayList<>();

        for (Projet p:facade.getProjets()) {
            if (p.getIntituleP().contains(motif)) {
                res.add(p);
            }
        }

        return res;
    }

}
