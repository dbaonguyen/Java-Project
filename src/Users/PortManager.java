package Users;

import Entities.Port;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class PortManager extends User implements Serializable {
    private static final long serialVersionUID = -7683490463929513482L;
    private Port portManaged;

    public PortManager(String username, String password, Port portManaged) {
        super(username, password);
        this.portManaged = portManaged;
    }

    public Port getPortManaged() {
        return portManaged;
    }

    public void setPortManaged(Port portManaged) {
        this.portManaged = portManaged;
    }

    @Override
    public String toString() {
        return "User Information:\n" +
                "Username: " + super.getUsername() + "\n" +
                "Password: " + super.getPassword() + "\n" +
                "Port managed: " + portManaged.getPortID();
    }

}
