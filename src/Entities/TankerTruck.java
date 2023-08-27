package Entities;
import Interface.IVehicle;
public class TankerTruck extends Vehicle implements IVehicle{

    public TankerTruck(String vehicleID, String name, double currentWeight, double currentFuel, double capacity, double fuelCapacity, Port port) {
        super(vehicleID, name,currentWeight, currentFuel, capacity, fuelCapacity, port);
    }
}
