package net.ellwein.routey.itest;

import java.util.Collection;

import net.ellwein.routey.core.extensions.RouteyPackageScanner;

import com.google.common.collect.ImmutableList;

public class TestPackageScanner implements RouteyPackageScanner {

	@Override
	public Collection<String> addPackagesToScan() {
		return ImmutableList.of("net.ellwein.routey.itest.controllers");
	}

}
