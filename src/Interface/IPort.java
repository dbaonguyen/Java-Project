package Interface;

import Entities.Container;
import Entities.Port;
import Entities.Trip;
import Entities.Vehicle;


public interface IPort {

    void addContainer(Container container);

    void removeContainer(Container container);

     void addVehicle(Vehicle vehicle);
     void removeVehicle(Vehicle vehicle);
     void addTrip(Trip trip);

     void displayShips();
    void displayTrucks();

     void displayContainers();

     void displayTrips();



}
