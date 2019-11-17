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

    public Collection<Projet> filter(String motif){
        Collection<Projet> res = new ArrayList<>();
        for(Projet p: facade.getProjets()){
            if(p.getProj_id().contains(motif)){
                res.add(p);
            }
        }
        return res;
    }
}
