package edu.iis.mto.serverloadbalancer;

/**
 * Created by Krystian on 2016-06-14.
 */
public class Server {

    public static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadPercentage;
    public int capacity;


    public Server(int capacity) {
        this.capacity = capacity;
    }

    public boolean contain(Vm theVm) {
        return true;
    }

    public void addVm(Vm vmse) {
        currentLoadPercentage =(double) vmse.size / (double)capacity * MAXIMUM_LOAD;
    }
}
