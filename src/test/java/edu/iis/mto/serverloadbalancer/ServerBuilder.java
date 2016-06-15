package edu.iis.mto.serverloadbalancer;

/**
 * Created by Krystian on 2016-06-15.
 */
public class ServerBuilder implements Builder<Server> {
    private int capacity;

    public ServerBuilder witCapacity(int capacity) {

        this.capacity = capacity;
        return this;
    }

    public Server build() {
        return new Server(capacity);
    }
    public static ServerBuilder server() {
        return new ServerBuilder();
    }
}
