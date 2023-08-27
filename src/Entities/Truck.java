package Entities;
import java.util.*;
import Interface.IVehicle;
public class Truck extends Vehicle implements IVehicle{
    ArrayList<Type> type;
    public Truck(String vehicleID, String name, double currentWeight, double currentFuel, double capacity, double fuelCapacity, Port port, ArrayList<Type> type) {
        super(vehicleID, name,currentWeight, currentFuel, capacity, fuelCapacity, port);
        this.type = new ArrayList<Type>();
        type.add(dryStorage);
        type.add(openTop);
        type.add(openSide);
    }
}