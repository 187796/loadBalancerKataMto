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
	public void balancingServer_noVms_serverStaysEmpty(){
		Server server = a(server().witCapacity(1));

		balancing(aServersListWith(server),anEmptyListOfVms());

		assertThat(server,hasCurrentLoadOf(0.0d));
	}

	@Test
	public void balancingAServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithVm(){
		Server server = a(server().witCapacity(1));
		Vm vm = a(vm().withSize(1));
		balancing(aServersListWith(server),aVmsListWith(vm));

		assertThat(server,hasCurrentLoadOf(100.0d));
		assertThat("server should contain vm",server.contain(vm));
	}

	private Vm a(VmBuilder vmBuilder) {
		return vmBuilder.build();
	}

	private Vm[] aVmsListWith(Vm... vms) {
		return vms;
	}

	private VmBuilder vm() {
		return new VmBuilder();
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


}
