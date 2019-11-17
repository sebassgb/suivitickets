package filtres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.FacadeAuth;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Filter1", urlPatterns = "/servlet1")
public class Filter implements javax.servlet.Filter {

    @Autowired
    FacadeAuth fa;

    @Override
    public void init(FilterConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("Vous devez avoir le rôle 1");

        //on récupere le nom de l'utilisateur courant
        HttpSession session=((HttpServletRequest)req).getSession();
        String idutil=(String)session.getAttribute("idutil");

        System.out.println(idutil);
        // on regarde s'il a le droit de venir là
        if (fa.hasRole(idutil,"role1")) {
            // si oui on passe la main au servlet
            chain.doFilter(req, resp);
        } else {
            // sinon on renvoie vers une page d'erreur
            req.getRequestDispatcher("/WEB-INF/forbiden.jsp").forward(req, resp);
        }
    }


}
