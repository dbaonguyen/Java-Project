package Entities;

import Interface.IVehicle;
import Source.AdminInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vehicle implements IVehicle, Serializable {
    private String vehicleID;
    private String name;
    private double currentWeight;
    private double currentFuel;
    private double capacity;
    private double fuelCapacity;
    private Port port;
    private boolean status;
    private ArrayList<Container> containers = new ArrayList<>();
    private double fuelConsumption;

    public Vehicle(){
        vehicleID = "tr-0000";
        name = "default";
        currentWeight = 0;
        currentFuel = 0;
        capacity = 0;
        fuelCapacity = 0;
        port = null;
        fuelConsumption = 0;
        status = true;
    }

    public Vehicle(String vehicleID, String name, double capacity, double fuelCapacity, Port port, boolean status) {
        this.vehicleID = vehicleID;
        this.name = name;
        this.currentWeight = 0;
        this.currentFuel = fuelCapacity;
        this.capacity = capacity;
        this.fuelCapacity = fuelCapacity;
        this.port = port;
        this.status = status;
        port.addVehicle(this);
    }
    @Override
    public boolean canMoveToPort(Port port) {
        double fuelNeeded = this.fuelConsumption * this.port.calculateDistance(port);
        //calculate fuel needs
        if ((this instanceof Truck || this instanceof ReeferTruck || this instanceof TankerTruck) && !port.isLandingAbility()) {
            System.out.println("This vehicle cannot move to this port due to the port's landing ability!");
            return false;
        } else if (fuelNeeded > this.currentFuel) {
            System.out.println("The vehicle doesn't have enough fuel!");
            return false;
        }
        return true;
    }

    @Override
    public boolean loadContainer(Container container) {
        if (container.getWeight() <= this.capacity - this.currentWeight && this.port != null) {
            containers.add(container);
            this.port.removeContainer(container);
            this.port.setCurrentWeight(this.port.getCurrentWeight() - container.getWeight());
            this.setCurrentWeight(this.currentWeight + container.getWeight());
            //change for truck
            this.fuelConsumption += container.getShipConsumption();
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public boolean unloadContainer(Container container) {
        if(this.port != null && container.getWeight() <= this.port.getCapacity() - this.port.getCurrentWeight()){
            this.port.addContainer(container);
            this.port.setCurrentWeight(this.port.getCurrentWeight() + container.getWeight());
            this.setCurrentWeight(this.currentWeight + container.getWeight());
            this.containers.remove(container);
            //change for truck
            this.fuelConsumption += container.getShipConsumption();
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void moveToPort(Port port) {
    }



    @Override
    public Trip moveToPort1(Port port, Date departureDate, Date arrivalDate) {
        //change port to null
        Trip trip = new Trip(this, this.port, departureDate, arrivalDate, port, false);
        //remove vehicle from old port
        this.port.removeVehicle(this);
        //make a new trip variable(status = false)
        this.port.addTrip(trip);
        AdminInterface.tripList.add(trip);
        this.setPort(null);
        return trip;
    }


    @Override
    public void hasArrived(Trip trip){
        this.status = true;
        //update status in trip
        trip.setStatus(true);
        //add this vehicle to the new port
        this.port = trip.getArriveTo();
        trip.getArriveTo().addVehicle(this);
        this.currentFuel -= Math.round(this.fuelConsumption * this.port.calculateDistance(trip.getDepartFrom()));
    }

    @Override
    public void refuel() {
            this.port.addUsedFuel(this.fuelCapacity - this.currentFuel);
            this.setCurrentFuel(this.fuelCapacity);
    }

    @Override
    public String toString() {
        List<String> containerIDs = new ArrayList<>();
        for (Container container : containers) {
            containerIDs.add(container.getContainerID());
        }
        return "Vehicle Information:" +
                "\nVehicle ID: " + vehicleID +
                "\nName: " + name +
                "\nCurrent Fuel: " + currentFuel +
                "\nCapacity: " + capacity +
                "\nFuel Capacity: " + fuelCapacity +
                "\nPort: " + port.getName() +
                "\nContainers: " + containerIDs;

    }

    public String getVehicleID() {
        return this.vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentWeight(){ return this.currentWeight;}

    public void setCurrentWeight(double newWeight){
        this.currentWeight = newWeight;
    }
    public double getCurrentFuel() {
        return this.currentFuel;
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

    public ArrayList<Container> getContainers(){
        return containers;
    }

    public String getStatus() {
        if (!status) {
            return "unavailable";
        }
        return "available";
    }
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public void containerIDs() {
        for (Container container: containers) {
            System.out.print(container.getContainerID() + "\t");
        }
    }
}
