package Entities;

import java.io.Serial;
import java.io.Serializable;

public class ReeferTruck extends Vehicle implements Serializable {
    @Serial
    private static final long serialVersionUID = -4126447521088482559L;

    public ReeferTruck(String vehicleID, String name, double capacity, double fuelCapacity, Port port,boolean status) {
        super(vehicleID, name, capacity, fuelCapacity, port, status);
    }


    @Override
    public boolean canMoveToPort(Port port){
        //check port condition
        if (!this.getPort().isLandingAbility()) {
            return false;
        }
        else{
            return super.canMoveToPort(port);
        }
    }
    public void moveToPort(Port port){
        if(!this.canMoveToPort(port)){
            System.out.println("The vehicle can not go to this port!");
        }
        else{
            super.moveToPort(port);
        }
    }
}
