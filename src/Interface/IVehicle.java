package Interface;

import Entities.CanMoveToPort;
import Entities.Container;
import Entities.Port;

public interface IVehicle {
    public boolean canMoveToPort(Port port);
    public void loadContainer(Container container);
    public void unloadContainer(Container container);
    public void moveToPort(Port port);

    public void hasArrived();
    public void refuel();
}
