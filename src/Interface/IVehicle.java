package Interface;

import Entities.CanMoveToPort;
import Entities.Container;
import Entities.Port;

public interface IVehicle {
    public boolean canMoveToPort(Port port);
    public boolean loadContainer(Container container);
    public boolean unloadContainer(Container container);
    public void moveToPort(Port port);

    public void hasArrived();
    public void refuel();
}
