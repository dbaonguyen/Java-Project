package Users;

import java.util.Map;
import java.util.HashMap;

public class PortManager extends User{
    private Map<String, String> userCredentials;

    public PortManager() {
        userCredentials = new HashMap<>();
        userCredentials.put("admin", "adminPassword");
        userCredentials.put("user1", "password1");
        userCredentials.put("user2", "password2");
    }

    public String getPasswordForUsername(String username) {
        return userCredentials.get(username);
    }

    public Map<String, String> getHashMap() {
        return userCredentials;
    }
}
