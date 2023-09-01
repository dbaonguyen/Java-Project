package Users;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class PortManager extends User implements Serializable {
    private Map<String, String> userCredentials;

    public PortManager() {
        userCredentials = new HashMap<>();
        userCredentials.put("2", "2");
        userCredentials.put("3", "3");
        userCredentials.put("4", "4");
    }

    public Map<String, String> getUserCredentials() {
        return userCredentials;
    }
}
