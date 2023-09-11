package Interface;

import Entities.Container;
import Entities.Port;
import Entities.Trip;
import Entities.Vehicle;
import Users.PortManager;

import java.util.*;

import java.awt.*;

public interface IPort {
    double calculateDistance(Port port);

    void addContainer(Container container);

    void removeContainer(Container container);

    void searchContainer(String id);


    public void addVehicle(Vehicle vehicle);
    public void removeVehicle(Vehicle vehicle);
    public void searchVehicleById(String id);

    public void searchVehicleByName(String name);
    public void addTrip(Trip trip);

    public void displayTrip(Date date);

    public void displayTrip(Date date1, Date date2);

    public void displayShips();

    public void displayContainers();

    public double totalFuel(String date);

}
