package Entities;

public class TankerTruck extends Vehicle{

    public TankerTruck(String vehicleID, String name, double currentWeight, double currentFuel, double capacity, double fuelCapacity, Port port) {
        super(vehicleID, name,currentWeight, currentFuel, capacity, fuelCapacity, port);
    }

    public void truckLoadContainer(Container container){
        if(container.getType().getType().equals("Liquid")){
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
}
