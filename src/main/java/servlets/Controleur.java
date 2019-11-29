package servlets;

import modele.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.Facade;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controleur")
public class Controleur extends HttpServlet {

    @Autowired
    private Facade facade;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ls = (String) request.getSession().getAttribute("courant");
        if (ls == null) {
            String todo = request.getParameter("TODO");
            if (todo == null) {
                request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
            } else {
                switch (todo) {
                case "log":
                    String l = request.getParameter("login");
                    String p = request.getParameter("password");
                    Utilisateur m = facade.findMembre(l, p);
                    if (m != null) {
                        request.getSession().setAttribute("courant", l);
                        request.setAttribute("surnom", m.getUsername());
                        request.setAttribute("user_id", m.getUser_profil_id());
                        if (m.getUser_profil_id().equals("agent")) {// Ici on fait les vérifications pour savoir si
                                                                    // l'utilisateur a le droit d'y accéder
                            request.setAttribute("responsable_ticket", ((Agent) m).getResponsable_ticket());
                            request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
                        } else if (m.getUser_profil_id().equals("gestionaire")) {// Ici on fait les vérifications pour
                                                                                 // savoir si l'utilisateur a le droit
                                                                                 // d'y accéder
                            request.setAttribute("list_agent", ((Gestionaire) m).getAgent_responsable());
                            request.getRequestDispatcher("WEB-INF/gestionAgent.jsp").forward(request, response);
                        } else if (m.getUser_profil_id().equals("client")) {// Ici on fait les vérifications pour savoir
                                                                            // si l'utilisateur a le droit d'y accéder
                            request.setAttribute("applications_created", facade.getApplications());
                            request.getRequestDispatcher("WEB-INF/createTicket.jsp").forward(request, response);
                        } else if (m.getUser_profil_id().equals("admin")) {// Ici on fait les vérifications pour savoir
                                                                           // si l'utilisateur a le droit d'y accéder
                            request.setAttribute("list_utilisateurs", facade.getUtilisateurs());
                            request.getRequestDispatcher("WEB-INF/utilisateur.jsp").forward(request, response);
                            versPage(request, response);
                        }

                    } else {// Si l'utilisateur n'existe pas il sera réderigé à la page de connexion
                        request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
                    }
                    break;
                }

            }
        } else {
            // deja connecte, donc on garde le session
            String todo = request.getParameter("TODO");
            switch (todo) {

            // ===============================
            // >>>>>>>>>>> Agent <<<<<<<<<<<<<
            // ===============================
            case "resolu": // Change l'etat de ticket non-resolu a resolu
                Integer t = Integer.parseInt(request.getParameter("ticket"));
                String d = request.getParameter("date").toString();
                String commentaire = request.getParameter("commentaire");
                String nomAgentTrace = request.getParameter("surnom");
                Ticket ticketResolu = facade.findTicketByID(t);
                facade.changeTicketResolu(ticketResolu, d, commentaire, nomAgentTrace);
                versPage(request, response);
                break;
            case "charge": // Un agent peut prendre en charge un ticket, des autres ne peuvent pas prend en
                           // charge ce ticket.
                String ticket_information = request.getParameter("role");
                if (ticket_information.contains("OK")) {
                    String[] split = ticket_information.split("OK");
                    Ticket ticket_responsable = facade.findTicketByID(Integer.parseInt(split[0]));
                    Utilisateur responsable = facade.findMembre(split[1]);
                    if (!facade.checkAuthentificationTicket(ticket_responsable)) {
                        facade.changeTicketPrendreEnCharge(ticket_responsable, responsable);
                    } else {
                        // PAS FAIT
                    }
                } else {
                    Ticket ticket_liberer = facade.findTicketByID(Integer.parseInt(ticket_information));
                    if (facade.checkAuthentificationTicket(ticket_liberer)) {
                        facade.libererTicket(ticket_liberer);
                    }
                }
                versPage(request, response);
                break;
            // ================ END ===================

            // ===============================
            // >>>>>>>>>>> Client <<<<<<<<<<<<
            // ===============================

            // Function for create a new ticket for the client
            case "createTicket":
                String date_ticket = request.getParameter("date_ticket").toString();
                String title_ticket = request.getParameter("title_ticket");
                String desc_ticket = request.getParameter("desc_ticket").toString();
                String name_application = request.getParameter("name_application");
                String username = request.getParameter("username");
                Ticket ticketClient = new Ticket(username, name_application, title_ticket, date_ticket, desc_ticket);
                facade.addTicket(ticketClient);
                request.setAttribute("applications_created", facade.getApplications());
                versPage(request, response);
                break;

            case "application":// fonction que sert pour réderiger
                Application(request, response);
                break;
            case "createApplication":// fonction qui ser à créer une nouvelle application
                String app_proj_id = request.getParameter("app_proj_id");
                String app_nom = request.getParameter("app_nom");
                Utilisateur app_responsable = facade.findMembre(request.getParameter("app_responsable"));
                Application applicationPetite = new Application(app_nom, app_proj_id, app_responsable,
                        facade.getTickets());
                facade.addApplication(applicationPetite);
                Application(request, response);
                break;

            case "noop":// fonction pour le button "fermer session"
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
                break;

            case "ticketDepose":// fonction a utiliser dans le moment que l'utilisateur a crée un nouveau ticket
                String clientActive = (String) request.getSession().getAttribute("courant");
                Utilisateur client = facade.findMembre(clientActive);
                request.setAttribute("tickets_deposes", facade.getTicketsClient(client.getUsername()));
                request.getRequestDispatcher("WEB-INF/statusTicket.jsp").forward(request, response);
                break;

            // ===============================
            // >>>>>>> GestionAgent <<<<<<<<<<
            // ===============================

            case "agent": // afficher tous des tickets d'un argent qui pris en charge et a ete resolu
                String agent_select_username = request.getParameter("agent_select");
                Utilisateur agent_select = facade.findMembre(agent_select_username);
                ArrayList<Ticket> Tickets = facade.getTickets();
                ArrayList<Ticket> ticket_pris_en_charge = new ArrayList<Ticket>();
                ArrayList<Ticket> ticket_resolu = new ArrayList<Ticket>();
                for (Ticket ticket_check : Tickets) {
                    if (ticket_check.getTicket_responsable() == agent_select) {
                        ticket_pris_en_charge.add(ticket_check);
                    }
                    if (agent_select.getUsername().equals(ticket_check.getTicket_trace())) {
                        ticket_resolu.add(ticket_check);
                    }
                }
                request.setAttribute("ticket_pris_en_charge", ticket_pris_en_charge);
                request.setAttribute("ticket_resolu", ticket_resolu);
                request.setAttribute("agent_select_username", agent_select_username);
                versPage(request, response);
                break;
            case "gestionaire": // Afficher un page pour creer un projet
                String l = (String) request.getSession().getAttribute("courant");
                Utilisateur m = facade.findMembre(l);
                request.setAttribute("surnom", m.getUsername());
                request.setAttribute("user_id", m.getUser_profil_id());
                request.setAttribute("applications", facade.getApplications());
                request.setAttribute("applications_created", facade.getApplications());
                request.setAttribute("list_agent", ((Gestionaire) m).getAgent_responsable());
                request.getRequestDispatcher("WEB-INF/gestionaire.jsp").forward(request, response);
                break;

            case "creerprojet": // Implementation la fonction "creer un projet"
                String resp_proj = request.getParameter("resp_proj");
                String desc_proj = request.getParameter("desc_proj");
                String[] application_select = request.getParameterValues("application_select");
                facade.creerProjet(resp_proj, desc_proj, application_select);
                versPage(request, response);
                break;

            // ===============================
            // >>>>>>>>>>> Admin <<<<<<<<<<<<<
            // ===============================

            case "admin": // Reload le page de admin
                versPage(request, response);
                break;
            case "autorisation": // Changement le role d'utilisateur selecté
                String AutorisationUtilisateur = request.getParameter("role_change");
                String[] split = AutorisationUtilisateur.split("\\+");
                Utilisateur utilisateur = facade.findMembre(split[0]);
                facade.changeRoleUtilisateur(utilisateur, split[1]);
                versPage(request, response);
                break;
            case "supprimerUtilisateur": // On peut supprimer l'utilisateur selecté
                String getUtilisateur = request.getParameter("role_change");
                String[] getNameUtilisateur = getUtilisateur.split("\\+");
                Utilisateur Utilisateursupprime = facade.findMembre(getNameUtilisateur[0]);
                facade.supprimerUtilisateur(Utilisateursupprime);
                versPage(request, response);
                break;
            case "Utilisateur": // Charger le page pour creer un nouveau utilisateur
                String courant = (String) request.getSession().getAttribute("courant");
                Utilisateur p = facade.findMembre(courant);
                request.setAttribute("surnom", p.getUsername());
                request.setAttribute("user_id", p.getUser_profil_id());
                request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
                break;
            case "createUtilisateur": // Implementation la fonctional qui cree un nouveau utilisateur
                String usernameNew = request.getParameter("username");
                String passwordNew = request.getParameter("password");
                String user_profil_id = request.getParameter("user_profil_id");
                boolean isSucces = facade.creerUtilisateur(usernameNew, passwordNew, user_profil_id);
                request.setAttribute("isSucces", isSucces);
                request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
                break;
            // ============ END ==========
            default:
                versPage(request, response);
            }
        }
    }

    public void Application(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String utilisateurActuelle = (String) request.getSession().getAttribute("courant");
        Utilisateur UtilisateurActuelle = facade.findMembre(utilisateurActuelle);
        request.setAttribute("surnom", UtilisateurActuelle.getUsername());
        request.setAttribute("applications_created", facade.getApplications());
        request.setAttribute("projets_created", facade.getBigProjets());
        request.setAttribute("list_admin", facade.getAdmins());
        request.getRequestDispatcher("WEB-INF/applications.jsp").forward(request, response);
    }

    private void versPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String l = (String) request.getSession().getAttribute("courant");
        Utilisateur m = facade.findMembre(l);
        request.setAttribute("surnom", m.getUsername());
        request.setAttribute("user_id", m.getUser_profil_id());
        if (m.getUser_profil_id().equals("agent")) {
            request.setAttribute("responsable_ticket", ((Agent) m).getResponsable_ticket());
            request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
        } else if (m.getUser_profil_id().equals("gestionaire")) {
            request.setAttribute("list_agent", ((Gestionaire) m).getAgent_responsable());
            request.getRequestDispatcher("WEB-INF/gestionAgent.jsp").forward(request, response);
        } else if (m.getUser_profil_id().equals("client")) {
            request.setAttribute("applications_created", facade.getApplications());
            request.getRequestDispatcher("WEB-INF/createTicket.jsp").forward(request, response);
        } else if (m.getUser_profil_id().equals("admin")) {
            request.setAttribute("list_utilisateurs", facade.getUtilisateurs());
            request.getRequestDispatcher("WEB-INF/utilisateur.jsp").forward(request, response);
        }

    }

}
