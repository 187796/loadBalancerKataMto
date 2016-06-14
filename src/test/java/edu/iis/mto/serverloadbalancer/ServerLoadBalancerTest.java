package edu.iis.mto.serverloadbalancer;


import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
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

    @Test
    public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsServerWithVm(){
        Server theServer = a(server().withCapacity(1));
        Vm theVm = a(vm().ofSize(1));
        balancing(aServersListWith(theServer),aVmsListWith(theVm));

        assertThat(theServer,hasCurrentLoadOf(100.00d));
        assertThat("server should contain the vm",theServer.contain(theVm));
    }

    private Vm[] aVmsListWith(Vm... vms) {
        return vms;
    }






    private void balancing(Server[] servers, Vm[] vmses) {
		 ServerLoadBalancer.balance(servers,vmses);
	}



	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aServersListWith(Server... servers) {
		return servers;
	}

    private <T> T a(Builder<T> builder){
        return builder.build();
    }

}
