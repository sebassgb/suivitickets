package services;

import modele.Application;
import modele.Projet;
import modele.Ticket;
import modele.Utilisateur;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class Facade {
    @PersistenceContext
    EntityManager em;
    private ArrayList<Projet> BigProjets;
    private List<Utilisateur> Utilisateurs;
    private List<Projet> projets;
    private ArrayList<Ticket> Tickets;
    private ArrayList<Application> Applications;

    public Facade() {
    }


    @PostConstruct
    public void init() {
        Utilisateurs=new ArrayList<>();
        Utilisateur m1=new Utilisateur("toto",
                "toto","gestionaire");
        Utilisateurs.add(m1);
        Utilisateur m2=new Utilisateur("tata",
                "tata","agent");
        Utilisateurs.add(m2);
        Utilisateur m3=new Utilisateur("tete",
                "tete","client");
        Utilisateurs.add(m3);
        Utilisateur m4=new Utilisateur("tutu",
                "tutu","admin");
        Utilisateurs.add(m4);

        //=======================
        Tickets = new ArrayList<Ticket>();
        Ticket t1 = new Ticket(1,"voiture","13-03-2019","abc",0);
        Tickets.add(t1);
        Ticket t2 = new Ticket(2,"plane","13-03-2019","abc",0);
        Tickets.add(t2);

        //========================

        Applications = new ArrayList<Application>();

        Application a1 = new Application(Tickets);

        Applications.add(a1);

        //=========================

        BigProjets=new ArrayList<Projet>();
        Projet p1 = new Projet("toto", Applications);
        projets.add(p1);


//        Projet p1=new Projet("composants","voyage dans la 4eme dimension");
//        Projet p2=new Projet("week end","coming soon");
//        Projet p3=new Projet("lundi","déjà?");

        projets.add(p1);
//        projets.add(p2);
//        projets.add(p3);

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

//    public MembreDetailDTO details(String l){
//        MembreDetailDTO mdto = new MembreDetailDTO();
//        mdto.setNom(findMembre(l).getSurnom());
//        mdto.setPart(findParticipe(l));
//        mdto.setResp(findResponsable(l));
//        return mdto;
//    }


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


//    public void ajoutParticipe(String l, String ip) {
//        Membre m=findMembre(l);
//        Projet p=findProjet(ip);
//        if (!m.getParticipe().contains(p)) {
//            m.getParticipe().add(p);
//        }
//    }

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
//
//    public Collection<Projet> filtrer (String motif){
//        Query q = em.createQuery("From Projet p where p.intituleP like :pattern ");
//        q.setParameter("pattern", "%" + motif + "%");
//        return q.getResultList();
//    }
    public void newTicket(){

    }
//@Transactional
//    public void changerSurnom(String ls, String nouveau) {
//        //Membre m = em.find(Membre.class, ls);
//        //m.setSurnom(nouveau);
//
//        Query q = em.createQuery(" update Membre m set m.surnom =:s where m.login =:l");
//        q.setParameter("s",nouveau);
//        q.setParameter("l", ls);
//        q.executeUpdate();
//    }
}
