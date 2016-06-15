package edu.iis.mto.serverloadbalancer;

/**
 * Created by Krystian on 2016-06-14.
 */
public class ServerLoadBalancer {
    public static void balance(Server[] servers, Vm[] vmses) {

        for (Vm vm :vmses) {
            Server lessLoadedServer = null;
            for (Server server : servers) {
                if(lessLoadedServer == null || server.currentLoadPercentage < lessLoadedServer.currentLoadPercentage){
                    lessLoadedServer = server;
                }
            }

            lessLoadedServer.addVm(vm);
        }
    }
}