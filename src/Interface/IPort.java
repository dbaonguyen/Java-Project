package Interface;

import Entities.Trip;
import Entities.Vehicle;

import java.awt.*;

public interface IPort {
    public void addContainer(Container container);
    public void removeContainer(Container container);
    public void addVehicle(Vehicle vehicle);
    public void removeVehicle(Vehicle vehicle);
    public void searchVehicleById(String id);
    public void searchVehicleByName(String name);
    public void addTrip(Trip trip);
}
