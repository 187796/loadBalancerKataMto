package edu.iis.mto.serverloadbalancer;

/**
 * Created by student on 2016-06-16.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vm) {
        if(vm.length > 0){
            servers[0].currentLoadOf = (double) vm[0].size / (double) servers[0].capacity * 100.0d;
        }
    }
}
