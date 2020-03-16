package com.eynan.shoppingmore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.faces.webapp.FacesServlet;

@ComponentScan(basePackages={"com.eynan.*"})
@EnableAutoConfiguration
public class ShoppingmoreApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingmoreApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		return new ServletRegistrationBean(servlet, "*.jsf","*.xhtml");
	}

}
