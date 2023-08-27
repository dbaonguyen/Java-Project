package Entities;

import Interface.IVehicle;

import java.util.ArrayList;
import java.util.List;

public class Vehicle implements IVehicle {
    private String vehicleID;
    private String name;
    private double currentWeight;
    private double currentFuel;
    private double capacity;
    private double fuelCapacity;
    private Port port;
    private List<Container> containers = new ArrayList<>();

        public Vehicle(String vehicleID, String name, double currentWeight, double currentFuel, double capacity, double fuelCapacity, Port port) {
        this.vehicleID = vehicleID;
        this.name = name;
        this.currentWeight = currentWeight;
        this.currentFuel = currentFuel;
        this.capacity = capacity;
        this.fuelCapacity = fuelCapacity;
        this.port = port;
    }


    @Override
    public boolean canMoveToPort(Port port) {
        //boolean to check ability to move
        boolean canLand = true;
        boolean enoughWeight = true;
        boolean enoughFuel = true;
        //list to store the unmet requirements
        ArrayList<String> violatedRequirements = new ArrayList<>();
        //calculate current weight of vehicle
        double vehicleCurrentWeight = 0;
        for (Container cont: this.containers) {
            vehicleCurrentWeight += cont.getWeight();
        }
        //calculate current fuel needs

        //calculate fuel needs

        //check conditions
        if (!port.isLandingAbility()) {
            canLand = false;
            violatedRequirements.add("Invalid landing ability!");
        }
        if (port.getCurrentWeight() + vehicleCurrentWeight > port.getCapacity()) {
            enoughWeight = false;
            violatedRequirements.add("The weight on the vehicle exceed the capacity of the destined port!");
        }

        return true;
    }

    @Override
    public void loadContainer(Container container) {
        containers.add(container);
    }

    @Override
    public void unloadContainer(Container container) {
        containers.remove(container);
    }

    @Override
    public void moveToPort(Port port) {
        //check value of canMoveToPort()
        if(this.canMoveToPort(port) == false){
            System.out.println("The vehicle can not go to this port!");
        }
        else{

            //make a new trip variable(arrivalDate = null)
            this.setPort(null);
            //change port to null
            port.removeVehicle(this);
            //remove vehicle from old port
        }




    }

    @Override
    public void hasArrived(){
            //update arrivalDate in trip
        this.port = port;
        //add this vehicle to the new port
    }
    @Override
    public void refuel() {
        if(this.port == null){
            System.out.println("The vehicle is travelling, can not refuel!");
        }
        else{
            this.port.addUsedFuel(this.fuelCapacity - this.currentFuel);
            this.currentFuel = this.fuelCapacity;
        }
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID=" + vehicleID +
                ", name='" + name + '\'' +
                ", currentFuel=" + currentFuel +
                ", capacity=" + capacity +
                ", fuelCapacity=" + fuelCapacity +
                ", port=" + port +
                ", containers=" + containers +
                '}';
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentWeight(){ return this.currentWeight;};

    public void setCurrentWeight(double newWeight){
        this.currentWeight = newWeight;
    }
    public double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

}
