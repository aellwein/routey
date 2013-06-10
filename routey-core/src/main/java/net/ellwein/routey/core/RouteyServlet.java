package net.ellwein.routey.core;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ellwein.routey.annotations.Mapping;
import net.ellwein.routey.annotations.RequestMethod;
import net.ellwein.routey.core.AnnotationScanner.AnnotationScannerNotifier;
import net.ellwein.routey.core.extensions.RouteyPackageScanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This servlet is the central place for routing requests via Routey.
 * 
 * @author Alex Ellwein
 * @since 1.0.0
 */
public final class RouteyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final transient Logger LOGGER = LoggerFactory.getLogger(RouteyServlet.class);

	private final Map<String, Object> initializedControllers = new HashMap<String, Object>();

	private final Map<String, Routing> requestMappingsForGET = new HashMap<String, Routing>();
	private final Map<String, Routing> requestMappingsForPOST = new HashMap<String, Routing>();
	private final Map<String, Routing> requestMappingsForDELETE = new HashMap<String, Routing>();
	private final Map<String, Routing> requestMappingsForHEAD = new HashMap<String, Routing>();
	private final Map<String, Routing> requestMappingsForPUT = new HashMap<String, Routing>();
	private final Map<String, Routing> requestMappingsForTRACE = new HashMap<String, Routing>();
	private final Map<String, Routing> requestMappingsForOPTIONS = new HashMap<String, Routing>();

	@Override
	public void init() throws ServletException {
		LOGGER.debug("init()");
		super.init();
		try {
			scanMappingAnnotations();
		} catch (IOException | ClassNotFoundException e) {
			LOGGER.error("error while scanning for mapping annotations: ", e);
			throw new ServletException(e);
		}
	}

	private void scanMappingAnnotations() throws IOException, ClassNotFoundException {
		for (final String aPackage : findPackagesToScan()) {
			AnnotationScanner.scanPackage(aPackage, Mapping.class, new AnnotationScannerNotifier() {

				@Override
				public void foundClass(final String packageName, final Class<?> classFound, final Method methodFound) {
					LOGGER.debug("found annotated method: " + classFound.getName() + "." + methodFound.getName() + "()");
					RequestMethod[] methods;

					final Mapping mapping = methodFound.getAnnotation(Mapping.class);
					if (mapping.value().length == 0) {
						LOGGER.error("Missing request URI in " + classFound.getName() + "." + methodFound.getName() + "(). Ignoring this mapping.");
						return;
					}
					if (mapping.method().length == 0) {
						LOGGER.debug("Mapping " + classFound.getName() + "." + methodFound.getName() + "() to default GET method.");
						methods = new RequestMethod[] { RequestMethod.GET };
					} else {
						methods = mapping.method();
					}
					for (final String requestUri : mapping.value()) {
						if (!initializedControllers.containsKey(classFound.getName())) {
							final Object controller = classFound.newInstance(); // TODO
							initializedControllers.put(classFound.getName(), controller);
						}
						for (final RequestMethod reqMethod : methods) {
							switch (reqMethod) {
							case DELETE:
								LOGGER.debug("map uri \"" + requestUri + "\" --> " + classFound.getName() + "." + methodFound.getName()
										+ "() for request method " + reqMethod);
								requestMappingsForDELETE.put(requestUri, new Routing(initializedControllers.get(classFound.getName()), methodFound));
								break;
							case GET:
								LOGGER.debug("map uri \"" + requestUri + "\" --> " + classFound.getName() + "." + methodFound.getName()
										+ "() for request method " + reqMethod);
								requestMappingsForGET.put(requestUri, new Routing(initializedControllers.get(classFound.getName()), methodFound));
								break;
							case HEAD:
								LOGGER.debug("map uri \"" + requestUri + "\" --> " + classFound.getName() + "." + methodFound.getName()
										+ "() for request method " + reqMethod);
								requestMappingsForHEAD.put(requestUri, new Routing(initializedControllers.get(classFound.getName()), methodFound));
								break;
							case OPTIONS:
								LOGGER.debug("map uri \"" + requestUri + "\" --> " + classFound.getName() + "." + methodFound.getName()
										+ "() for request method " + reqMethod);
								requestMappingsForOPTIONS.put(requestUri, new Routing(initializedControllers.get(classFound.getName()), methodFound));
								break;
							case POST:
								LOGGER.debug("map uri \"" + requestUri + "\" --> " + classFound.getName() + "." + methodFound.getName()
										+ "() for request method " + reqMethod);
								requestMappingsForPOST.put(requestUri, new Routing(initializedControllers.get(classFound.getName()), methodFound));
								break;
							case PUT:
								LOGGER.debug("map uri \"" + requestUri + "\" --> " + classFound.getName() + "." + methodFound.getName()
										+ "() for request method " + reqMethod);
								requestMappingsForPUT.put(requestUri, new Routing(initializedControllers.get(classFound.getName()), methodFound));
								break;
							case TRACE:
								LOGGER.debug("map uri \"" + requestUri + "\" --> " + classFound.getName() + "." + methodFound.getName()
										+ "() for request method " + reqMethod);
								requestMappingsForTRACE.put(requestUri, new Routing(initializedControllers.get(classFound.getName()), methodFound));
								break;
							default:
								break;
							}
						}
					}
				}
			});
		}
	}

	@Override
	protected void doDelete(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.debug("doDelete()");
		final Routing routing = requestMappingsForDELETE.get(req.getRequestURI());
		if (routing != null) {
			mapArgsForRoutingAndInvoke(req, resp, routing);
		} else {
			// default handling
			super.doDelete(req, resp);
		}
	}

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.debug("doGet()");
		final Routing routing = requestMappingsForGET.get(req.getRequestURI());
		if (routing != null) {
			mapArgsForRoutingAndInvoke(req, resp, routing);
		} else {
			// default handling
			super.doGet(req, resp);
		}
	}

	@Override
	protected void doHead(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.debug("doHead()");
		final Routing routing = requestMappingsForHEAD.get(req.getRequestURI());
		if (routing != null) {
			mapArgsForRoutingAndInvoke(req, resp, routing);
		} else {
			// default handling
			super.doHead(req, resp);
		}

	}

	@Override
	protected void doOptions(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.debug("doOptions()");
		final Routing routing = requestMappingsForOPTIONS.get(req.getRequestURI());
		if (routing != null) {
			mapArgsForRoutingAndInvoke(req, resp, routing);
		} else {
			// default handling
			super.doOptions(req, resp);
		}

	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.debug("doPost()");
		final Routing routing = requestMappingsForPOST.get(req.getRequestURI());
		if (routing != null) {
			mapArgsForRoutingAndInvoke(req, resp, routing);
		} else {
			// default handling
			super.doPost(req, resp);
		}
	}

	@Override
	protected void doPut(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.debug("doPut()");
		final Routing routing = requestMappingsForPUT.get(req.getRequestURI());
		if (routing != null) {
			mapArgsForRoutingAndInvoke(req, resp, routing);
		} else {
			// default handling
			super.doPut(req, resp);
		}

	}

	@Override
	protected void doTrace(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.debug("doTrace()");
		final Routing routing = requestMappingsForTRACE.get(req.getRequestURI());
		if (routing != null) {
			mapArgsForRoutingAndInvoke(req, resp, routing);
		} else {
			// default handling
			super.doTrace(req, resp);
		}

	}

	protected List<String> findPackagesToScan() {
		LOGGER.debug("findPackagesToScan()");
		final List<String> result = new ArrayList<String>();

		// search for possible extensions - RouteyPackageScanner
		final ServiceLoader<RouteyPackageScanner> serviceLoader = ServiceLoader.load(RouteyPackageScanner.class);

		for (final RouteyPackageScanner packageScanner : serviceLoader) {
			final Collection<String> addPackagesToScan = packageScanner.addPackagesToScan();
			if (addPackagesToScan != null) {
				for (final String aPackage : addPackagesToScan) {
					result.add(aPackage);
				}
			}
		}
		return result;
	}

	protected void mapArgsForRoutingAndInvoke(final HttpServletRequest req, final HttpServletResponse resp, final Routing routing) {

	}
}
