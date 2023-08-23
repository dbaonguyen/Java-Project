package Interface;

import Entities.Container;
import Entities.Port;

public interface IVehicle {
    public boolean canMovetoPort(Port port);
    public void loadContainer(Container container);
    public void unloadContainer(Container container);
    public void moveToPort(Port port);
    public void refuel();
}
