package services;

import dto.MembreDetailDTO;
import modele.Membre;
import modele.Projet;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class Facade {
    @PersistenceContext
    EntityManager em;

    private List<Membre> membres;
    private List<Projet> projets;

    public Facade() {
    }


    @PostConstruct
    public void init() {
        membres=new ArrayList<>();
        Membre m1=new Membre("toto",
                "toto","toto");
        membres.add(m1);
        Membre m2=new Membre("alain",
                "alex","alain");
        membres.add(m2);

        projets=new ArrayList<>();

        Projet p1=new Projet("composants","voyage dans la 4eme dimension");
        Projet p2=new Projet("week end","coming soon");
        Projet p3=new Projet("lundi","déjà?");

        projets.add(p1);
        projets.add(p2);
        projets.add(p3);

        // m1 responsabl de p1
        m1.getResponsable().add(p1);
        p1.setDirigePar(m1);

        // participe à p2
        m1.getParticipe().add(p2);
        p2.getContributionDe().add(m1);

    }


    public Membre findMembre(String l, String p) {
        Query q = em.createQuery("From Membre m" + " where m.login=:log and m.motdepasse=:mdp");
        q.setParameter("log",l);
        q.setParameter("mdp", p);//proteger des injections
        try {
            return (Membre) q.getSingleResult();
        } catch (NoResultException nre){
            return null;
        }
        /*
        for (Membre m:membres) {
            if ((m.getLogin().equals(l))
                    &&(m.getMotdepasse().equals(p))) {
                return  m;
            }
        }
        return null;*/
    }

    public Membre findMembre(String l) {
        return em.find(Membre.class,l);
        /*
        for (Membre m:membres) {
            if (m.getLogin().equals(l)) {
                return  m;
            }
        }
        return null;*/
    }



    public Collection<String> findResponsable(String l) {
        Membre m=findMembre(l);
        Collection<String> resp = new ArrayList<>();
        if (m!=null) {
            for (Projet p:m.getResponsable()){
                resp.add(p.getIntituleP()+ "(" + p.getDescriptionP()+ ")");
            }
        }
        return resp;
    }

    public Collection<String> findParticipe(String l) {
        Membre m=findMembre(l);
        Collection<String> part = new ArrayList<>();
        if (m!=null) {
            for (Projet p:m.getParticipe()){
                part.add(p.getIntituleP()+ "(" + p.getDescriptionP()+ ")");
            }
        }
        return part;
    }

    public MembreDetailDTO details(String l){
        MembreDetailDTO mdto = new MembreDetailDTO();
        mdto.setNom(findMembre(l).getSurnom());
        mdto.setPart(findParticipe(l));
        mdto.setResp(findResponsable(l));
        return mdto;
    }


    public Projet findProjet(String ip){
        return em.find(Projet.class,ip);
        /*
        for (Projet p:projets) {
            if (p.getIntituleP().equals(ip)) {
                return p;
            }
        }
        return null;*/
    }


    public void ajoutParticipe(String l, String ip) {
        Membre m=findMembre(l);
        Projet p=findProjet(ip);
        if (!m.getParticipe().contains(p)) {
            m.getParticipe().add(p);
        }
    }

    public List<Projet> getProjets() {
        return em.createQuery("From Projet p")
                .getResultList();
      //  return projets;
    }
    //
@Transactional
    public void nouveauProjet(String ls, String intitule, String description) {//modification dans le BD sans les droits
        Membre m = em.find(Membre.class,ls);
        Projet p = new Projet();
        p.setDirigePar(m);
        p.setIntituleP(intitule);
        p.setDescriptionP(description);

        em.persist(p);
    }

    public Collection<Projet> filtrer (String motif){
        Query q = em.createQuery("From Projet p where p.intituleP like :pattern ");
        q.setParameter("pattern", "%" + motif + "%");
        return q.getResultList();
    }

@Transactional
    public void changerSurnom(String ls, String nouveau) {
        //Membre m = em.find(Membre.class, ls);
        //m.setSurnom(nouveau);

        Query q = em.createQuery(" update Membre m set m.surnom =:s where m.login =:l");
        q.setParameter("s",nouveau);
        q.setParameter("l", ls);
        q.executeUpdate();
    }
}
