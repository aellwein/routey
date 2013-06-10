package net.ellwein.routey.core.extensions;

import java.util.Collection;

/**
 * Base Routey extension which provides the name of packages to scan for
 * annotations.
 * 
 * @author Alexander Ellwein
 * @since 1.0.0
 */
public interface RouteyPackageScanner {

	/**
	 * Adds a collection of package names to scan. NOTE: regular expressions are
	 * not supported here (yet).
	 * 
	 * @return Collection of full-qualified package names.
	 */
	Collection<String> addPackagesToScan();
}
