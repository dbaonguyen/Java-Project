package Entities;

import java.io.Serializable;

public class ReeferTruck extends Vehicle implements Serializable {
    private static final long serialVersionUID = -4126447521088482559L;

    public ReeferTruck(String vehicleID, String name, double capacity, double fuelCapacity, Port port,boolean status) {
        super(vehicleID, name, capacity, fuelCapacity, port, status);
    }

    public void truckLoadContainer(Container container){
        if(container.getType().getType().equals("Refrigerated")){
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
