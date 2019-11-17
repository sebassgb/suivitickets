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
//            String todo = request.getParameter("TODO");
//            if (todo == null) {
//                request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
//            }
//            else {
//                switch (todo) {
//                    case "filtrer":
//                        String filtre  = request.getParameter("filtre");
//                        request.setAttribute("filtre", filtre);
//                        request.setAttribute("resfiltre",service2.filter(filtre));
//                        //service2.filter(filtre);
//                        request.getRequestDispatcher("WEB-INF/filtre.jsp").forward(request,response);
//                        break;
//                    default:
//                        versPage(request, response);
//                        break;
//                }
//            }
            System.out.printf("FAIL");
         }
    }

    private void versPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String l= (String) request.getSession().getAttribute("courant");
//        Utilisateur m=facade.findMembre(l);
//        request.setAttribute("surnom",m.getUsername());
//        request.setAttribute("participe", facade.findParticipe(l));
//        request.setAttribute("responsable",facade.findResponsable(l));
        request.getRequestDispatcher("WEB-INF/home.jsp").forward(request,response);
    }



}
