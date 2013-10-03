package net.ellwein.routey.example;

import java.util.Collection;

import net.ellwein.routey.core.extensions.RouteyPackageScanner;

import com.google.common.collect.Lists;

public class PackageScanner implements RouteyPackageScanner {

	@Override
	public Collection<String> addPackagesToScan() {
		return Lists.newArrayList( "net.ellwein.routey.example" );
	}
}
