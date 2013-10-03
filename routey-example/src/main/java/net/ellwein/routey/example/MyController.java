package net.ellwein.routey.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ellwein.routey.annotations.Mapping;
import net.ellwein.routey.annotations.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyController {

	private static final transient Logger	LOGGER	= LoggerFactory.getLogger( MyController.class );

	@Mapping( value = { "/testme" }, method = { RequestMethod.GET, RequestMethod.POST } )
	public void index( final HttpServletRequest request, final HttpServletResponse response ) {
		LOGGER.info( "index()" );
		LOGGER.info( "Request: " + request );
		LOGGER.info( "Response: " + response );
	}

}
