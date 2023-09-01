import Entities.*;
import Users.Admin;
import Users.PortManager;
import Users.User;
import java.io.*;
import java.util.*;

public class Main {
    //data load
    private static List<User> userList = new ArrayList<>();
    private static List<Port> portList = new ArrayList<>();
    private static List<Container> containerList = new ArrayList<>();
    private static List<Ship> shipList = new ArrayList<>();
    private static List<Truck> truckList = new ArrayList<>();
    private static List<ReeferTruck> reeferTruckList = new ArrayList<>();
    private static List<TankerTruck> tankerTruckList = new ArrayList<>();
    private static List<Type> typeList = new ArrayList<>();
    private static final String DEFAULT_DIRECTORY = "Data"; // Change this to your default directory path

    public static <T> void writeListToFile(List<T> list, String fileName) {
        String filePath = DEFAULT_DIRECTORY + File.separator + fileName;
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(list);
            System.out.println("List has been serialized and saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> readListFromFile(String fileName) {
        String filePath = DEFAULT_DIRECTORY + File.separator + fileName;
        List<T> deserializedList = null;
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            deserializedList = (List<T>) in.readObject();
            System.out.println("List has been deserialized from " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedList;
    }
    public static void main(String[] args) {

        //data initialization
        userList.add(new Admin("1","1"));
        userList.add(new User("2", "2"));

        typeList.add(new Type("Dry storage", 3.5, 4.6));
        typeList.add(new Type("Open top", 2.8, 3.2));
        typeList.add(new Type("Open side", 2.7, 3.2));
        typeList.add(new Type("Refrigerated", 4.5, 5.4));
        typeList.add(new Type("Liquid", 4.8, 5.3));

        containerList.add(new Container("C1", 5.0, typeList.get(0)));  // Dry storage
        containerList.add(new Container("C2", 7.2, typeList.get(1)));  // Open top
        containerList.add(new Container("C3", 4.3, typeList.get(2)));  // Open side
        containerList.add(new Container("C4", 6.1, typeList.get(3)));  // Refrigerated
        containerList.add(new Container("C5", 3.9, typeList.get(4)));  // Liquid
        containerList.add(new Container("C6", 8.6, typeList.get(0)));  // Dry storage
        containerList.add(new Container("C7", 2.5, typeList.get(1)));  // Open top
        containerList.add(new Container("C8", 4.8, typeList.get(2)));  // Open side
        containerList.add(new Container("C9", 5.7, typeList.get(3)));  // Refrigerated
        containerList.add(new Container("C10", 6.9, typeList.get(4))); // Liquid
        //Ports

        portList.add(new Port("p-1", "Harbor Bay", 34.0522, -118.2437, 500.0, 1500.0, true));
        portList.add(new Port("p-2", "Marine City", 40.7128, -74.0060, 700.0, 1800.0, false));
        portList.add(new Port("p-3", "Ocean View", -33.8688, 151.2093, 800.0, 2000.0, true));
        portList.add(new Port("p-4", "Seaside Haven", 37.7749, -122.4194, 400.0, 1200.0, true));
        portList.add(new Port("p-5", "Island Port", 21.3069, -157.8583, 600.0, 1600.0, false));

        shipList.add(new Ship("S1", "Ship 1", 10000.0, 2000.0, 20000.0, 4000.0, portList.get(0)));
        shipList.add(new Ship("S2", "Ship 2", 15000.0, 3000.0, 25000.0, 5000.0, portList.get(1)));
        shipList.add(new Ship("S3", "Ship 3", 12000.0, 2500.0, 22000.0, 4500.0, portList.get(2)));

        truckList.add(new Truck("T1", "Truck 1", 5000.0, 500.0, 10000.0, 1000.0, portList.get(0)));
        truckList.add(new Truck("T2", "Truck 2", 6000.0, 600.0, 11000.0, 1100.0, portList.get(1)));
        truckList.add(new Truck("T3", "Truck 3", 7000.0, 700.0, 12000.0, 1200.0, portList.get(2)));

        reeferTruckList.add(new ReeferTruck("RT1", "Reefer Truck 1", 6000.0, 600.0, 12000.0, 1200.0, portList.get(0)));
        reeferTruckList.add(new ReeferTruck("RT2", "Reefer Truck 2", 7000.0, 700.0, 13000.0, 1300.0, portList.get(1)));
        reeferTruckList.add(new ReeferTruck("RT3", "Reefer Truck 3", 8000.0, 800.0, 14000.0, 1400.0, portList.get(2)));

        tankerTruckList.add(new TankerTruck("TT1", "Tanker Truck 1", 7000.0, 700.0, 14000.0, 1400.0, portList.get(0)));
        tankerTruckList.add(new TankerTruck("TT2", "Tanker Truck 2", 8000.0, 800.0, 15000.0, 1500.0, portList.get(1)));
        tankerTruckList.add(new TankerTruck("TT3", "Tanker Truck 3", 9000.0, 900.0, 16000.0, 1600.0, portList.get(2)));

        writeListToFile(portList, "portList.ser");
        writeListToFile(userList, "userList.ser");
        writeListToFile(containerList, "containerList.ser");
        writeListToFile(shipList, "shipList.ser");
        writeListToFile(truckList, "truckList.ser");
        writeListToFile(reeferTruckList, "reeferTruckList.ser");
        writeListToFile(tankerTruckList, "tankerTruckList.ser");
        writeListToFile(typeList, "typeList.ser");
        Scanner scanner = new Scanner(System.in);
//        do {
//            int choice = 0;
//            System.out.println("1. Login");
//            System.out.println("2. Exit");
//            try {
//                System.out.println("Your option: ");
//                choice = Integer.parseInt(scanner.nextLine());
//            } catch (Exception e) {
//                System.out.println("Please choose a valid option: ");
//            }
//
//            switch (choice) {
//                case 1:
//                    System.out.println("Enter your username:");
//                    String username = scanner.next();
//
//                    System.out.println("Enter your password:");
//                    String password = scanner.next();
//                    String indicator = loginValidation2(username, password);
//                    if (!indicator.equals("invalid")) {
//                        System.out.println("Welcome!" + indicator);
//                    } else {
//                        System.out.println("Incorrect username or password!");
//                    }
//                    break;
//            }
//            break;
//        } while (true);
    }

    public static String loginValidation2 (String enteredUsername, String enteredPassword) {
        // Search for a user with the provided username in the list of registered users.
        User currentLoginUser = null;
        for (User user : userList) {
            if (user.getUsername().equals(enteredUsername)) {
                currentLoginUser = user;
                break; // Exit the loop once a matching username is found.
            }
        }
        if (currentLoginUser != null) {
            // If a matching username is found, validate the password.
            if (currentLoginUser.getPassword().equals(enteredPassword)) {
                // Check the user's type (role).
                if (currentLoginUser instanceof Admin) {
                    return "admin";
                } else if (currentLoginUser instanceof PortManager) {
                    return "manager";
                } else {
                    return "user";
                }
            }
        }

        // If no matching username or invalid password, return "invalid" as a flag.
        return "invalid";
    }
}