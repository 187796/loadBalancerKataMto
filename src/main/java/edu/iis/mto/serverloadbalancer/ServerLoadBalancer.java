package edu.iis.mto.serverloadbalancer;

/**
 * Created by student on 2016-06-16.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vm) {

        for (Vm vm1 : vm) {
            addToLessLoadedServer(servers, vm1);
        }

    }

    private void addToLessLoadedServer(Server[] servers, Vm vm1) {
        Server lessLoadedServer = findLessLoadedServer(servers);

        lessLoadedServer.addVm(vm1);
    }

    private Server findLessLoadedServer(Server[] servers) {
        Server lessLoadedServer = null;
        for (Server server : servers) {
            if(lessLoadedServer ==null || server.currentLoadPecentage < lessLoadedServer.currentLoadPecentage){
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
}
