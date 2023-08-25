package Entities;

public class TankerTruck extends Vehicle {

    public TankerTruck(String vehicleID, String name, double currentFuel, double capacity, double fuelCapacity, Port port, int totalContainers, int totalContainersByType) {
        super(vehicleID, name, currentFuel, capacity, fuelCapacity, port, totalContainers, totalContainersByType);
    }
}
