package Users;

import Entities.Port;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class PortManager extends User implements Serializable {
    public Port portManaged;

    public PortManager(String username, String password, Port portManaged) {
        super(username, password);
        this.portManaged = portManaged;
    }
}
