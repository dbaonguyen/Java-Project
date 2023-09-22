package Users;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin() {
        super("admin", "admin123");
    }
}
