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
            }
            else {
                switch (todo) {
                    case "log":
                        String l = request.getParameter("login");
                        String p = request.getParameter("password");
                        Utilisateur m = facade.findMembre(l, p);
                        if (m != null) {
                            request.getSession().setAttribute("courant", l);
                            request.setAttribute("surnom", m.getUsername());
                            request.setAttribute("user_id", m.getUser_profil_id());
                            if(m.getUser_profil_id().equals("agent")) {
                                m = (Agent) m;
                                request.setAttribute("responsable_ticket", ((Agent) m).getResponsable_ticket());
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
                        facade.changeTicketResolu(t);
                        versPage(request, response);
                        break;
                case "charge":
                        String charges = request.getParameter("charges");
                         System.out.printf("toto");
                    case "noop":
                        request.getSession().invalidate();
                        request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
                        return;
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
        }
        request.getRequestDispatcher("WEB-INF/home.jsp").forward(request,response);
    }



}
