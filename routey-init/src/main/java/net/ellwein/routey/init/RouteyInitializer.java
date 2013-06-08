package net.ellwein.routey.init;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import net.ellwein.routey.core.RouteyServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Initializes the ServletContainer via Java SPI. This initializer will give
 * Routey full control over all the incoming requests.
 * 
 * @author Alex Ellwein
 * @since 1.0.0
 * 
 */
public class RouteyInitializer implements ServletContainerInitializer {

	private static final String DEFAULT_MAPPING = "/*";
	private static final String ROUTEY_SERVLET_NAME = "RouteyServlet";
	private static final transient Logger LOGGER = LoggerFactory
			.getLogger(RouteyInitializer.class);

	@Override
	public void onStartup(final Set<Class<?>> c, final ServletContext ctx)
			throws ServletException {

		LOGGER.debug("onStartup() called");

		final Dynamic servlet = ctx.addServlet(ROUTEY_SERVLET_NAME,
				RouteyServlet.class);
		if (servlet == null) {
			LOGGER.error("RouteyServlet was already registered in the current ServletContext.");
			return;
		}
		final Set<String> mapping = servlet.addMapping(DEFAULT_MAPPING);
		if (!mapping.contains(DEFAULT_MAPPING)) {
			LOGGER.error("Unable to add default mapping for Routey servlet.");
		} else {
			LOGGER.info(ROUTEY_SERVLET_NAME
					+ " was succesfully registered for mapping \""
					+ DEFAULT_MAPPING + "\"");
		}
	}
}
