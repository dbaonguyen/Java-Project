public class Main {
    public static void main(String[] args) {
        AdminInterface.run();
        boolean running = true;
        do {
            int choice = AdminInterface.login();
            while (choice == 1) {
                String indicator = AdminInterface.loginValidation();
                if (indicator.equals("invalid")) {
                    System.out.println("Invalid credentials, please enter again!");
                    choice = AdminInterface.login();
                } else if (indicator.equals("admin")) {
                    AdminInterface.loginMainMenu(indicator);
                } else {
                    PortManagerInterface.loginMainMenu(indicator);
                }
            }
            if (choice == 2) {
                System.out.println("Logged out");
                running = false;
            } else {
                System.out.println("Invalid option");
            }
        } while (running);
    }
}