package Entities;

public class Truck extends Vehicle {

    public Truck(int vehicleID, String name, double currentFuel, int capacity, double fuelCapacity, Port port, int totalContainers, int totalContainersByType) {
        super(vehicleID, name, currentFuel, capacity, fuelCapacity, port, totalContainers, totalContainersByType);
    }
}
