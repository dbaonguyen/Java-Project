package Users;

import java.util.Map;
import java.util.HashMap;

public class PortManager extends User{
    private Map<String, String> userCredentials;

    public PortManager() {
        userCredentials = new HashMap<>();
        // Initialize the map with usernames and passwords
        userCredentials.put("admin", "adminPassword");
        userCredentials.put("user1", "password1");
        userCredentials.put("user2", "password2");
        // Add more username-password pairs as needed
    }

    public String getPasswordForUsername(String username) {
        return userCredentials.get(username);
    }

    public Map<String, String> getHashMap() {
        return userCredentials;
    }
}
