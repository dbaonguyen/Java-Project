package Interface;

import Entities.Container;
import Entities.Port;
import Entities.Trip;
import Entities.Vehicle;

import java.awt.*;

public interface IPort {
    double calculateDistance(Port port);

    void addContainer(Container container);

    void removeContainer(Container container);


    public void addVehicle(Vehicle vehicle);
    public void removeVehicle(Vehicle vehicle);
    public void searchVehicleById(String id);

    void searchVehicle(String vehicleIdentifier);

    public void searchVehicleByName(String name);
    public void addTrip(Trip trip);
}
