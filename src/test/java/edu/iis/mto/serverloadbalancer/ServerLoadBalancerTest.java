package edu.iis.mto.serverloadbalancer;


import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.hasVmsCountOf;
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


    @Test
    public void balancingServerWithEnoughRoom_getsFilledWithAllVms(){
        Server theServer = a(server().withCapacity(10));
        Vm theFirtstVm = a(vm().ofSize(1));
        Vm theSecondVm = a(vm().ofSize(1));
        balancing(aServersListWith(theServer),aVmsListWith(theFirtstVm,theSecondVm));

        assertThat(theServer,hasVmsCountOf(2));
        assertThat("server should contain the first vm",theServer.contain(theFirtstVm));
        assertThat("server should contain the second vm",theServer.contain(theSecondVm));
    
    }



    @Test
    public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillsServerWithTenPercent(){

        Server theServer = a(server().withCapacity(10));
        Vm theVm = a(vm().ofSize(1));
        balancing(aServersListWith(theServer),aVmsListWith(theVm));

        assertThat(theServer,hasCurrentLoadOf(10.00d));
        assertThat("server should contain the vm",theServer.contain(theVm));
    }

    @Test
    public void aVms_shouldBeBalanced_onLessLoadedServerFirts(){
        Server moreLoadedServer = a(server().withCapacity(100).withCurrentLoadOf(50.0d));
        Server lessLoadedServer = a(server().withCapacity(100).withCurrentLoadOf(45.0d));


        Vm theVm = a(vm().ofSize(10));


        balancing(aServersListWith(moreLoadedServer,lessLoadedServer),aVmsListWith(theVm));

        assertThat("less loaded server should contain the vm",lessLoadedServer.contain(theVm));
        assertThat("more loaded server should not contain the vm",!moreLoadedServer.contain(theVm));

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
