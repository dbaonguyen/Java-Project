package Entities;

import Interface.IPort;
import Users.PortManager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Port implements IPort, Serializable {
    private static final long serialVersionUID = 1030224541977093439L;
    private String portID;
    private String name;
    private double latitude;
    private double longtitude;
    private double currentWeight;
    private double capacity;
    private boolean landingAbility;
    private List<Container> containers = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Trip> trips = new ArrayList<>();
    public Date currentDate;
    private double usedFuel;
    public Port(String portID, String name, double latitude, double longtitude, double capacity, double currentWeight,boolean landingAbility) {
        this.portID = String.valueOf(portID);
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.capacity = capacity;
        this.landingAbility = landingAbility;
        this.currentWeight = currentWeight;
    }

    public String getPortID() {
        return portID;
    }

    public void setPortID(String portID) {
        this.portID = portID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public boolean isLandingAbility() {
        return landingAbility;
    }

    public void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    @Override
    public double calculateDistance(Port port) {
        double distance = Math.sqrt(Math.pow((port.longtitude - this.latitude),2) + Math.pow((port.latitude - this.latitude),2));
        return distance;
    }

    @Override
    public void addContainer(Container container) {
        if (container.getWeight() > this.capacity){
            System.out.println("The container is heavier than the port capacity!");
        } else if ((this.currentWeight + container.getWeight()) > this.capacity){
            System.out.println("The container is heavier than the port capacity!");
        } else{
            this.containers.add(container);
            this.currentWeight += container.getWeight();
            System.out.println("Container is added");
        }
    }

    @Override
    public void removeContainer(Container container) {
        this.containers.remove(container);
        this.currentWeight -= container.getWeight();
    }

    @Override
    public void searchContainer(String id) {

    }

    @Override
    public void updateContainer(Container container) {

    }

    @Override
    public void updatePort(Port port) {

    }

    @Override
    public void updateVehicle(Vehicle vehicle) {

    }

    @Override
    public void updateManagerInfo(PortManager portManager) {

    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        if (!this.isLandingAbility() && (vehicle instanceof Truck || vehicle instanceof ReeferTruck || vehicle instanceof TankerTruck)){
            System.out.println("Can not add this vehicle!");
        } else{
            this.vehicles.add(vehicle);
        }
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
    }

    @Override
    public void searchVehicleById(String id) {
        List<String> vehicleID = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
            vehicleID.add(vehicle.getVehicleID());
        }

        if (vehicleID.contains(id)) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getVehicleID().equals(id)){
                    System.out.println(vehicle);
                }
            }
        } else{
            System.out.println("Vehicle not found");
        }
    }

    @Override
    public void searchVehicleByName(String name) {
        List<String> vehicleName = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
            vehicleName.add(vehicle.getName());
        }

        if (vehicleName.contains(name)) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getName().equals(name)){
                    System.out.println(vehicle);
                }
            }
        } else{
            System.out.println("Vehicle not found");
        }
    }

    @Override
    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    @Override
    public void displayTrip(Date date) {
        for (Trip trip : trips){

        }
    }

    @Override
    public void displayTrip(Date date1, Date date2) {

    }

    @Override
    public void displayShips() {
        for (Vehicle vehicle : vehicles){
            if (vehicle instanceof Ship){
                System.out.println(vehicle);
            }
        }
    }

    public void displayTrucks() {
        for (Vehicle vehicle : vehicles){
            if (vehicle instanceof ReeferTruck || vehicle instanceof Truck || vehicle instanceof TankerTruck){
                System.out.println(vehicle);
            }
        }
    }

    @Override
    public void displayContainers() {
        for (Container container : containers){
            System.out.println(container);
        }
    }

    @Override
    public double totalFuel() {
        return usedFuel;
    }
    public void displayPortInfo(Port port){
        System.out.println(port);
    }
    public void addUsedFuel(double newFuel){
        if(currentDate == this.currentDate){
            this.usedFuel += newFuel;
        }
        else{
            this.currentDate = currentDate;
            this.usedFuel = newFuel;
        }
    }

    @Override
    public String toString() {
        return "Port Information:" +
                "\nPort ID: " + portID +
                "\nName: " + name +
                "\nLatitude: " + latitude +
                "\nLongtitude: " + longtitude +
                "\nCurrent Weight: " + currentWeight +
                "\nCapacity: " + capacity +
                "\nLanding Ability: " + landingAbility +
                "\nContainers: " + containers +
                "\nVehicles: " + vehicles +
                "\nTrips: " + trips +
                "\nCurrent Date: " + currentDate +
                "\nUsed Fuel: " + usedFuel;
    }

}
