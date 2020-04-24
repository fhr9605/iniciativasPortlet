package org.cenicafe.config;


import com.sun.faces.config.FacesInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan(basePackages = "org.cenicafe")
public class SpringConfiguration extends FacesInitializer implements WebApplicationInitializer {
    
	public void onStartup(ServletContext sc) throws ServletException {
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.register(SpringConfiguration.class);
        sc.addListener(new ContextLoaderListener(root));
    }
	
	@Bean
    public static ViewScopeFactoryPostProcessor beanFactoryPostProcessor() {
        return new ViewScopeFactoryPostProcessor();
    }
}