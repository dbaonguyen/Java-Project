package Entities;
import Interface.IVehicle;
public class Ship extends Vehicle implements IVehicle{
    public Ship(String vehicleID, String name, double currentWeight, double currentFuel, double capacity, double fuelCapacity, Port port) {
        super(vehicleID, name,currentWeight, currentFuel, capacity, fuelCapacity, port);
    }


}
