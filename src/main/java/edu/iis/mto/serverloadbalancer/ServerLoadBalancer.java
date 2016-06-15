package edu.iis.mto.serverloadbalancer;

/**
 * Created by Krystian on 2016-06-14.
 */
public class ServerLoadBalancer {
    public static void balance(Server[] servers, Vm[] vmses) {

        for (Vm vm :vmses) {
            addToLessLoadedServer(servers, vm);
        }
    }

    private static void addToLessLoadedServer(Server[] servers, Vm vm) {
        Server lessLoadedServer = findLessLoadedServer(servers);

        lessLoadedServer.addVm(vm);
    }

    private static Server findLessLoadedServer(Server[] servers) {
        Server lessLoadedServer = null;
        for (Server server : servers) {
            if(lessLoadedServer == null || server.currentLoadPercentage < lessLoadedServer.currentLoadPercentage){
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
}