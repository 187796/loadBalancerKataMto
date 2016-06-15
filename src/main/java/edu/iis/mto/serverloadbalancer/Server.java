package edu.iis.mto.serverloadbalancer;

/**
 * Created by Krystian on 2016-06-15.
 */
public class Server {
    public double currentLoadPercentage;
    public int capacity;

    public Server(int capacity) {

        this.capacity = capacity;
    }

    public boolean contain(Vm vm) {
        return true;
    }
}
