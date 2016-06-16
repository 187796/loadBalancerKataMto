package edu.iis.mto.serverloadbalancer;

/**
 * Created by student on 2016-06-16.
 */
public class ServerBuilder {
    public ServerBuilder withCapacity(int capacity) {
        return this;
    }

    public Server build() {
        return new Server();
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }
}
