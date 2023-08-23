package Entities;

import Interface.IPort;

import java.util.ArrayList;
import java.util.List;

public class Port implements IPort {
    private int portID;
    private String name;
    private double latitude;
    private double longtitude;
    private int capacity;
    private boolean landingAbility;
    private List<Container> containers = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();

    public Port(int portID, String name, double latitude, double longtitude, int capacity, boolean landingAbility) {
        this.portID = portID;
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.capacity = capacity;
        this.landingAbility = landingAbility;
    }

    @Override
    public double calculateDistance(Port port) {
        final double RADIUS_OF_EARTH = 6371;

        double dlat = port.latitude - this.latitude;
        double dlon = port.longtitude - this.longtitude;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(this.latitude) * Math.cos(port.latitude) *
                        Math.sin(dlon / 2) * Math.sin(dlon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = RADIUS_OF_EARTH * c;

        return distance;
    }

    @Override
    public void addContainer(Container container) {

    }

    @Override
    public void removeContainer(Container container) {

    }

    @Override
    public void addContainer(java.awt.Container container) {

    }

    @Override
    public void removeContainer(java.awt.Container container) {

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

    }

    @Override
    public void addTrip(Trip trip) {

    }
}
