package servlets;

import modele.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.Facade;
import services.Service2;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet("/Controleur")
public class Controleur extends HttpServlet {

    @Autowired
    private Facade facade;

    @Autowired
    private Service2 service2;

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
                                    m = (Agent) m;
                                    request.setAttribute("responsable_ticket", ((Agent) m).getResponsable_ticket());
                                } else if (m.getUser_profil_id().equals("gestionaire")) {
                                    request.setAttribute("list_agent", ((Gestionaire) m).getAgent_responsable());
                                    request.getRequestDispatcher("WEB-INF/gestionAgent.jsp").forward(request, response);

                                } else if(m.getUser_profil_id().equals("client")) {
                                  /// POUR TOI

                                }
                                versPage(request, response);

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
                                    //
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
                        Ticket ticketClient = new Ticket(name_application, date_ticket, desc_ticket);
                        facade.addTicket(ticketClient);
                        versPage(request, response);
                        break;

                    case "noop":
                            HttpSession session = request.getSession(false);
                            if (session !=null){
                                session.invalidate();
                            }
                            request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
                            break;

                case "ticketDepose":
                    request.getRequestDispatcher("WEB-INF/statusTicket.jsp").forward(request, response);
                    break;

                 case "agent":
                            String agent_select_username = request.getParameter("agent_select");
                            Utilisateur agent_select =  facade.findMembre(agent_select_username);

                            // tim trong dong tickets xem then ticket nao co t.responsable = agent_select

                            ArrayList<Ticket> Tickets = facade.getTickets();
                            ArrayList<Ticket> ticket_pris_en_charge = new ArrayList<Ticket>();
                            ArrayList<Ticket> ticket_resolu = new ArrayList<Ticket>();
                            for(Ticket ticket_check: Tickets){
                                if( ticket_check.getTicket_trace() == agent_select.getUsername()){
                                    ticket_resolu.add(ticket_check); // resolu
                                }
                                if( ticket_check.getTicket_responsable() == agent_select ){
                                    ticket_pris_en_charge.add(ticket_check);
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

                    request.setAttribute("projets",facade.getBigProjets());

                    request.getRequestDispatcher("WEB-INF/gestionaire.jsp").forward(request, response);
                    break;
                default:
                        versPage(request, response);
            }
         }
    }



    private void versPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String l= (String) request.getSession().getAttribute("courant");
        Utilisateur m=facade.findMembre(l);
        request.setAttribute("surnom",m.getUsername());
        request.setAttribute("user_id", m.getUser_profil_id());
        if(m.getUser_profil_id().equals("agent")) {
            m = (Agent) m;
            request.setAttribute("responsable_ticket", ((Agent) m).getResponsable_ticket());
            request.getRequestDispatcher("WEB-INF/home.jsp").forward(request,response);
        } else if(m.getUser_profil_id().equals("gestionaire")){
            request.setAttribute("list_agent", ((Gestionaire) m).getAgent_responsable());
            request.getRequestDispatcher("WEB-INF/gestionAgent.jsp").forward(request, response);
        }

    }

}
