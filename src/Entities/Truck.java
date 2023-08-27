package Entities;
import java.util.*;

public class Truck extends Vehicle {

    public Truck(String vehicleID, String name, double currentWeight, double currentFuel, double capacity, double fuelCapacity, Port port, ArrayList<Type> type) {
        super(vehicleID, name,currentWeight, currentFuel, capacity, fuelCapacity, port);
        this.type = new ArrayList<>();
        type.add(dryStorage);
        type.add(openTop);
        type.add(openSide);
    }
}