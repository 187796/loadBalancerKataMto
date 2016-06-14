package edu.iis.mto.serverloadbalancer;

/**
 * Created by Krystian on 2016-06-14.
 */
public class ServerBuilder {
    private int capacity;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return new ServerBuilder();
    }

    public Server build() {
        return new Server();
    }




    public static ServerBuilder server() {

        return null;

    }
}