package edu.iis.mto.serverloadbalancer;

/**
 * Created by Krystian on 2016-06-14.
 */
public class ServerLoadBalancer {
    public static void balance(Server[] servers, Vm[] vmses) {
        if (vmses.length > 0) {
            servers[0].currentLoadPercentage =(double) vmses[0].size / (double)servers[0].capacity * 100.0d;
        }
    }
}