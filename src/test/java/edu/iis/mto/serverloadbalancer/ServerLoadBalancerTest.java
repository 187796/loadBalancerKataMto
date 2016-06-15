package edu.iis.mto.serverloadbalancer;


import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServer_noVms_serverStaysEmpty(){
		Server server = a(server().witCapacity(1));

		balancing(aServersListWith(server),anEmptyListOfVms());

		assertThat(server,hasCurrentLoadOf(0.0d));
	}

	private Matcher<? super Server> hasCurrentLoadOf(double expectedLoadPercentage) {
		return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers,vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aServersListWith(Server... servers) {
		return servers;
	}

	private Server a(ServerBuilder serverBuilder) {
		return serverBuilder.build();
	}

	private ServerBuilder server() {
		return new ServerBuilder();
	}
}
