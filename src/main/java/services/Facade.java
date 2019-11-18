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
        Utilisateurs=new ArrayList<Utilisateur>();
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

        Agent m5=  new Agent("titi",
                "titi","agent");
        Utilisateurs.add(m5);

        //=======================
        Tickets = new ArrayList<Ticket>();
        Ticket t1 = new Ticket("tete","voiture","13-03-2019","abc");
        Tickets.add(t1);
        Ticket t2 = new Ticket("tete","plane","13-04-2019","abc");
        Tickets.add(t2);
        Ticket t3 = new Ticket("tete","insa","13-06-2019","abc");
        Tickets.add(t3);
        t1.setTicket_trace("tata");
        t2.setTicket_trace("tata");
        t1.setTicket_responsable(m2);


        //========================

        Applications = new ArrayList<Application>();

        Application a1 = new Application(Tickets);

        Applications.add(a1);

//        =========================

        BigProjets=new ArrayList<Projet>();
        Projet p1 = new Projet(m1, Applications);
        BigProjets.add(p1);

//        t2.setTicket_status(0);
        //
//
//        // m1 responsabl de p1
        p1.setProj_responsable(m1); // gestionaire
        a1.setApp_responsable(m4); // admin
        m2.setResponsable_ticket(Tickets);
        m5.setResponsable_ticket(Tickets);

        m1.setAgent_responsable(Utilisateurs);
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


//    public Collection<String> findResponsable(String l) {
//        Utilisateur m=findMembre(l);
//        Collection<String> resp = new ArrayList<>();
//        if (m!=null) {
//            for (Projet p:m.getResponsable()){
//                resp.add(p.getProj_responsable()+ "(" + p.getProj_desc()+ ")");
//            }
//        }
//        return resp;
//    }
//    public Collection<String> findContribution(String l) {
//        Utilisateur m=findMembre(l);
//        Collection<String> part = new ArrayList<>();
//        if (m!=null) {
//            for (Projet p:m.getResponsable()){
//                part.add(p.getProj_contribution()+ "(" + p.getProj_desc()+ ")");
//            }
//        }
//        return part;
//    }



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
    public void changeTicketResolu(Integer l,  String d, String commentaire, String nom){
        for(Ticket t: Tickets){
            if(t.getTicket_id().equals(l)){
                t.setTicket_status(1);
                t.setTicket_date(d);
                t.setTicket_desc(commentaire);
                t.setTicket_responsable(findMembre(nom));
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



    public Ticket findTicketByID(Integer l){
        for(Ticket t: Tickets){
            if(t.getTicket_id().equals(l)){
                return t;
            }
        }
        return null;
    }

    public boolean checkAuthentificationTicket(Ticket t){
        // true = interdire de prendre en charge
        // false =  permettre de prendre en charge
        if(!t.getTicket_aut()) {
            return false; // permettre de prendre en charge
        } else{ return  true;}
    }


    public void changeTicketPrendreEnCharge(Ticket ticket_responsable, Utilisateur responsable) {
        ticket_responsable.setTicket_responsable(responsable);
    }

    public void libererTicket(Ticket ticket_liberer) {
        ticket_liberer.setTicket_responsable(null);
        ticket_liberer.setTicket_aut(false);
    }


    public void addTicket(Ticket ticketClient) {
        Tickets.add(ticketClient);
        System.out.println(Tickets.get(0).getTicket_client_creator());
        System.out.println(Tickets.get(0).getTicket_id());
        System.out.println(Tickets.get(0).getTicket_appnom());
        System.out.println(Tickets.get(0).getTicket_date());
        System.out.println(Tickets.get(0).getTicket_desc());
    }


    //Function that will find the tickets created by the user in session
    public ArrayList<Ticket> getTicketsClient(String username) {
        ArrayList<Ticket> TicketsClient = new ArrayList<Ticket>();
        if(Tickets.size()>0) {
            for (Ticket t : Tickets) {
                if (t.getTicket_client_creator().equals(username)) {
                    TicketsClient.add(t);
                }
            }
        }
            return TicketsClient;
    }

    public ArrayList<Ticket> getTickets() {
        return Tickets;
    }

    public ArrayList<Projet> getBigProjets() {
        return BigProjets;
    }

    public void setBigProjets(ArrayList<Projet> bigProjets) {
        BigProjets = bigProjets;
    }
}
