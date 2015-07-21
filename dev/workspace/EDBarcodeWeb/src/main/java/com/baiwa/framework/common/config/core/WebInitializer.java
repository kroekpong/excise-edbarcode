package com.baiwa.framework.common.config.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import com.baiwa.framework.common.config.AppConfig;
import com.baiwa.framework.common.config.RestConfig;
import com.baiwa.framework.common.config.WebConfig;
import com.baiwa.framework.common.config.WebServiceConfig;

public class WebInitializer implements WebApplicationInitializer {

	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		logger.trace("onStartup");
		logger.debug("onStartup");
		logger.info("onStartup");
		logger.warn("onStartup");
		logger.error("onStartup");
		logger.fatal("onStartup");
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

		rootContext.setDisplayName("Baiwa Web");

		// Create the 'root' Spring application context
		rootContext.register(AppConfig.class);
		// Manage the life-cycle of the root application context
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		configWebDispatcher(servletContext);
		configRestDispatcher(servletContext);
		configWebServiceDispatcher(servletContext);
	}

	private void configWebDispatcher(ServletContext servletContext) throws ServletException {
		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(WebConfig.class);

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic webDispatcher = servletContext.addServlet("baiwa-web", new DispatcherServlet(webContext));
		webDispatcher.setLoadOnStartup(1);
		webDispatcher.addMapping("*.htm");
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
