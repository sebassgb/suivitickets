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


    /*@Override
//    public void init() throws ServletException {
        super.init();
        //facade=new Facade();
        //facade.init();
    }*/
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ls=(String) request.getSession().getAttribute("courant");
        if (ls==null) {
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
                                if (m.getUser_profil_id().equals("agent")) {
                                    request.setAttribute("responsable_ticket", ((Agent) m).getResponsable_ticket());
                                    request.getRequestDispatcher("WEB-INF/home.jsp").forward(request,response);
                                } else if (m.getUser_profil_id().equals("gestionaire")) {
                                    request.setAttribute("list_agent", ((Gestionaire) m).getAgent_responsable());
                                    request.getRequestDispatcher("WEB-INF/gestionAgent.jsp").forward(request, response);
                                } else if(m.getUser_profil_id().equals("client")) {
                                  /// POUR TOI
                                    request.setAttribute("applications_created", facade.getApplications());
                                    request.getRequestDispatcher("WEB-INF/createTicket.jsp").forward(request, response);
                                } else if(m.getUser_profil_id().equals("admin")){
                                    request.setAttribute("list_utilisateurs", facade.getUtilisateurs());
                                    request.getRequestDispatcher("WEB-INF/utilisateur.jsp").forward(request, response);
                                    versPage(request, response);
                                }

                            } else {
                                request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
                            }
                            break;
                        }

                }
            }
        else{
            // deja connecte
            String todo = request.getParameter("TODO");
            switch(todo) {
                    case "resolu":
                        Integer t = Integer.parseInt(request.getParameter("ticket"));
                        String d = request.getParameter("date");
                        String commentaire = request.getParameter("commentaire");
                        String nom = request.getParameter("surnom");
                        facade.changeTicketResolu(t, d, commentaire, nom);
                        versPage(request, response);
                        break;
                    case "charge":
                        String ticket_information = request.getParameter("role");
                        if(ticket_information.contains("OK")){
                            String[] split = ticket_information.split("OK");
                            Ticket ticket_responsable = facade.findTicketByID(Integer.parseInt(split[0]));
                            Utilisateur responsable = facade.findMembre(split[1]);
                            if(!facade.checkAuthentificationTicket(ticket_responsable)){
                                facade.changeTicketPrendreEnCharge(ticket_responsable, responsable);
                            }
                            else {
                                    // PAS FAIT
                            }
                        } else {
                            Ticket ticket_liberer = facade.findTicketByID(Integer.parseInt(ticket_information));
                            if(facade.checkAuthentificationTicket(ticket_liberer)){
                                facade.libererTicket(ticket_liberer);
                            }
                        }
                        versPage(request, response);
                        break;
                        //Function for create a new ticket for the client
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

                case "application":
                    Application(request, response);
                    break;
                case "createApplication":
                    String app_proj_id = request.getParameter("app_proj_id");
                    String app_nom = request.getParameter("app_nom");
                    Utilisateur app_responsable=facade.findMembre(request.getParameter("app_responsable"));
                    Application applicationPetite = new Application(app_nom, app_proj_id, app_responsable,facade.getTickets());
                    facade.addApplication(applicationPetite);
                    Application(request, response);
                    break;
                    case "noop":
                            HttpSession session = request.getSession(false);
                            if (session !=null){
                                session.invalidate();
                            }
                            request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
                            break;

                case "ticketDepose":
                    String clientActive= (String) request.getSession().getAttribute("courant");
                    Utilisateur client=facade.findMembre(clientActive);
                    request.setAttribute("tickets_deposes", facade.getTicketsClient(client.getUsername()));
                    request.getRequestDispatcher("WEB-INF/statusTicket.jsp").forward(request, response);
                    break;

                 case "agent":
                            String agent_select_username = request.getParameter("agent_select");
                            Utilisateur agent_select =  facade.findMembre(agent_select_username);

                            ArrayList<Ticket> Tickets = facade.getTickets();
                            ArrayList<Ticket> ticket_pris_en_charge = new ArrayList<Ticket>();
                            ArrayList<Ticket> ticket_resolu = new ArrayList<Ticket>();
                            System.out.print(agent_select.getUsername() + "\n");
                            for(Ticket ticket_check: Tickets){

                                if(ticket_check.getTicket_responsable() == agent_select ){
                                    System.out.printf("RESPONSABLE \n");
                                    ticket_pris_en_charge.add(ticket_check);
                                } else if(ticket_check.getTicket_trace() == agent_select.getUsername()){
                                    System.out.printf("OKOKOK \n");
                                    ticket_resolu.add(ticket_check); // resolu
                                }
                            }
                            request.setAttribute("ticket_pris_en_charge", ticket_pris_en_charge);
                            request.setAttribute("ticket_resolu", ticket_resolu);
                            versPage(request, response);
                        break;
                case "gestionaire":
                    String l= (String) request.getSession().getAttribute("courant");
                    Utilisateur m=facade.findMembre(l);
                    request.setAttribute("surnom",m.getUsername());
                    request.setAttribute("user_id", m.getUser_profil_id());
                    request.setAttribute("applications",facade.getApplications());
                    request.setAttribute("applications_created", facade.getApplications());
                    request.getRequestDispatcher("WEB-INF/gestionaire.jsp").forward(request, response);
                    break;

                case "creerprojet":
                    String resp_proj = request.getParameter("resp_proj");
                    String desc_proj = request.getParameter("desc_proj");
                    String[] application_select = request.getParameterValues("application_select");
                    facade.creerProjet(resp_proj, desc_proj, application_select);
                    versPage(request, response);
                    break;
                case "admin":
                    versPage(request, response);
                    break;
                case "autorisation":
                    String AutorisationUtilisateur = request.getParameter("role_change");
                    String[] split = AutorisationUtilisateur.split("\\+");
                    Utilisateur utilisateur = facade.findMembre(split[0]);
                    facade.changeRoleUtilisateur(utilisateur, split[1]);
                    versPage(request, response);
                    break;
                case "supprimerUtilisateur":
                    String getUtilisateur = request.getParameter("role_change");
                    String[] getNameUtilisateur = getUtilisateur.split("\\+");
                    Utilisateur Utilisateursupprime = facade.findMembre(getNameUtilisateur[0]);
                    facade.supprimerUtilisateur(Utilisateursupprime);
                    versPage(request, response);
                    break;

                case "Utilisateur":
                    String courant= (String) request.getSession().getAttribute("courant");
                    Utilisateur p=facade.findMembre(courant);
                    request.setAttribute("surnom",p.getUsername());
                    request.setAttribute("user_id", p.getUser_profil_id());
                    request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
                    break;
                case "createUtilisateur":
                    String usernameNew = request.getParameter("username");
                    String passwordNew = request.getParameter("password");
                    String user_profil_id = request.getParameter("user_profil_id");
                    boolean isSucces = facade.creerUtilisateur(usernameNew, passwordNew, user_profil_id);
                    request.setAttribute("isSucces", isSucces);
                    request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
                    break;
                default:
                        versPage(request, response);
            }
         }
    }

    public void Application(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String utilisateurActuelle= (String) request.getSession().getAttribute("courant");
        Utilisateur UtilisateurActuelle=facade.findMembre(utilisateurActuelle);
        request.setAttribute("surnom",UtilisateurActuelle.getUsername());
        request.setAttribute("applications_created", facade.getApplications());
        request.setAttribute("projets_created", facade.getBigProjets());
        request.getRequestDispatcher("WEB-INF/applications.jsp").forward(request, response);
    }

    private void versPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String l= (String) request.getSession().getAttribute("courant");
        Utilisateur m=facade.findMembre(l);
        request.setAttribute("surnom",m.getUsername());
        request.setAttribute("user_id", m.getUser_profil_id());
        if(m.getUser_profil_id().equals("agent")) {
            request.setAttribute("responsable_ticket", ((Agent) m).getResponsable_ticket());
            request.getRequestDispatcher("WEB-INF/home.jsp").forward(request,response);
        } else if(m.getUser_profil_id().equals("gestionaire")){
            request.setAttribute("list_agent", ((Gestionaire) m).getAgent_responsable());
            request.setAttribute("applications_created", facade.getApplications());
            request.getRequestDispatcher("WEB-INF/gestionAgent.jsp").forward(request, response);
        }
        else if(m.getUser_profil_id().equals("client")){
            request.setAttribute("applications_created", facade.getApplications());
            request.getRequestDispatcher("WEB-INF/createTicket.jsp").forward(request, response);
        } else if(m.getUser_profil_id().equals("admin")){
            request.setAttribute("list_utilisateurs", facade.getUtilisateurs());
            request.getRequestDispatcher("WEB-INF/utilisateur.jsp").forward(request, response);
        }

    }


}
