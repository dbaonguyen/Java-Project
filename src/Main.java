import Entities.*;
import Users.Admin;
import Users.PortManager;
import Users.User;

import java.io.FileInputStream;
import java.util.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        User admin = new Admin("1","1");
        //Ports
        List<Port> ports = new ArrayList<>();
        Port port1 = new Port("p-1", "Harbor Bay", 34.0522, -118.2437, 500.0, 1500.0, true);
        Port port2 = new Port("p-2", "Marine City", 40.7128, -74.0060, 700.0, 1800.0, false);
        Port port3 = new Port("p-3", "Ocean View", -33.8688, 151.2093, 800.0, 2000.0, true);
        Port port4 = new Port("p-4", "Seaside Haven", 37.7749, -122.4194, 400.0, 1200.0, true);
        Port port5 = new Port("p-5", "Island Port", 21.3069, -157.8583, 600.0, 1600.0, false);
        ports.add(port1);
        ports.add(port2);
        ports.add(port3);
        ports.add(port4);
        ports.add(port5);

        Type dryStorage = new Type("Dry storage",3.5, 4.6);
        Type openTop = new Type("Open top", 2.8, 3.2);
        Type openSide = new Type("Open side", 2.7, 3.2);
        Type refrigerated = new Type("Refrigerated", 4.5, 5.4);
        Type liquid = new Type("Liquid", 4.8, 5.3);
        List<Container> containerList = new ArrayList<>();

        containerList.add(new Container("C1", 5.0, dryStorage));
        containerList.add(new Container("C2", 7.2, openTop));
        containerList.add(new Container("C3", 4.3, openSide));
        containerList.add(new Container("C4", 6.1, refrigerated));
        containerList.add(new Container("C5", 3.9, liquid));
        containerList.add(new Container("C6", 8.6, dryStorage));
        containerList.add(new Container("C7", 2.5, openTop));
        containerList.add(new Container("C8", 4.8, openSide));
        containerList.add(new Container("C9", 5.7, refrigerated));
        containerList.add(new Container("C10", 6.9, liquid));

        Ship ship1 = new Ship("S1", "Ship 1", 10000.0, 2000.0, 20000.0, 4000.0, port1);
        Ship ship2 = new Ship("S2", "Ship 2", 15000.0, 3000.0, 25000.0, 5000.0, port2);
        Ship ship3 = new Ship("S3", "Ship 3", 12000.0, 2500.0, 22000.0, 4500.0, port3);

        Truck truck1 = new Truck("T1", "Truck 1", 5000.0, 500.0, 10000.0, 1000.0, port1);
        Truck truck2 = new Truck("T2", "Truck 2", 6000.0, 600.0, 11000.0, 1100.0, port2);
        Truck truck3 = new Truck("T3", "Truck 3", 7000.0, 700.0, 12000.0, 1200.0, port3);

        ReeferTruck reeferTruck1 = new ReeferTruck("RT1", "Reefer Truck 1", 6000.0, 600.0, 12000.0, 1200.0, port1);
        ReeferTruck reeferTruck2 = new ReeferTruck("RT2", "Reefer Truck 2", 7000.0, 700.0, 13000.0, 1300.0, port2);
        ReeferTruck reeferTruck3 = new ReeferTruck("RT3", "Reefer Truck 3", 8000.0, 800.0, 14000.0, 1400.0, port3);

        TankerTruck tankerTruck1 = new TankerTruck("TT1", "Tanker Truck 1", 7000.0, 700.0, 14000.0, 1400.0, port1);
        TankerTruck tankerTruck2 = new TankerTruck("TT2", "Tanker Truck 2", 8000.0, 800.0, 15000.0, 1500.0, port2);
        TankerTruck tankerTruck3 = new TankerTruck("TT3", "Tanker Truck 3", 9000.0, 900.0, 16000.0, 1600.0, port3);
        HashMap<String, String> userCredentials = new HashMap<>();
        userCredentials.put("1", "1");
        userCredentials.put("2", "2");
        userCredentials.put("3", "3");

        Scanner scanner = new Scanner(System.in);

        do {
            int choice = 0;
            System.out.println("1. Login");
            System.out.println("2. Exit");
            try {
                System.out.println("Your option: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Please choose a valid option: ");
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter your username:");
                    String username = scanner.next();

                    System.out.println("Enter your password:");
                    String password = scanner.next();

                    if (loginValidation(username, password)) {
                        System.out.println("Welcome!");
                    } else {
                        System.out.println("Incorrect username or password!");
                    }
                    break;
            }
            break;
        } while (true);
    }

    public static boolean loginValidation (String username, String password) {
        User admin = new Admin();
        User portManagers = new PortManager();
        if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
            return true;
        }
        if (((PortManager)portManagers).getUserCredentials().containsKey(username) && ((PortManager)portManagers).getUserCredentials().containsValue(password)){
            return true;
        }
        return false;
    }
}