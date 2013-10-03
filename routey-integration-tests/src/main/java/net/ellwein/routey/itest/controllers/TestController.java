package net.ellwein.routey.itest.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ellwein.routey.annotations.Mapping;

public class TestController {

	@Mapping( "^/testme(/?)$" )
	public void getIndex( final HttpServletRequest request, final HttpServletResponse response ) throws IOException {
		response.sendError( HttpServletResponse.SC_NO_CONTENT );
	}

}
