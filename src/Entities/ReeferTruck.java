package Entities;

public class ReeferTruck extends Vehicle{

    public ReeferTruck(String vehicleID, String name, double currentWeight, double currentFuel, double capacity, double fuelCapacity, Port port) {
        super(vehicleID, name,currentWeight, currentFuel, capacity, fuelCapacity, port);
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
        if (!port.isLandingAbility()) {
            return false;
        }
        else{
            return super.canMoveToPort(port);
        }
    }
}
