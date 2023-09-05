package Users;

import Entities.Port;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class PortManager extends User implements Serializable {
    private static final long serialVersionUID = -7683490463929513482L;
    public Port portManaged;

    public PortManager(String username, String password, Port portManaged) {
        super(username, password);
        this.portManaged = portManaged;
    }
}
