package net.ellwein.routey.itest.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ellwein.routey.annotations.Mapping;

import com.google.common.base.Throwables;

public class TestController {

	@Mapping("/testme")
	public void getIndex(final HttpServletRequest request, final HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		try {
			response.getWriter().println("TESTME");
			response.getWriter().flush();
		} catch (final IOException e) {
			Throwables.propagate(e);
		}
	}

}
