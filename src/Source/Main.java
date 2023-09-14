package Source;

import Users.Admin;
import Users.User;

public class Main {
    public static void main(String[] args) {
        AdminInterface.removeTripsAfterSevenDays();
//        AdminInterface.run();
        boolean running = true;
        do {
            int choice = AdminInterface.login();
            while (choice == 1) {
                User indicator = AdminInterface.loginValidation();
                if (indicator == null) {
                    System.out.println("Invalid credentials, please enter again!");
                    choice = AdminInterface.login();
                } else if (indicator instanceof Admin) {
                    AdminInterface.loginMainMenu();
                    choice = AdminInterface.login();
                } else {
                    PortManagerInterface.loginMainMenu(indicator);
                    choice = AdminInterface.login();
                }
            }
            if (choice == 2) {
                AdminInterface.writeListToFile(AdminInterface.userList, "userList.ser");
                AdminInterface.writeListToFile(AdminInterface.portList, "portList.ser");
                AdminInterface.writeListToFile(AdminInterface.containerList, "containerList.ser");
                AdminInterface.writeListToFile(AdminInterface.shipList, "shipList.ser");
                AdminInterface.writeListToFile(AdminInterface.truckList, "truckList.ser");
                AdminInterface.writeListToFile(AdminInterface.reeferTruckList, "reeferTruckList.ser");
                AdminInterface.writeListToFile(AdminInterface.tankerTruckList, "tankerTruckList.ser");
                AdminInterface.writeListToFile(AdminInterface.typeList, "typeList.ser");
                AdminInterface.writeListToFile(AdminInterface.usedUsername, "usedUsername.ser");
                AdminInterface.writeListToFile(AdminInterface.usedPortID, "usedPortID.ser");
                AdminInterface.writeListToFile(AdminInterface.tripList, "tripList.ser");
                AdminInterface.writeListToFile(AdminInterface.notificationList, "notificationList.ser");
                AdminInterface.writeListToFile(AdminInterface.containerIDs, "containerIDs.ser");
                AdminInterface.writeListToFile(AdminInterface.vehicleIDs, "vehicleIDs.ser");
                AdminInterface.writeListToFile(AdminInterface.portIDs, "portIDs.ser");
                System.out.println("Logged out");
                running = false;
            } else {
                System.out.println("Invalid option");
            }
        } while (running);
    }
}