package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krystian on 2016-06-14.
 */
public class Server {

    public static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadPercentage;
    public int capacity;

    private List<Vm> vms = new ArrayList<Vm>();


    public Server(int capacity) {
        this.capacity = capacity;
    }

    public boolean contain(Vm theVm) {
       return vms.contains(theVm);
    }

    public void addVm(Vm vmse) {
        currentLoadPercentage = loadOfVm(vmse);
        vms.add(vmse);
    }

    private double loadOfVm(Vm vmse) {
        return (double) vmse.size / (double)capacity * MAXIMUM_LOAD;
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return currentLoadPercentage + loadOfVm(vm) <= MAXIMUM_LOAD;
    }
}
