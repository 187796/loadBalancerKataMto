package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krystian on 2016-06-15.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {

        for (Vm vm : vms) {
            addToLessLoadedServer(servers, vm);
        }

    }

    private void addToLessLoadedServer(Server[] servers, Vm vm) {
        List<Server> capableServers = new ArrayList<Server>();

        findCapableServers(servers, vm, capableServers);

        Server lessLoadedServer = findLessLoadedServer(capableServers);
        addVmToCapableServer(vm, lessLoadedServer);
    }

    private void addVmToCapableServer(Vm vm, Server lessLoadedServer) {
        if (lessLoadedServer != null) {
            lessLoadedServer.addVm(vm);
        }
    }

    private void findCapableServers(Server[] servers, Vm vm, List<Server> capableServers) {
        for (Server server : servers) {
            if (server.canFit(vm)) {
                capableServers.add(server);
            }
        }
    }

    private Server findLessLoadedServer(List<Server> servers) {
        Server lessLoadedServer = null;
        for (Server server : servers) {
            if (lessLoadedServer == null || server.getCurrentLoadPecentage() < lessLoadedServer.getCurrentLoadPecentage()) {
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
}
