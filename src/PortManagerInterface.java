import Entities.*;
import Users.Admin;
import Users.PortManager;
import Users.User;
import java.io.*;
import java.util.*;

public class PortManagerInterface {
    //data load
    private static List<User> userList = readListFromFile("userList.ser");
    private static List<Port> portList = readListFromFile("portList.ser");
    private static List<Container> containerList = readListFromFile("containerList.ser");
    private static List<Ship> shipList = readListFromFile("shipList.ser");
    private static List<Truck> truckList = readListFromFile("truckList.ser");
    private static List<ReeferTruck> reeferTruckList = readListFromFile("reeferTruckList.ser");
    private static List<TankerTruck> tankerTruckList = readListFromFile("tankerTruckList.ser");
    private static List<Type> typeList = readListFromFile("typeList.ser");
    private static final String DEFAULT_DIRECTORY = "Data"; // Change this to your default directory path
    private static int choice = -1;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean running = true;
    public static <T> void writeListToFile(List<T> list, String fileName) {
        String filePath = DEFAULT_DIRECTORY + File.separator + fileName;
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(list);
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
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedList;
    }

    public static void decorativeLine() {
        for (int i = 0;i < 50;i++){
            System.out.print("*");
        }
    }
    public static void login() {
        decorativeLine();
        System.out.println();
        System.out.println("1. Login");
        System.out.println("2. Exit");
        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option");
        }
    }
    public static void calculateDistance(Port port, List<String> portIDs, String portOption) {
        List<String> portIDs2 = new ArrayList<>();
        do {
            decorativeLine();
            System.out.println();
            for (Port portFrom : portList) {
                if (portFrom != port){
                    System.out.println(portFrom.getPortID() + ". " + portFrom.getName());
                    portIDs2.add(portFrom.getPortID());
                }
            }
            System.out.println("0. Go back");
            System.out.print("Enter the ID of the port above that you want to calculate distance between: ");
            String portOption2 = scanner.nextLine();

            //Method
            decorativeLine();
            System.out.println();
            if (portIDs2.contains(portOption2)) {
                System.out.printf("The distance between 2 ports is: %.2f km\n", portList.get(portIDs.indexOf(portOption)).calculateDistance(portList.get(portIDs2.indexOf(portOption2))));
                break;
            } else {
                System.out.println("Please choose a valid option");
            }

            if (portOption2.equals("0")) {
                break;
            }
        } while (true);
    }
    public static void addContainer(List<String> containerIDs, Port port) {
        String containerID;
        do {
            decorativeLine();
            System.out.println();
            try {
                System.out.println("Please enter the container ID by the format 'c-containerID': ");
                containerID = scanner.nextLine();

                if (!containerID.matches("c-\\d+")) {
                    System.out.println("Invalid ID. The ID must be in the format 'c-<integer>'.");
                } else {
                    if (!containerIDs.contains(containerID)) {
                        containerIDs.add(containerID);
                        break;
                    } else {
                        System.out.println("The ID is already existed!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        } while (true);

        double containerWeight = 0;
        do {
            try {
                System.out.println("Please enter your port current weight:");
                containerWeight = Double.parseDouble(scanner.nextLine());
                if (containerIDs.contains(containerID) && containerWeight > port.getCurrentWeight()) {
                    containerIDs.remove(containerID);
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        } while (true);

        int typeID = -1;
        do {
            for (int i = 0; i < typeList.size(); i++) {
                System.out.println(i + ". " + typeList.get(i).getType());
            }
            try {
                System.out.println("Please choose the container type number 0-4");
                typeID = Integer.parseInt(scanner.nextLine());

                if (typeID >= 0 && typeID < typeList.size()) {
                    break;
                } else {
                    System.out.println("Please enter a valid option (0-4)");
                }
            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        } while (true);
        port.addContainer(new Container(containerID, containerWeight, typeList.get(typeID)));
        System.out.println(containerIDs);
    }
    public static void removeContainer(Port port) {
        decorativeLine();
        System.out.println();
        boolean running4 = true;
        Iterator<Container> containerIterator = port.getContainers().iterator();
        do {
            for (Container container : port.getContainers()) {
                System.out.println(container.getContainerID() + ". " + container.getType());
            }
            System.out.println("0. Go back");
            System.out.println("Enter the container ID that you want to remove");
            String containerRemoved = scanner.nextLine();

            if (containerRemoved.equals("0")) {
                break;
            }

            boolean containerFound = false;
            while (containerIterator.hasNext()) {
                Container container = containerIterator.next();
                if (containerRemoved.equals(container.getContainerID())) {
                    containerIterator.remove();
                    containerFound = true;
                    running4 = false;
                    System.out.println("Container is removed");
                    break;
                }
            }

            if (!containerFound) {
                System.out.println("The container does not exist");
            }
        } while (running4);
    }
    public static void addVehicle(List<String> vehicleIDs, Port port) {
        decorativeLine();
        System.out.println();
        choice = -1;
        do {
            try {
                System.out.println("Choose the vehicle you want to add");
                System.out.println("1. Ship");
                System.out.println("2. Truck");
                System.out.println("3. Reefer Truck");
                System.out.println("4. Tanker Truck");
                System.out.println("5. Go back");
                System.out.print("Your option: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Please choose a valid option");
            }

            //Ship
            if (choice == 1) {
                String vehicleID;
                do {
                    try {
                        System.out.println("Please enter the vehicle ID by the format 's-vehicleID': ");
                        vehicleID = scanner.nextLine();

                        if (!vehicleID.matches("s-\\d+")) {
                            System.out.println("Invalid ID. The ID must be in the format 's-<integer>'.");
                        } else {
                            if (!vehicleIDs.contains(vehicleID)) {
                                vehicleIDs.add(vehicleID);
                                break;
                            } else {
                                System.out.println("The ID is already existed!");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                System.out.println("Please enter your vehicle name:");
                String vehicleName = scanner.nextLine();

                double vehicleCurrentWeight = 0;

                double curruntFuel = 0;
                do {
                    try {
                        System.out.println("Please enter your vehicle current fuel:");
                        curruntFuel = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                double capacity = 0;
                do {
                    try {
                        System.out.println("Please enter your vehicle capacity:");
                        capacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);


                double fuelCapacity = 0;
                do {
                    try {
                        System.out.println("Please enter your vehicle fuel capacity:");
                        fuelCapacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);
                port.addVehicle(new Ship(vehicleID, vehicleName, vehicleCurrentWeight, curruntFuel, capacity, fuelCapacity, port));
                System.out.println("New vehicle has been added");
                System.out.println(vehicleIDs);
                break;
            }

            //Truck
            else if (choice == 2) {
                String vehicleID;
                do {
                    try {
                        System.out.println("Please enter the vehicle ID by the format 't-vehicleID': ");
                        vehicleID = scanner.nextLine();

                        if (!vehicleID.matches("t-\\d+")) {
                            System.out.println("Invalid ID. The ID must be in the format 't-<integer>'.");
                        } else {
                            if (!vehicleIDs.contains(vehicleID)) {
                                vehicleIDs.add(vehicleID);
                                break;
                            } else {
                                System.out.println("The ID is already existed!");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                System.out.println("Please enter your vehicle name:");
                String vehicleName = scanner.nextLine();

                double vehicleCurrentWeight = 0;

                double curruntFuel = 0;
                do {
                    try {
                        System.out.println("Please enter your vehicle current fuel:");
                        curruntFuel = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                double capacity = 0;
                do {
                    try {
                        System.out.println("Please enter your vehicle capacity:");
                        capacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);


                double fuelCapacity = 0;
                do {
                    try {
                        System.out.println("Please enter your vehicle fuel capacity:");
                        fuelCapacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);
                port.addVehicle(new Truck(vehicleID, vehicleName, vehicleCurrentWeight, curruntFuel, capacity, fuelCapacity, port));
                System.out.println("New vehicle has been added");
                System.out.println(vehicleIDs);
                break;
            }

            //Reefer Truck
            else if (choice == 3) {
                String vehicleID;
                do {
                    try {
                        System.out.println("Please enter the vehicle ID by the format 'rt-vehicleID': ");
                        vehicleID = scanner.nextLine();

                        if (!vehicleID.matches("rt-\\d+")) {
                            System.out.println("Invalid ID. The ID must be in the format 'rt-<integer>'.");
                        } else {
                            if (!vehicleIDs.contains(vehicleID)) {
                                vehicleIDs.add(vehicleID);
                                break;
                            } else {
                                System.out.println("The ID is already existed!");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                System.out.println("Please enter your vehicle name:");
                String vehicleName = scanner.nextLine();

                double vehicleCurrentWeight = 0;

                double curruntFuel = 0;
                do {
                    try {
                        System.out.println("Please enter your vehicle current fuel:");
                        curruntFuel = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                double capacity = 0;
                do {
                    try {
                        System.out.println("Please enter your vehicle capacity:");
                        capacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                double fuelCapacity = 0;
                do {
                    try {
                        System.out.println("Please enter your vehicle fuel capacity:");
                        fuelCapacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);
                port.addVehicle(new ReeferTruck(vehicleID, vehicleName, vehicleCurrentWeight, curruntFuel, capacity, fuelCapacity, port));
                System.out.println("New vehicle has been added");
                System.out.println(vehicleIDs);
                break;
            }

            //Tanker Truck
            else if (choice == 4) {
                String vehicleID;
                do {
                    try {
                        System.out.println("Please enter the vehicle ID by the format 'tt-vehicleID': ");
                        vehicleID = scanner.nextLine();

                        if (!vehicleID.matches("tt-\\d+")) {
                            System.out.println("Invalid ID. The ID must be in the format 'tt-<integer>'.");
                        } else {
                            if (!vehicleIDs.contains(vehicleID)) {
                                vehicleIDs.add(vehicleID);
                                break;
                            } else {
                                System.out.println("The ID is already existed!");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                System.out.println("Please enter your vehicle name:");
                String vehicleName = scanner.nextLine();

                double vehicleCurrentWeight = 0;

                double curruntFuel = 0;
                do {
                    try {
                        System.out.println("Please enter your vehicle current fuel:");
                        curruntFuel = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                double capacity = 0;
                do {
                    try {
                        System.out.println("Please enter your vehicle capacity:");
                        capacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Please enter a valid value");
                    }
                } while (true);

                double fuelCapacity = 0;
                do {
                    try {
                        System.out.println("Please enter your vehicle fuel capacity:");
                        fuelCapacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);
                port.addVehicle(new TankerTruck(vehicleID, vehicleName, vehicleCurrentWeight, curruntFuel, capacity, fuelCapacity, port));
                System.out.println("New vehicle has been added");
                System.out.println(vehicleIDs);
                break;
            } else if (choice == 5) {
                System.out.println(vehicleIDs);
                break;
            } else {
                System.out.println("Please enter from 1-5");
            }
        } while (true);
    }
    public static void searchVehicle(Port port) {
        choice = -1;
        do {
            try {
                decorativeLine();
                System.out.println();
                System.out.println("1. By ID");
                System.out.println("2. By Name");
                System.out.println("3. Go back");
                System.out.print("Your option: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid value");
            }

            if (choice == 1) {
                System.out.println("Please enter the ID of the vehicle that you want to find");
                String vehicleID = scanner.nextLine();
                decorativeLine();
                System.out.println();
                port.searchVehicleById(vehicleID);
                break;
            } else if (choice == 2) {
                System.out.println("Please enter the name of the vehicle that you want to find");
                String vehicleName = scanner.nextLine();
                decorativeLine();
                System.out.println();
                port.searchVehicleByName(vehicleName);
                break;
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Please enter from 1-3");
            }
        } while (true);
    }
    public static void addPort(List<String> portIDs) {
        String portID;
        do {
            try {
                System.out.println("Please enter the port ID by the format 'p-portID': ");
                portID = scanner.nextLine();

                if (!portID.matches("p-\\d+")) {
                    System.out.println("Invalid ID. The ID must be in the format 'p-<integer>'.");
                } else {
                    if (!portIDs.contains(portID)) {
                        portIDs.add(portID);
                        break;
                    } else {
                        System.out.println("The ID is already existed!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        } while (true);

        System.out.println("Please enter your port name:");
        String portName = scanner.nextLine();

        double portLatitude = 0;
        do {
            try {
                System.out.println("Please enter your port latitude:");
                portLatitude = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid value");
            }
        } while (true);

        double portLongtitude = 0;
        do {
            try {
                System.out.println("Please enter your port longtitude:");
                portLongtitude = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid value");
            }
        } while (true);

        double portCapacity = 0;
        do {
            try {
                System.out.println("Please enter your port capacity:");
                portCapacity = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid value");
            }
        } while (true);

        double portCurrentWeight = 0;

        boolean portLandingAbility;
        do {
            try {
                System.out.println("Please enter your port landing ability (true/false):");
                portLandingAbility = Boolean.parseBoolean(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid value");
            }
        } while (true);
        portList.add(new Port(portID,portName,portLatitude,portLongtitude,portCapacity,portCurrentWeight,portLandingAbility));
        System.out.println("New port has been added");
    }
    public static void removePort(List<String> portIDs) {
        boolean running5 = true;
        do {
            Iterator<Port> iterator = portList.iterator();
            for (Port port : portList) {
                System.out.println(port.getPortID() + ". " + port.getName());
            }
            System.out.println("0. Go back");
            System.out.println("Enter the port ID that you want to remove:");
            String portRemoved = scanner.nextLine();
            if (portRemoved.equals("0")) {
                running5 = false;
            }
            boolean found = false;
            while (iterator.hasNext()) {
                Port port = iterator.next();
                if (port.getPortID().equals(portRemoved)) {
                    iterator.remove();
                    portIDs.remove(portRemoved);
                    System.out.println("Port is removed");
                    found = true;
                    running5 = false;
                    break;
                }
            }
            if (!found) {
                System.out.println("Port does not exist");
            }
        } while (running5);
    }
    public static void run() {

        //Login
        do {
            login();
            switch (choice) {
                //Username and Password
                case 1:
                    //validate login
                    String indicator = loginValidation();
                    if (!indicator.equals("invalid")) {
                        System.out.println("Welcome " + indicator);
                        //Admin
                        boolean running2 = true;
                        choice = -1;
                        do {
                            //Menu
                            decorativeLine();
                            System.out.println();
                            System.out.println("1. Choose port");
                            System.out.println("2. Add port");
                            System.out.println("3. Remove port");
                            System.out.println("4. Go back");
                            try {
                                System.out.print("Your option: ");
                                choice = Integer.parseInt(scanner.nextLine());
                            } catch (Exception e) {
                                System.out.println("Please choose a valid option: ");
                            }
                            decorativeLine();
                            System.out.println();
                            List<String> portIDs = new ArrayList<>();
                            for (Port port : portList) {
                                portIDs.add(port.getPortID());
                            }


                            switch (choice) {
                                //Choose port
                                case 1:
                                    List<String> containerIDs = new ArrayList<>();
                                    List<String> vehicleIDs = new ArrayList<>();
                                    for (Port port : portList) {
                                        for (Container container : port.getContainers()) {
                                            containerIDs.add(container.getContainerID());
                                        }
                                        for (Vehicle vehicle : port.getVehicles()) {
                                            vehicleIDs.add(vehicle.getVehicleID());
                                        }
                                    }
                                    do {
                                        //Port IDs
                                        for (Port port : portList) {
                                            System.out.println(port.getPortID() + ". " + port.getName());
                                            portIDs.add(port.getPortID());
                                        }

                                        System.out.println("0. Go back");
                                        System.out.print("Enter the ID of the port above that you want to modify: ");
                                        String portOption = scanner.nextLine();

                                        if (!portIDs.contains(portOption)) {
                                            System.out.println("Port does not exist");
                                        } else {
                                            for (Port port : portList) {
                                                if (portOption.equals(port.getPortID())) {
                                                    boolean running3 = true;
                                                    choice = 0;
                                                    do {
                                                        //Menu
                                                        decorativeLine();
                                                        System.out.println();
                                                        System.out.println("1. Calculate distance");
                                                        System.out.println("2. Add Container");
                                                        System.out.println("3. Remove Container");
                                                        System.out.println("4. Add Vehicle");
                                                        System.out.println("5. Remove Vehicle");
                                                        System.out.println("6. Search Vehicle");
                                                        System.out.println("7. Add Trips");
                                                        System.out.println("8. Remove Trips");
                                                        System.out.println("9. Display Trips");
                                                        System.out.println("10. Display Vehicles");
                                                        System.out.println("11. Display Containers");
                                                        System.out.println("12. Go Back");
                                                        try {
                                                            System.out.print("Your option: ");
                                                            choice = Integer.parseInt(scanner.nextLine());
                                                        } catch (Exception e) {
                                                            System.out.print("Please choose a valid option: ");
                                                        }

                                                        switch (choice) {
                                                            //Caculate distance
                                                            case 1:
                                                                calculateDistance(port, portIDs, portOption);
                                                                break;
                                                            //Add container
                                                            case 2:
                                                                addContainer(containerIDs, port);
                                                                break;
                                                            //Remove container
                                                            case 3:
                                                                removeContainer(port);
                                                                break;
                                                            //Add vehicles
                                                            case 4:
                                                                addVehicle(vehicleIDs, port);
                                                                break;
                                                            //Remove Vehicle
                                                            case 5:
                                                                removeContainer(port);
                                                                break;
                                                            //Search vehicle
                                                            case 6:
                                                                searchVehicle(port);
                                                                break;
                                                            //Add Trip
                                                            case 7:
                                                                break;
                                                            //Remove Trip
                                                            case 8:
                                                                break;
                                                            //Display trips
                                                            case 9:
                                                                port.displayTrip(new Date());
                                                                //Display vehicles
                                                            case 10:
                                                                port.displayShips();
                                                                port.displayTrucks();
                                                                break;
                                                            //Display containers
                                                            case 11:
                                                                System.out.println(port.getContainers());
                                                                break;
                                                            //Go back
                                                            case 12:
                                                                running3 = false;
                                                                break;
                                                        }
                                                    } while (running3);
                                                }
                                            }
                                        }

                                        if (portOption.equals("0")) {
                                            break;
                                        }

                                    } while (true);
                                    break;

                                //Add port
                                case 2:
                                    addPort(portIDs);
                                    break;
                                //Remove port
                                case 3:
                                    removePort(portIDs);
                                    break;
                                //Go back
                                case 4:
                                    running2 = false;
                                    break;
                                default:
                                    System.out.println("Please choose from 1-4");
                                    break;
                            }
                        } while (running2);

                    } else {
                        System.out.println("Incorrect username or password!");
                    }
                    break;

                //Exit
                case 2:
                    System.out.println("Logged out");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (running);
    }

    public static String loginValidation () {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Search for a user with the provided username in the list of registered users.
        User currentLoginUser = null;
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                currentLoginUser = user;
                break; // Exit the loop once a matching username is found.
            }
        }
        if (currentLoginUser != null) {
            // If a matching username is found, validate the password.
            if (currentLoginUser.getPassword().equals(password)) {
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