package edu.iis.mto.serverloadbalancer;

/**
 * Created by Krystian on 2016-06-14.
 */
public class Server {

    public double currentLoadPercentage;
    private int capacity;

    public Server(int capacity) {

        this.capacity = capacity;
    }

    public boolean contain(Vm theVm) {
        return true;
    }
}
