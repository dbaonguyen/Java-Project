package Entities;

import Interface.IPort;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Port implements IPort {
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
    public SimpleDateFormat currentDate;
    private double usedFuel;
    public Port(String portID, String name, double latitude, double longtitude, double capacity, double currentWeight,boolean landingAbility ) {
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
        }
    }

    @Override
    public void removeContainer(Container container) {
        this.containers.remove(container);
        this.currentWeight -= container.getWeight();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {

    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);

    }

    @Override
    public void searchVehicleById(String id) {
        for (Vehicle vehicle : vehicles){
            if (vehicle.getVehicleID().equals(id)){
                System.out.println(vehicle);
            } else{
                System.out.println("Vehicle not found");
            }
        }
    }

    @Override
    public void searchVehicleByName(String name) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getName().equals(name)) {
                System.out.println(vehicle);
            } else {
                System.out.println("Vehicle not found");
            }
        }
    }

    @Override
    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    @Override
    public double totalWeight() {
        return 0;
    }

    @Override
    public void displayTrip(Date date) {

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

    public void addUsedFuel(double newFuel){
        if(currentDate == this.currentDate){
            this.usedFuel += newFuel;
        }
        else{
            this.currentDate = currentDate;
            this.usedFuel = newFuel;
        }
    }
}
