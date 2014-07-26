package com.ronx.smart;

import com.ronx.smart.configuration.RestWebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class SmartWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(RestWebConfig.class);
        ctx.setServletContext(servletContext);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
        dispatcherServlet.setDetectAllViewResolvers(true);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", dispatcherServlet);
        dynamic.addMapping("/*");
        dynamic.setLoadOnStartup(1);
    }
}
