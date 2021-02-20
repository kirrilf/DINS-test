package org.kirrif.model;

public final class Views {
    public interface BasicCar{}
    public interface AdvancedCar extends BasicCar{}
    public interface FullCar extends AdvancedCar {}
}
