package net.ellwein.routey.internal;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Helper class for annotation scaning.
 * 
 * @author Alexander Ellwein
 * @since 1.0.0
 */
public final class AnnotationScanner {

	private AnnotationScanner() {
	}

	public interface AnnotationScannerNotifier {
		void foundClass(String packageName, Class<?> classFound, Method methodFound);
	}

	public static void scanPackage(final String packageName, final Class<? extends Annotation> annotationToScanFor, final AnnotationScannerNotifier notifier)
			throws IOException, ClassNotFoundException {
		for (final Class<?> clazz : getClasses(packageName)) {
			for (final Method method : clazz.getDeclaredMethods()) {
				if (method.isAnnotationPresent(annotationToScanFor) && notifier != null) {
					notifier.foundClass(packageName, clazz, method);
				}
			}
		}
	}

	/**
	 * Recursive method used to find all classes in a given directory and
	 * subdirs.
	 * 
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	static List<Class<?>> findClasses(final File directory, final String packageName) throws ClassNotFoundException {
		final List<Class<?>> classes = new ArrayList<Class<?>>();
		if (!directory.exists()) {
			return classes;
		}
		final File[] files = directory.listFiles();
		for (final File file : files) {
			if (file.isDirectory()) {
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

	/**
	 * Scans all classes accessible from the context class loader which belong
	 * to the given package and subpackages.
	 * 
	 * @param packageName
	 *            The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	static Iterable<Class<?>> getClasses(final String packageName) throws ClassNotFoundException, IOException {
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		final String path = packageName.replace('.', '/');
		final Enumeration<URL> resources = classLoader.getResources(path);
		final List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			final URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		final List<Class<?>> classes = new ArrayList<Class<?>>();
		for (final File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}

		return classes;
	}
}
