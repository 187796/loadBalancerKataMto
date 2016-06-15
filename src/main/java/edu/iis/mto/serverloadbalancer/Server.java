package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krystian on 2016-06-14.
 */
public class Server {

    public static final double MAXIMUM_LOAD = 100.0d;
    private double currentLoadPercentage;
    private int capacity;

    private List<Vm> vms = new ArrayList<Vm>();


    public Server(int capacity) {
        this.setCapacity(capacity);
    }

    public boolean contain(Vm theVm) {
       return vms.contains(theVm);
    }

    public void addVm(Vm vmse) {
        setCurrentLoadPercentage(getCurrentLoadPercentage() + loadOfVm(vmse));
        vms.add(vmse);
    }

    private double loadOfVm(Vm vmse) {
        return (double) vmse.getSize() / (double) getCapacity() * MAXIMUM_LOAD;
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return getCurrentLoadPercentage() + loadOfVm(vm) <= MAXIMUM_LOAD;
    }

    public double getCurrentLoadPercentage() {
        return currentLoadPercentage;
    }

    public void setCurrentLoadPercentage(double currentLoadPercentage) {
        this.currentLoadPercentage = currentLoadPercentage;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
