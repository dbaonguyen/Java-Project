package Entities;

public class TankerTruck extends Vehicle {

    public TankerTruck(int vehicleID, String name, double currentFuel, int capacity, double fuelCapacity, Port port, int totalContainers, int totalContainersByType) {
        super(vehicleID, name, currentFuel, capacity, fuelCapacity, port, totalContainers, totalContainersByType);
    }
}
