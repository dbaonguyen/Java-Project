package Entities;

public class Ship extends Vehicle{
    public Ship(String vehicleID, String name, double currentWeight, double currentFuel, double capacity, double fuelCapacity, Port port) {
        super(vehicleID, name,currentWeight, currentFuel, capacity, fuelCapacity, port);
    }
}
