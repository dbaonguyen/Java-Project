package Interface;

import Entities.Container;
import Entities.Trip;
import Entities.Vehicle;

import java.awt.*;

public interface IPort {


    void addContainer(Container container);

    void removeContainer(Container container);

    public void addVehicle(Vehicle vehicle);
    public void removeVehicle(Vehicle vehicle);
    public void searchVehicleById(String id);
    public void searchVehicleByName(String name);
    public void addTrip(Trip trip);
}
