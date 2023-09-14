package Interface;

import Entities.Container;
import Entities.Port;
import Entities.Trip;
import Entities.Vehicle;


public interface IPort {
    double calculateDistance(Port port);

    void addContainer(Container container);

    void removeContainer(Container container);

    void searchContainer();

     void addVehicle(Vehicle vehicle);
     void removeVehicle(Vehicle vehicle);
     void searchVehicleById(String id);

     void searchVehicleByName(String name);
     void addTrip(Trip trip);

     void displayShips();

     void displayContainers();

     void displayTrips();



}
