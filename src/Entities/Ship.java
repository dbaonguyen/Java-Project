package Entities;
import java.io.Serializable;

public class Ship extends Vehicle implements Serializable {
    public Ship(String vehicleID, String name, double capacity, double fuelCapacity, Port port, boolean status) {
        super(vehicleID, name, capacity, fuelCapacity, port, status);
    }

}
