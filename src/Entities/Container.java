package Entities;

public class Container {
    private String containerID;
    private double weight;

    public Container(String containerID, double weight) {
        this.containerID = containerID;
        this.weight = weight;
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
}
