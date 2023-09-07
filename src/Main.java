public class  Main {
    public static void main(String[] args) {
        int choice = AdminInterface.login();
        boolean running = true;
        do {
            switch (choice) {
                //Login
                case 1:
                    String indicator = AdminInterface.loginValidation();
                    while (indicator.equals("invalid")) {
                        System.out.println("Invalid credentials, please enter again!");
                        indicator = AdminInterface.loginValidation();
                    }
                    if (indicator.equals("admin")) {
                        AdminInterface.loginMainMenu(indicator);
                    } else {
                        PortManagerInterface.loginMainMenu(indicator);
                    }
                    break;
                //Exit
                case 2:
                    System.out.println("Logged out");
                    running = false;
                    break;
                //Default case
                default:
                    System.out.println("Invalid option");
                    choice = AdminInterface.login();
                    break;
            }
        } while (running);

    }
}