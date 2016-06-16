package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServer_noVms_serverStaysEmpty(){
		Server theServer = a(server().withCapacity(10));

		balancing(aServersListWith(theServer),anEmptyVmList());

		assertThat(theServer,hasCurrentLoadOf(0.00d));
	}

	private Matcher<? super Server> hasCurrentLoadOf(double expectedLoad) {
		return new CurrentServerLoadMatcher(expectedLoad);
	}

	private void balancing(Server[] servers, Vm[] vm) {
		new ServerLoadBalancer().balance(servers,vm);
	}



	private Vm[] anEmptyVmList() {
		return new Vm[0];
	}

	private Server[] aServersListWith(Server... servers) {
		return servers;
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}


}
