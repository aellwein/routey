package net.ellwein.routey.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This servlet is the central place for routing requests via Routey.
 * 
 * @author Alex Ellwein
 * @since 1.0.0
 */
public class RouteyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final transient Logger LOGGER = LoggerFactory
			.getLogger(RouteyServlet.class);

	@Override
	protected void doDelete(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doHead(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doHead(req, resp);
	}

	@Override
	protected void doOptions(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doOptions(req, resp);
	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void doPut(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

	@Override
	protected void doTrace(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doTrace(req, resp);
	}
}
