package net.ellwein.routey.example;
import java.util.Collection;

import com.google.common.collect.Lists;

import net.ellwein.routey.core.extensions.RouteyPackageScanner;

public class PackageScanner implements RouteyPackageScanner {

	@Override
	public Collection<String> addPackagesToScan() {
		return Lists.newArrayList("net.ellwein.routey.example");
	}

}
