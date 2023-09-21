package Entities;

import java.io.Serial;
import java.io.Serializable;

public class TankerTruck extends Vehicle implements Serializable {
    @Serial
    private static final long serialVersionUID = -7759814915653786155L;

    public TankerTruck(String vehicleID, String name, double capacity, double fuelCapacity, Port port, boolean status) {
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


}
