package Interface;

import Entities.CanMoveToPort;
import Entities.Container;
import Entities.Port;
import Entities.Trip;

import java.util.Date;

public interface IVehicle {
    public boolean canMoveToPort(Port port);
    public boolean loadContainer(Container container);
    public boolean unloadContainer(Container container);
    public void moveToPort(Port port);

    void moveToPort(Port port, String departureDate, String arrivalDate);

    Trip moveToPort1(Port port, Date departureDate, Date arrivalDate);

    public void hasArrived(Trip trip);
    public void refuel();
}
