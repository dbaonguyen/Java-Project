package Interface;


import Entities.Container;
import Entities.Port;
import Entities.Trip;

import java.util.Date;

public interface IVehicle {
     boolean canMoveToPort(Port port);
     boolean loadContainer(Container container);
     boolean unloadContainer(Container container);
    Trip moveToPort(Port port, Date departureDate, Date arrivalDate);

     void hasArrived(Trip trip);
     void refuel();
}
