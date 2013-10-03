package net.ellwein.routey.init;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
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

	private static final String				DEFAULT_MAPPING		= "/*";
	private static final String				ROUTEY_SERVLET_NAME	= "RouteyServlet";
	private static final transient Logger	LOGGER				= LoggerFactory.getLogger( RouteyInitializer.class );

	@Override
	public void onStartup( final Set<Class<?>> c, final ServletContext ctx ) throws ServletException {

		LOGGER.debug( "onStartup() called" );

		final ServletRegistration servletRegistration = ctx.getServletRegistration( ROUTEY_SERVLET_NAME );
		// if servlet not registered already
		if ( servletRegistration == null ) {
			final RouteyServlet routeyServlet = ctx.createServlet( RouteyServlet.class );

			// force eager initialization to scan routes early
			routeyServlet.init();

			final Dynamic servlet = ctx.addServlet( ROUTEY_SERVLET_NAME, routeyServlet );
			servlet.addMapping( DEFAULT_MAPPING );
		}

	}
}
