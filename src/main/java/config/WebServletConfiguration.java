package config;

import org.h2.server.web.WebServlet;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import servlets.Controleur;
import servlets.Servlet1;
import servlets.Servlet2;
import servlets.ServletAuth;

import javax.servlet.*;
import java.util.EnumSet;

public class WebServletConfiguration implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webctx=new AnnotationConfigWebApplicationContext();
        webctx.register(ClientWebConfig.class);
        webctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet=servletContext.addServlet("controleur",new Controleur());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        ContextLoaderListener contextLoaderListener =
                new ContextLoaderListener(webctx);
        servletContext.addListener(contextLoaderListener);

       FilterRegistration.Dynamic filter=
          servletContext.addFilter("emInViewFilter",
          new OpenEntityManagerInViewFilter());
       filter.addMappingForUrlPatterns(EnumSet
         .allOf(DispatcherType
          .class), true, "/*");

        ServletRegistration.Dynamic servlet0=servletContext.addServlet("auth",new ServletAuth());
        servlet0.setLoadOnStartup(1);
        servlet0.addMapping("/servletauth");


        ServletRegistration.Dynamic servlet1=servletContext.addServlet("s1",new Servlet1());
        servlet1.setLoadOnStartup(1);
        servlet1.addMapping("/servlet1");

        ServletRegistration.Dynamic servlet2=servletContext.addServlet("s2",new Servlet2());
        servlet2.setLoadOnStartup(1);
        servlet2.addMapping("/servlet2");

        ServletRegistration.Dynamic h2servlet = servletContext
                .addServlet("h2-console", new WebServlet());
        h2servlet.setLoadOnStartup(2);
        h2servlet.addMapping("/console/*");
    }
}
