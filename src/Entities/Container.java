package Entities;

public class Container {
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

    public String getContainerType(){
        return this.containerType.getType();
    }

    public double getTruckConsumption(){
        return this.containerType.getTruckConsume();
    }

    public double getShipConsumption(){
        return this.containerType.getShipConsume();
    }
}
