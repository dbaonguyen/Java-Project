package Entities;
import java.io.Serializable;
import java.util.*;

public class Truck extends Vehicle implements Serializable {


    public Truck(String vehicleID, String name, double currentWeight, double currentFuel, double capacity, double fuelCapacity, Port port) {
        super(vehicleID, name,currentWeight, currentFuel, capacity, fuelCapacity, port);

    }

    //load method for trucks
    public void truckLoadContainer(Container container){
        if(container.getType().getType().equals("Dry storage") || container.getType().getType().equals("Open top") || container.getType().getType().equals("Open side")){
            super.loadContainer(container);
            //only if loading is possible, the fuel consumption will be updated
            if(super.loadContainer(container)){
                this.setFuelConsumption(getFuelConsumption() + container.getTruckConsumption());
            }
        }
        else{
            System.out.println("This container type can not be loaded onto this vehicle!");
        }
    }
    //unload method for trucks
    public void truckUnloadContainer(Container container){
        super.loadContainer(container);
        //only if unloading is possible, the fuel consumption will be updated
        if(super.loadContainer(container)){
            this.setFuelConsumption(getFuelConsumption() - container.getTruckConsumption());
        }
    }

    @Override
    public boolean canMoveToPort(Port port){
        //check port condition
        if (!this.getPort().isLandingAbility()) {
            return false;
        }
        else{
            return super.canMoveToPort(port);
        }
    }

    @Override
    public void moveToPort(Port port){
        if(!this.canMoveToPort(port)){
            System.out.println("The vehicle can not go to this port!");
        }
        else{
            super.moveToPort(port);
        }
    }
}