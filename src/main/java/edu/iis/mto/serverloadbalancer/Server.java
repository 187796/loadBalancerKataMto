package edu.iis.mto.serverloadbalancer;

/**
 * Created by Krystian on 2016-06-15.
 */
public class Server {
    private int capacity;
    public double currentLoadPercentage;

    public Server(int capacity) {

        this.capacity = capacity;
    }
}
