package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
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

	@Test
	public void balancingServerWithOneSlotCapacity_andOneSlotVm_fillsServerWithVm(){
		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().withSize(10));

		balancing(aServersListWith(theServer),aVmListWith(theVm));

		assertThat(theServer,hasCurrentLoadOf(100.00d));
	}



	private Vm[] aVmListWith(Vm... vms) {
		return vms;
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



	private <T> T a(Builder<T> builder){
		return builder.build();
	}


}
