package Entities;
import java.io.Serializable;

public class Ship extends Vehicle implements Serializable {
    public Ship(String vehicleID, String name, double currentWeight, double currentFuel, double capacity, double fuelCapacity, Port port) {
        super(vehicleID, name,currentWeight, currentFuel, capacity, fuelCapacity, port);
    }

    //load method for ships
    public void shipLoadContainer(Container container){
        super.loadContainer(container);
        //only if loading is possible, the fuel consumption will be updated
        if(super.loadContainer(container)){
            this.setFuelConsumption(getFuelConsumption() + container.getShipConsumption());
        }
    }
    //unload method for ships
    public void shipUnloadContainer(Container container){
        super.loadContainer(container);
        //only if unloading is possible, the fuel consumption will be updated
        if(super.loadContainer(container)){
            this.setFuelConsumption(getFuelConsumption() - container.getShipConsumption());
        }
    }
}
