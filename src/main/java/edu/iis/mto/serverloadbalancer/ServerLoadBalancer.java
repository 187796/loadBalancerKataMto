package edu.iis.mto.serverloadbalancer;

/**
 * Created by Krystian on 2016-06-14.
 */
public class ServerLoadBalancer {
    public static void balance(Server[] servers, Vm[] vmses) {
        if (vmses.length > 0) {
            servers[0].addVm(vmses[0]);
        }
    }
}