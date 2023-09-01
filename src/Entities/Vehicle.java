package Entities;

import Interface.IVehicle;

import java.io.Serializable;
import java.util.ArrayList;

public class Vehicle implements IVehicle, Serializable {
    private String vehicleID;
    private String name;
    private double currentWeight;
    private double currentFuel;
    private double capacity;
    private double fuelCapacity;
    private Port port;
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
    }

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
        //calculate fuel needs
        double fuelNeeded = this.fuelConsumption * this.port.calculateDistance(port);
        if(fuelNeeded > this.fuelCapacity){
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
            System.out.println("This container can not be loaded on this vehicle!");
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
            System.out.println("This vehicle can not unload this container now!");
            return false;
        }
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
    public void hasArrived(Port port){

        //update arrivalDate in trip
        this.port = port;
        port.addVehicle(this);
        this.currentFuel -= this.fuelConsumption * this.port.calculateDistance(port);
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

    public double getCurrentWeight(){ return this.currentWeight;};

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

    public double getFuelConsumption(){
        return fuelConsumption;
    }

    public void setFuelConsumption(double newConsumption){
        this.fuelConsumption = newConsumption;
    }

}
