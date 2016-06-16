package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2016-06-16.
 */
public class Server {
    public static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadOf;
    public int capacity;
    private List<Vm> vms = new ArrayList<Vm>();

    public Server(int capacity) {

        this.capacity = capacity;
    }

    public void addVm(Vm vm) {
        currentLoadOf = (double) vm.size / (double) capacity * MAXIMUM_LOAD;
        vms.add(vm);
    }

    public int vmCount() {
        return vms.size();
    }
}
