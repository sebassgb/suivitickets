package services;

import modele.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class Facade {
    private ArrayList<Projet> BigProjets;
    private List<Utilisateur> Utilisateurs;
    private List<Projet> projets;
    private ArrayList<Ticket> Tickets;
    private ArrayList<Application> Applications;
    public Facade(){
    }

    @PostConstruct
    public void init(){
        Utilisateurs=new ArrayList<>();
        Gestionaire m1=  new Gestionaire("toto",
                "toto","gestionaire");
        Utilisateurs.add(m1);
        Agent m2=  new Agent("tata",
                "tata","agent");
        Utilisateurs.add(m2);

        Client m3=  new Client("tete",
                "tete","client");
        Utilisateurs.add(m3);
        Admin m4=  new Admin("tutu",
                "tutu","admin");
        Utilisateurs.add(m4);

        //=======================
        Tickets = new ArrayList<Ticket>();
        Ticket t1 = new Ticket(1,"voiture","13-03-2019","abc",0); // 0 = Non resolu
        Tickets.add(t1);
        Ticket t2 = new Ticket(2,"plane","13-03-2019","abc",1); //  1 = Resolu
        Tickets.add(t2);
        Ticket t3 = new Ticket(3,"insa","19-03-2019","abc",0); //  1 = Resolu
        Tickets.add(t3);

        //========================

        Applications = new ArrayList<Application>();

        Application a1 = new Application(Tickets);

        Applications.add(a1);

//        =========================

        BigProjets=new ArrayList<Projet>();
        Projet p1 = new Projet(m1, Applications);
        BigProjets.add(p1);


        m2.setResponsable_ticket(Tickets);
        t2.setTicket_status(0);
        //
//
//        // m1 responsabl de p1
        p1.setProj_responsable(m1); // gestionaire
        a1.setApp_responsable(m4); // admin
        t1.setTicket_responsable(m2);
//        // Client apres;
    }
//
    public Utilisateur findMembre(String l, String p){
        for (Utilisateur m:Utilisateurs) {
            if ((m.getUsername().equals(l))
                    &&(m.getPassword().equals(p))) {
                return  m;
            }
        }
        return null;
    }

   public Utilisateur findMembre(String l) {
//        return em.find(Membre.class,l);

        for (Utilisateur m:Utilisateurs) {
            if (m.getUsername().equals(l)) {
                return  m;
            }
        }
        return null;
    }
//


    public Collection<String> findResponsable(String l) {
        Utilisateur m=findMembre(l);
        Collection<String> resp = new ArrayList<>();
        if (m!=null) {
            for (Projet p:m.getResponsable()){
                resp.add(p.getProj_responsable()+ "(" + p.getProj_desc()+ ")");
            }
        }
        return resp;
    }
    public Collection<String> findContribution(String l) {
        Utilisateur m=findMembre(l);
        Collection<String> part = new ArrayList<>();
        if (m!=null) {
            for (Projet p:m.getResponsable()){
                part.add(p.getProj_contribution()+ "(" + p.getProj_desc()+ ")");
            }
        }
        return part;
    }



    public Projet findProjet(String ip){
//        return em.find(Projet.class,ip);

        for (Projet p:projets) {
            if (p.getProj_id().equals(ip)) {
                return p;
            }
        }
        return null;
    }


//    public void ajoutParticipe(String l, String ip) {
//        Membre m=findMembre(l);
//        Projet p=findProjet(ip);
//        if (!m.getParticipe().contains(p)) {
//            m.getParticipe().add(p);
//        }
//    }

    public List<Projet> getProjets() {
//        return em.createQuery("From Projet p")
//                .getResulconfigtList();
        return projets;
    }
    public void changeTicketResolu(Integer l){
        for(Ticket t: Tickets){
            if(t.getTicket_id().equals(l)){
                t.setTicket_status(1);
            }
            else{
                continue;
            }
        }
    }

    public void changeTicket(Integer l, String date, String commentaire){
        for(Ticket t: Tickets){
            if(t.getTicket_id().equals(l)){
                t.setTicket_status(1);
                t.setTicket_date(date);
                t.setTicket_desc(commentaire);
            }
            else{
                continue;
            }
        }
    }


}
