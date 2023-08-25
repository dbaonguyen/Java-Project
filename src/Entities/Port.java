package Entities;

import Interface.IPort;

import java.util.ArrayList;
import java.util.List;

public class Port implements IPort {
    private String portID;
    private String name;
    private double latitude;
    private double longtitude;
    private int capacity;
    private boolean landingAbility;
    private List<Container> containers = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Trip> trips = new ArrayList<>();

    public Port(String portID, String name, double latitude, double longtitude, int capacity, boolean landingAbility) {
        this.portID = String.valueOf(portID);
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.capacity = capacity;
        this.landingAbility = landingAbility;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isLandingAbility() {
        return landingAbility;
    }

    public void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }

    @Override
    public double calculateDistance(Port port) {
        double distance = Math.sqrt(Math.pow((port.longtitude - this.latitude),2) + Math.pow((port.latitude - this.latitude),2));
        return distance;
    }

    @Override
    public void addContainer(Container container) {

    }

    @Override
    public void removeContainer(Container container) {

    }

    @Override
    public void addVehicle(Vehicle vehicle) {

    }

    @Override
    public void removeVehicle(Vehicle vehicle) {

    }

    @Override
    public void searchVehicleById(String id) {

    }

    @Override
    public void searchVehicle(String vehicleIdentifier) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getName().equals(vehicleIdentifier)) {
                System.out.println(vehicle);
            } else {
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
}
