package edu.iis.mto.serverloadbalancer;

/**
 * Created by student on 2016-06-16.
 */
public class Server {
    public static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadOf;
    public int capacity;

    public Server(int capacity) {

        this.capacity = capacity;

    }

    public void addVm(Vm[] vm) {
        currentLoadOf = (double) vm[0].size / (double) capacity * MAXIMUM_LOAD;
    }
}
