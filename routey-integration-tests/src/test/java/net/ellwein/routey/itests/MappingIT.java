package net.ellwein.routey.itests;

import static com.eclipsesource.restfuse.Assert.*;

import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;

@RunWith(HttpJUnitRunner.class)
public class SimpleTest {

	@Rule
	public Destination destination = new Destination(this, "http://localhost:8080");

	@Context
	Response response;

	@HttpTest(method = Method.GET, path = "/testme")
	public void reachTestController() throws Exception {
		assertOk(response);
	}
}
