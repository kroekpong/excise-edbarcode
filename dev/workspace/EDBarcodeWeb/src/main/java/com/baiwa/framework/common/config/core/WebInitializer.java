package com.baiwa.framework.common.config.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import com.baiwa.framework.common.config.AppConfig;
import com.baiwa.framework.common.config.RestConfig;
import com.baiwa.framework.common.config.WebConfig;
import com.baiwa.framework.common.config.WebServiceConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// configure Spring Container in ContextListener
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	// configure default Spring Dispatcher (DispatcherServlet)
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	// configure default Spring MVC URL pattern
	@Override
	protected String[] getServletMappings() {
		return new String[] { "*.htm" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		
		configRestDispatcher(servletContext);
		configWebServiceDispatcher(servletContext);
	}

	private void configRestDispatcher(ServletContext servletContext) throws ServletException {
		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext restContext = new AnnotationConfigWebApplicationContext();
		restContext.register(RestConfig.class);

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic restDispatcher = servletContext.addServlet("baiwa-rest", new DispatcherServlet(restContext));
		restDispatcher.setLoadOnStartup(2);
		restDispatcher.addMapping("/json/*");
	}

	private void configWebServiceDispatcher(ServletContext servletContext) throws ServletException {
		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext wsContext = new AnnotationConfigWebApplicationContext();
		wsContext.register(WebServiceConfig.class);

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic wsDispatcher = servletContext.addServlet("baiwa-ws", new MessageDispatcherServlet(wsContext));
		wsDispatcher.setLoadOnStartup(3);
		wsDispatcher.addMapping("/ws/*");
	}

}