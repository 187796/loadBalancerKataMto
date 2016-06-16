package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentServerLoadMatcher.hasCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerVmLoadMatcher.hasCountOf;
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

	@Test
	public void balancingServerWithTenSlotCapacity_andOneSlotVm_fillsTheServerWithTenPercent(){
		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().withSize(1));

		balancing(aServersListWith(theServer),aVmListWith(theVm));

		assertThat(theServer,hasCurrentLoadOf(10.0d));
	}

	@Test
	public void balancingServerWithEnoughRoom_fillsServerWithAllVms(){
		Server theServer = a(server().withCapacity(10));
		Vm thefirstVm = a(vm().withSize(1));
		Vm theSecondVm = a(vm().withSize(1));

		balancing(aServersListWith(theServer),aVmListWith(thefirstVm,theSecondVm));

		assertThat(theServer, hasCountOf(2));
		assertThat("server should contain theFirstVm",server().contains(thefirstVm));
		assertThat("server should contain theSecondVm",server().contains(theSecondVm));

	}




	private Vm[] aVmListWith(Vm... vms) {
		return vms;
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
