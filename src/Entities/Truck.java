package Entities;
import java.util.*;
import Interface.IVehicle;
public class Truck extends Vehicle implements IVehicle{
    ArrayList<Type> type;

    public Truck(String vehicleID, String name, double currentWeight, double currentFuel, double capacity, double fuelCapacity, Port port) {
        super(vehicleID, name,currentWeight, currentFuel, capacity, fuelCapacity, port);

    }

    @Override
    public boolean canMoveToPort(Port port){
        if()
    }
}