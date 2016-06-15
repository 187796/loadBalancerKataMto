package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

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
        List<Server> capableServers = new ArrayList<Server>();
        for (Server server : servers) {
            if(server.canFit(vm)){
                capableServers.add(server);
            }
        }

        Server lessLoadedServer = findLessLoadedServer(capableServers);
        if(lessLoadedServer !=null) {
            lessLoadedServer.addVm(vm);
        }
    }

    private static Server findLessLoadedServer(List<Server> servers) {
        Server lessLoadedServer = null;
        for (Server server : servers) {
            if(lessLoadedServer == null || server.currentLoadPercentage < lessLoadedServer.currentLoadPercentage){
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
}