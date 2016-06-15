package edu.iis.mto.serverloadbalancer;


import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerCountVmMatcher.hasVmCountOf;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
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

	@Test
	public void balancingServerWithTenSlotCapacity_andOneSlotVm_fillsServerWithTenPercent(){
		Server server = a(server().witCapacity(10));
		Vm vm = a(vm().withSize(1));
		balancing(aServersListWith(server),aVmsListWith(vm));

		assertThat(server,hasCurrentLoadOf(10.0d));
		assertThat("server should contain vm",server.contain(vm));
	}

	@Test
	public void balancingServerWithEnoughRoom_getsFilledWithAllVms(){
		Server server = a(server().witCapacity(10));
		Vm vm1 = a(vm().withSize(1));
		Vm vm2 = a(vm().withSize(2));
		balancing(aServersListWith(server),aVmsListWith(vm1,vm2));

		assertThat(server,hasVmCountOf(2));
		assertThat("server should contain vm",server.contain(vm1));
		assertThat("server should have 2 vms",server.contain(vm2));

	}




	private Vm[] aVmsListWith(Vm... vms) {
		return vms;
	}

	private <T> T a(Builder<T> builder){
		return builder.build();
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



}
