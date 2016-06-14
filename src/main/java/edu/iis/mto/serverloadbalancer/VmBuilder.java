package edu.iis.mto.serverloadbalancer;

/**
 * Created by Krystian on 2016-06-14.
 */
public class VmBuilder {
    private int size;

    public VmBuilder ofSize(int size) {
        this.size = size;
        return new VmBuilder();
    }

    public Vm build() {
        return new Vm();
    }
}
