package Entities;

import Interface.IVehicle;

import java.util.ArrayList;
import java.util.List;

public class Vehicle implements IVehicle {
    private String vehicleID;
    private String name;
    private double currentFuel;
    private int capacity;
    private double fuelCapacity;
    private Port port;
    private int totalContainers;
    private int totalContainersByType;
    private List<Container> containers = new ArrayList<>();

    public Vehicle(int vehicleID, String name, double currentFuel, int capacity, double fuelCapacity, Port port, int totalContainers, int totalContainersByType) {
        this.vehicleID = String.valueOf(vehicleID);
        this.name = name;
        this.currentFuel = currentFuel;
        this.capacity = capacity;
        this.fuelCapacity = fuelCapacity;
        this.port = port;
        this.totalContainers = totalContainers;
        this.totalContainersByType = totalContainersByType;
    }


    @Override
    public boolean canMovetoPort(Port port) {
        return false;
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
        this.port = port;
    }

    @Override
    public void refuel() {
        currentFuel = currentFuel + (fuelCapacity - currentFuel);
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
                ", totalContainers=" + totalContainers +
                ", totalContainersByType=" + totalContainersByType +
                ", containers=" + containers +
                '}';
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = String.valueOf(vehicleID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public int getCapacity() {
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

    public int getTotalContainers() {
        return totalContainers;
    }

    public void setTotalContainers(int totalContainers) {
        this.totalContainers = totalContainers;
    }
}
