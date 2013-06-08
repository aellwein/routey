package net.ellwein.routey.example;

import net.ellwein.routey.annotations.Mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyController {

	private static final transient Logger LOGGER = LoggerFactory
			.getLogger(MyController.class);

	@Mapping
	public void index() {
		LOGGER.info("index()");
	}
}
