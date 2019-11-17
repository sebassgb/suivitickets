package servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.Facade;
import services.Service2;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/Controleur")
public class Controleur extends HttpServlet {
    @Autowired
    private Facade facade;

    @Autowired
    private Service2 service2;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Init controleur");
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ls=(String) request.
                getSession().getAttribute("courant");

        if (ls==null) {

            String todo = request.getParameter("TODO");

            if (todo == null) {
                request
                        .getRequestDispatcher("WEB-INF/connexion.jsp")
                        .forward(request, response);
            } else {
                switch (todo) {
                    case "log":
                        String l = request.getParameter("login");
                        String p = request.getParameter("password");
                        Membre m = facade.findMembre(l, p);
                        if (m != null) {
                            request.getSession().setAttribute("courant", l);
                            versPage(request, response);
                        } else {
                            request.getRequestDispatcher("WEB-INF/connexion.jsp")
                                    .forward(request, response);
                        }
                        break;
                }
            }

        } else {
            // déjà connecté
            String todo = request.getParameter("TODO");

            switch (todo) {
                case "filtrer" :
                    String filtre=request.getParameter("filtre");
                    facade.findProjet(filtre);
                    request.setAttribute("filtre",filtre);
                    request.setAttribute
                            ("resfiltre",service2.filtrer(filtre));
                    request.getRequestDispatcher("WEB-INF/filtree.jsp")
                            .forward(request,response);

                    break;

                case "ajouterProjet":
                    String intitule=request.getParameter("intitule");
                    String description= request.getParameter("description");
                    facade.nouveauProjet(ls,intitule,description);
                    versPage(request, response);
                    break;
                case "renommer":
                    String nouveau = request.getParameter("surnom");
                    facade.changerSurnom(ls, nouveau);
                    versPage(request,response);
                    break;
                    default:
                    versPage(request,response);

            }

        }

    }

private void versPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String l=(String)request.
            getSession().getAttribute("courant");
    Membre m=facade.findMembre(l);
    request.setAttribute("details", facade.details(l));
    request.getRequestDispatcher("WEB-INF/page.jsp")
            .forward(request,response);
}

}
