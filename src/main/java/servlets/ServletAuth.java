package servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.FacadeAuth;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletAuth extends HttpServlet {

    @Autowired
    FacadeAuth fa;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // on gère la demande de connexion

        String login=request.getParameter("login");
        if (fa.exists(login)) {
            request.getSession().setAttribute("idutil",login);
            request.getRequestDispatcher("/WEB-INF/menu.jsp").forward(request,response);
        } else {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get!!!!");
        // on affiche la page de connexion
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }
}
