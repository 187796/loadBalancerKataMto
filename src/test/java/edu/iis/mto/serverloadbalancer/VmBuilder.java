package edu.iis.mto.serverloadbalancer;

/**
 * Created by student on 2016-06-16.
 */
public class VmBuilder implements Builder<Vm> {
    public VmBuilder withSize(int size) {
        return this;
    }

    public Vm build() {
        return new Vm();
    }

    public static VmBuilder vm() {
        return new VmBuilder();
    }
}
