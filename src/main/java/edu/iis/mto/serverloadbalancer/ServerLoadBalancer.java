package edu.iis.mto.serverloadbalancer;

/**
 * Created by student on 2016-06-16.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vm) {

        for (Vm vm1 : vm) {
            Server lessLoadedServer = null;
            for (Server server : servers) {
                if(lessLoadedServer ==null || server.currentLoadPecentage < lessLoadedServer.currentLoadPecentage){
                    lessLoadedServer = server;
                }
            }

            lessLoadedServer.addVm(vm1);
        }

    }
}
