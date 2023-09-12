package Entities;

import java.io.Serializable;

public class Container implements Serializable {
    private String containerID;
    private double weight;
    private Type containerType;
    public Container(String containerID, double weight, Type containerType) {
        this.containerID = containerID;
        this.weight = weight;
        this.containerType = containerType;
    }

    public String getContainerID() {
        return containerID;
    }

    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Type getType(){
        return this.containerType;
    };

    public double getTruckConsumption(){
        return this.containerType.getTruckConsume();
    }

    public double getShipConsumption(){
        return this.containerType.getShipConsume();
    }

    @Override
    public String toString() {
        return "Container Information:" +
                "\nContainer ID: " + containerID +
                "\nWeight: " + weight + " kg" +
                "\nContainer Type: " + containerType.getType();
    }
}
