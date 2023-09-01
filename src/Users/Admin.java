package Users;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin() {
        super("1", "1");
    }
    public Admin(String username, String password) {
        super(username, password);
    }
}
