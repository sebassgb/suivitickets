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
    private ArrayList<Ticket> Tickets;
    private ArrayList<Application> Applications;

    public Facade() {
    }

    @PostConstruct
    public void init() {
        Utilisateurs = new ArrayList<Utilisateur>();
        Gestionaire m1 = new Gestionaire("toto", "toto", "gestionaire");

        Utilisateurs.add(m1);
        Agent m2 = new Agent("tata", "tata", "agent");
        Utilisateurs.add(m2);
        Client m3 = new Client("tete", "tete", "client");
        Utilisateurs.add(m3);
        Admin m4 = new Admin("tutu", "tutu", "admin");
        Utilisateurs.add(m4);
        Agent m5 = new Agent("titi", "titi", "agent");
        Utilisateurs.add(m5);

        Tickets = new ArrayList<Ticket>();
        Ticket t1 = new Ticket("tete", "voiture", "title 1", "13-03-2019", "abc");
        Tickets.add(t1);
        Ticket t2 = new Ticket("tete", "plane", "title 2", "13-04-2019", "abc");
        Tickets.add(t2);
        Ticket t3 = new Ticket("tete", "insa", "title 3", "13-06-2019", "abc");
        Tickets.add(t3);

        // t1.setTicket_trace("tata");
        // t2.setTicket_trace("tata");

        Applications = new ArrayList<Application>();

        Application a1 = new Application("Projet d'option", "3", m4, Tickets);
        Application a2 = new Application("Projet d'option2", "4", m4, Tickets);

        Applications.add(a1);
        Applications.add(a2);

        BigProjets = new ArrayList<Projet>();
        Projet p1 = new Projet(m1, Applications);
        BigProjets.add(p1);
        Projet p2 = new Projet(m1, Applications);
        BigProjets.add(p2);

        p1.setProj_responsable(m1); // gestionaire
        m2.setResponsable_ticket(Tickets);
        m5.setResponsable_ticket(Tickets);

        m1.setAgent_responsable(Utilisateurs);
        // // Client apres;
    }

    //
    public Utilisateur findMembre(String l, String p) {
        for (Utilisateur m : Utilisateurs) {
            if ((m.getUsername().equals(l)) && (m.getPassword().equals(p))) {
                return m;
            }
        }
        return null;
    }

    public Utilisateur findMembre(String l) {

        for (Utilisateur m : Utilisateurs) {
            if (m.getUsername().equals(l)) {
                return m;
            }
        }
        return null;
    }

    public void changeTicketResolu(Ticket ticketsolu, String d, String commentaire, String nom) {
        ticketsolu.setTicket_trace(nom);
        ticketsolu.setTicket_status(1);
        ticketsolu.setTicket_aut(false);
        ticketsolu.setTicket_date(d);
        ticketsolu.setTicket_desc(commentaire);
        ticketsolu.setTicket_responsable(null);
    }

    public Ticket findTicketByID(Integer l) {
        for (Ticket t : Tickets) {
            if (t.getTicket_id().equals(l)) {
                return t;
            }
        }
        return null;
    }

    public boolean checkAuthentificationTicket(Ticket t) {
        if (!t.getTicket_aut()) {
            return false; // permettre de prendre en charge
        } else {
            return true;
        }
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
    }

    public void addApplication(Application newApplication) {
        Applications.add(newApplication);
    }

    // Function that will find the tickets created by the user in session
    public ArrayList<Ticket> getTicketsClient(String username) {
        ArrayList<Ticket> TicketsClient = new ArrayList<Ticket>();
        if (Tickets.size() > 0) {
            for (Ticket t : Tickets) {
                if (t.getTicket_client_creator().equals(username)) {
                    TicketsClient.add(t);
                }
            }
        }
        return TicketsClient;
    }

    public ArrayList<Application> getApplications() {
        return Applications;
    }

    public ArrayList<Ticket> getTickets() {
        return Tickets;
    }

    public ArrayList<Projet> getBigProjets() {
        return BigProjets;
    }

    public void creerProjet(String resp_proj, String desc_proj, String[] application_select) {

        Utilisateur resp_proj_utilisateur = findMembre(resp_proj);
        ArrayList<Application> app = new ArrayList<Application>();
        for (String l : application_select) {
            app.add(findAppication(l));
        }
        Projet projet_new = new Projet(resp_proj_utilisateur, app);
        projet_new.setProj_desc(desc_proj);
        BigProjets.add(projet_new);

    }

    private Application findAppication(String l) {
        for (Application a : Applications) {
            if (a.getApp_nom().equals(l)) {
                return a;
            }
        }
        return null;
    }

    public List<Utilisateur> getUtilisateurs() {
        return Utilisateurs;
    }

    public boolean creerUtilisateur(String username, String password, String user_profil_id) {
        if (!Utilisateurs.contains(findMembre(username))) {
            if (user_profil_id.equals("gestionaire")) {
                Gestionaire newUtil = new Gestionaire(username, password, user_profil_id);
                newUtil.setAgent_responsable(Utilisateurs);
                Utilisateurs.add(newUtil);
            } else if (user_profil_id.equals("agent")) {
                Agent newUtil = new Agent(username, password, user_profil_id);
                newUtil.setResponsable_ticket(Tickets);
                Utilisateurs.add(newUtil);
            } else if (user_profil_id.equals("client")) {
                Utilisateurs.add(new Client(username, password, user_profil_id));
            } else {
                Utilisateurs.add(new Admin(username, password, user_profil_id));
            }
            return true;
        } else {
            // PAS FAIT
            return false;
        }

    }

    public void changeRoleUtilisateur(Utilisateur utilisateur, String s) {
        Utilisateurs.remove(utilisateur);
        creerUtilisateur(utilisateur.getUsername(), utilisateur.getPassword(), s);
    }

    public void supprimerUtilisateur(Utilisateur utilisateursupprime) {
        Utilisateurs.remove(utilisateursupprime);
    }
}
