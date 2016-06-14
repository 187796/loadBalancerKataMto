package edu.iis.mto.serverloadbalancer;


import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}


	@Test
	public void balancingServer_noVm_ServerStaysEmtpy(){
		Server theServer = a(server().withCapacity(1));

		balancing(aServersListWith(theServer),anEmptyListOfVms());

		assertThat(theServer,hasCurrentLoadOf(0.00d));
	}


	private void balancing(Server[] servers, Vms[] vmses) {
		 ServerLoadBalancer.balance(servers,vmses);
	}



	private Vms[] anEmptyListOfVms() {
		return new Vms[0];
	}

	private Server[] aServersListWith(Server... servers) {
		return servers;
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}
}
