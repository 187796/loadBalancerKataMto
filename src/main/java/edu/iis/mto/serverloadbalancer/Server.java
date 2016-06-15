package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krystian on 2016-06-15.
 */
public class Server {
    public static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadPercentage;
    public int capacity;

    List<Vm> vms = new ArrayList<Vm>();

    public Server(int capacity) {

        this.capacity = capacity;
    }

    public boolean contain(Vm vm) {
        return true;
    }

    public void addVm(Vm vm) {
        currentLoadPercentage = (double) vm.size /(double) this.capacity * MAXIMUM_LOAD;
        vms.add(vm);
    }

    public int countVm() {
        return vms.size();
    }
}
