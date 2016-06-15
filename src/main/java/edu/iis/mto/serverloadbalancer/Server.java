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
        currentLoadPercentage =(double) vmse.size / (double)capacity * MAXIMUM_LOAD;
        vms.add(vmse);
    }

    public int countVms() {
        return vms.size();
    }
}
