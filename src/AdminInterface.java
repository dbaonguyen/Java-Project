import Entities.*;
import Users.Admin;
import Users.PortManager;
import Users.User;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;
import java.util.Date;

public class AdminInterface {
    //data load
    private static List<User> userList = new ArrayList<>();
    private static List<Port> portList = new ArrayList<>();
    private static List<Container> containerList = new ArrayList<>();
    private static List<Ship> shipList = new ArrayList<>();
    private static List<Truck> truckList = new ArrayList<>();
    private static List<ReeferTruck> reeferTruckList = new ArrayList<>();
    private static List<TankerTruck> tankerTruckList = new ArrayList<>();
    private static List<Type> typeList = new ArrayList<>();
    private static final String DEFAULT_DIRECTORY = "Data";
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Port port1 = new Port("p-1", "Harbor City Port", 40.7128, -74.0060, 100000, 0, true);
        Port port2 = new Port("p-2", "Pacific Harbor Port", 34.0522, -118.2437, 80000, 0, true);
        Port port3 = new Port("p-3", "London Bay Port", 51.5074, -0.1278, 120000, 0, true);
        Port port4 = new Port("p-4", "Paris Dock Port", 48.8566, 2.3522, 90000, 0, false);
        Port port5 = new Port("p-5", "Golden Gate Port", 37.7749, -122.4194, 75000, 0, true);
        portList.add(port1);
        portList.add(port2);
        portList.add(port3);
        portList.add(port4);
        portList.add(port5);
        PortManager manager1 = new PortManager("1", "1", port1);
        PortManager manager2 = new PortManager("2", "2", port2);
        PortManager manager3 = new PortManager("3", "3", port3);
        PortManager manager4 = new PortManager("4", "4", port4);
        PortManager manager5 = new PortManager("5", "5", port5);

        Admin admin = new Admin("0", "0");

        Type dryStorage = new Type("Dry storage", 4.6, 3.5);
        Type openTop = new Type("Open top", 3.2, 2.8);
        Type openSide = new Type("Open side", 3.2, 2.7);
        Type refrigerated = new Type("Refrigerated", 5.4, 4.5);
        Type liquid = new Type("Liquid", 5.3, 4.8);
        typeList.add(dryStorage);
        typeList.add(openTop);
        typeList.add(openSide);
        typeList.add(refrigerated);
        typeList.add(liquid);
        Container c1 = new Container("c-1", 5000, dryStorage);
        Container c2 = new Container("c-2", 6000, openTop);
        Container c3 = new Container("c-3", 5500, openSide);
        Container c4 = new Container("c-4", 7000, refrigerated);
        Container c5 = new Container("c-5", 8000, liquid);
        Container c6 = new Container("c-6", 4800, dryStorage);
        Container c7 = new Container("c-7", 5200, openTop);
        Container c8 = new Container("c-8", 6200, openSide);
        Container c9 = new Container("c-9", 7500, refrigerated);
        Container c10 = new Container("c-10", 9000, liquid);
        Container c11 = new Container("c-11", 5100, dryStorage);
        Container c12 = new Container("c-12", 5900, openTop);
        Container c13 = new Container("c-13", 5300, openSide);
        Container c14 = new Container("c-14", 7200, refrigerated);
        Container c15 = new Container("c-15", 8500, liquid);
        Container c16 = new Container("c-16", 4700, dryStorage);
        Container c17 = new Container("c-17", 6400, openTop);
        Container c18 = new Container("c-18", 5600, openSide);
        Container c19 = new Container("c-19", 7300, refrigerated);
        Container c20 = new Container("c-20", 8800, liquid);
        Container c21 = new Container("c-21", 4900, dryStorage);
        Container c22 = new Container("c-22", 6100, openTop);
        Container c23 = new Container("c-23", 5400, openSide);
        Container c24 = new Container("c-24", 7100, refrigerated);
        Container c25 = new Container("c-25", 8400, liquid);
        Container c26 = new Container("c-26", 5200, dryStorage);
        Container c27 = new Container("c-27", 6300, openTop);
        Container c28 = new Container("c-28", 5800, openSide);
        Container c29 = new Container("c-29", 7400, refrigerated);
        Container c30 = new Container("c-30", 8900, liquid);
        containerList.add(c1);
        containerList.add(c2);
        containerList.add(c3);
        containerList.add(c4);
        containerList.add(c5);
        containerList.add(c6);
        containerList.add(c7);
        containerList.add(c8);
        containerList.add(c9);
        containerList.add(c10);
        containerList.add(c11);
        containerList.add(c12);
        containerList.add(c13);
        containerList.add(c14);
        containerList.add(c15);
        containerList.add(c16);
        containerList.add(c17);
        containerList.add(c18);
        containerList.add(c19);
        containerList.add(c20);
        containerList.add(c21);
        containerList.add(c22);
        containerList.add(c23);
        containerList.add(c24);
        containerList.add(c25);
        containerList.add(c26);
        containerList.add(c27);
        containerList.add(c28);
        containerList.add(c29);
        containerList.add(c30);
        // Create Trucks
        Truck truck1 = new Truck("tr-001", "Truck 1", 10000, 200, port1);
        Truck truck2 = new Truck("tr-002", "Truck 2", 12000, 220, port2);
        Truck truck3 = new Truck("tr-003", "Truck 3", 11000, 210, port3);
        Truck truck4 = new Truck("tr-004", "Truck 4", 10500, 205, port4);
        Truck truck5 = new Truck("tr-005", "Truck 5", 9500, 190, port5);
        truckList.add(truck1);
        truckList.add(truck2);
        truckList.add(truck3);
        truckList.add(truck4);
        truckList.add(truck5);
        // Create Ships
        Ship ship1 = new Ship("s-001", "Ship 1", 50000, 10000, port1);
        Ship ship2 = new Ship("s-002", "Ship 2", 55000, 12000, port4);
        Ship ship3 = new Ship("s-003", "Ship 3", 52000, 11000, port5);
        Ship ship4 = new Ship("s-004", "Ship 4", 53000, 11200, port2);
        Ship ship5 = new Ship("s-005", "Ship 5", 51000, 10800, port3);
        shipList.add(ship1);
        shipList.add(ship2);
        shipList.add(ship3);
        shipList.add(ship4);
        shipList.add(ship5);
        // Create ReeferTrucks
        ReeferTruck reeferTruck1 = new ReeferTruck("rf-001", "ReeferTruck 1", 14000, 250, port2);
        ReeferTruck reeferTruck2 = new ReeferTruck("rf-002", "ReeferTruck 2", 15000, 270, port4);
        ReeferTruck reeferTruck3 = new ReeferTruck("rf-003", "ReeferTruck 3", 14500, 260, port5);
        ReeferTruck reeferTruck4 = new ReeferTruck("rf-004", "ReeferTruck 4", 14300, 255, port1);
        ReeferTruck reeferTruck5 = new ReeferTruck("rf-005", "ReeferTruck 5", 14700, 265, port3);
        reeferTruckList.add(reeferTruck1);
        reeferTruckList.add(reeferTruck2);
        reeferTruckList.add(reeferTruck3);
        reeferTruckList.add(reeferTruck4);
        reeferTruckList.add(reeferTruck5);
        // Create TankerTrucks
        TankerTruck tankerTruck1 = new TankerTruck("tt-001", "TankerTruck 1", 16000, 300, port3);
        TankerTruck tankerTruck2 = new TankerTruck("tt-002", "TankerTruck 2", 17000, 320, port4);
        TankerTruck tankerTruck3 = new TankerTruck("tt-003", "TankerTruck 3", 16500, 310, port2);
        TankerTruck tankerTruck4 = new TankerTruck("tt-004", "TankerTruck 4", 16300, 305, port1);
        TankerTruck tankerTruck5 = new TankerTruck("tt-005", "TankerTruck 5", 16700, 315, port5);
        tankerTruckList.add(tankerTruck1);
        tankerTruckList.add(tankerTruck2);
        tankerTruckList.add(tankerTruck3);
        tankerTruckList.add(tankerTruck4);
        tankerTruckList.add(tankerTruck5);
    }
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

    public static int login() {
        int choice = -1;
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
        return choice;
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

            if (portOption2.equals("0")) {
                break;
            } else {
                if (portIDs2.contains(portOption2)) {
                    System.out.printf("The distance between 2 ports is: %.2f km\n", portList.get(portIDs.indexOf(portOption)).calculateDistance(portList.get(portIDs2.indexOf(portOption2))));
                    break;
                } else {
                    System.out.println("Please choose a valid option");
                }
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
    public static void removeVehicle(Port port) {
        decorativeLine();
        System.out.println();
        boolean running4 = true;
        Iterator<Vehicle> vehicleIterator = port.getVehicles().iterator();
        do {
            for (Vehicle vehicle : port.getVehicles()) {
                System.out.println(vehicle.getVehicleID() + ". " + vehicle.getName());
            }
            System.out.println("0. Go back");
            System.out.println("Enter the vehicle ID that you want to remove");
            String containerRemoved = scanner.nextLine();

            if (containerRemoved.equals("0")) {
                break;
            }

            boolean vehicleFound = false;
            while (vehicleIterator.hasNext()) {
                Vehicle vehicle = vehicleIterator.next();
                if (containerRemoved.equals(vehicle.getVehicleID())) {
                    vehicleIterator.remove();
                    vehicleFound = true;
                    running4 = false;
                    System.out.println("Vehicle is removed");
                    break;
                }
            }

            if (!vehicleFound) {
                System.out.println("The vehicle does not exist");
            }
        } while (running4);
    }
    public static void addVehicle(List<String> vehicleIDs, Port port) {
        decorativeLine();
        System.out.println();
        int choice = -1;
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
                new Ship(vehicleID, vehicleName, vehicleCurrentWeight, fuelCapacity, port);
                System.out.println("New vehicle has been added");
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
                new Truck(vehicleID, vehicleName, vehicleCurrentWeight, fuelCapacity, port);
                System.out.println("New vehicle has been added");
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
                new ReeferTruck(vehicleID, vehicleName, vehicleCurrentWeight, fuelCapacity, port);
                System.out.println("New vehicle has been added");
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
                new TankerTruck(vehicleID, vehicleName, vehicleCurrentWeight, fuelCapacity, port);
                System.out.println("New vehicle has been added");
                break;
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Please enter from 1-5");
            }
        } while (true);
    }
    public static void searchVehicle(Port port) {
        int choice = -1;
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
                portLandingAbility = scanner.nextBoolean();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid value");
                scanner.nextLine();
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
    public static void portOptions(List<String> portIDs, List<String> vehicleIDs, String portOption, List<String> containerIDs, Port port) {
        boolean running3 = true;
        int choice = 0;
        do {
            //Menu
            decorativeLine();
            System.out.println();
            System.out.println("1. Calculate distance \t|\t\t2. Add Container \t\t|\t\t3. Remove Container");
            System.out.println("4. Add Vehicle \t\t\t|\t\t5. Remove Vehicle \t\t|\t\t6. Search Vehicle");
            System.out.println("7. null\t\t\t|\t\t8. Load Container \t\t|\t\t9. Display Trips");
            System.out.println("10. Display Vehicles\t|\t\t11. Display Containers \t|\t\t12. Go Back");
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
                    removeVehicle(port);
                    break;
                //Search vehicle
                case 6:
                    searchVehicle(port);
                    break;
                //Unload
                case 7:
                    break;
                //Load
                case 8:
                    do {
                        List<String> portVehicleIDs = new ArrayList<>();
                        for (Vehicle vehicle : port.getVehicles()) {
                            System.out.println(vehicle.getVehicleID() + ". " + vehicle.getName());
                            portVehicleIDs.add(vehicle.getVehicleID());
                        }
                        System.out.println("0. Go back");
                        System.out.println("Please choose the vehicle ID that you want to unload containers from:");
                        String vehicleID = scanner.nextLine();

                        if (vehicleID.equals("0")) {
                            break;
                        } else {
                            for (Vehicle vehicle : port.getVehicles()) {
                                if (vehicleID.equals(vehicle.getVehicleID())) {
                                    boolean running4 = true;
                                    do {
                                        for (Container container : port.getContainers()) {
                                            System.out.println(container.getContainerID() + ". " + container.getType());
                                        }
                                        System.out.println("0. Go back");
                                        System.out.println("Enter the container ID you want to load to the vehicle:");
                                        String containerID = scanner.nextLine();
                                        if (containerID.equals("0")) {
                                            break;
                                        } else {
                                            if (!containerIDs.contains(containerID)) {
                                                System.out.println("The container does not exist");
                                            } else {
                                                for (Container container : port.getContainers()) {
                                                    if (containerID.equals(container.getContainerID())) {
                                                        if (vehicle.loadContainer(container)) {
                                                            System.out.println("The container is loaded to the vehicle");
                                                            running4 = false;
                                                            break;
                                                        } else {
                                                            System.out.println("This container can not be loaded on this vehicle!");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } while (running4);
                                }
                            }
                        }
                    } while (true);
                    break;
                //Display trips
                case 9:
                    System.out.println(port.getTrips());
                    break;
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
    public static void choosePort(List<String> portIDs) {
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

            if (portOption.equals("0")) {
                break;
            } else {
                if (!portIDs.contains(portOption)) {
                    System.out.println("Port does not exist");
                } else {
                    for (Port port : portList) {
                        if (portOption.equals(port.getPortID())) {
                            portOptions(portIDs, vehicleIDs, portOption, containerIDs, port);
                        }
                    }
                }
            }
        } while (true);
    }
    public static void loginMainMenu(String indicator) {
        //validate login
        if (!indicator.equals("invalid")) {
            System.out.println("Welcome " + indicator);
            //Admin
            boolean running2 = true;
            int choice = -1;
            do {
                //Menu
                decorativeLine();
                System.out.println();
                System.out.println("1. Choose port");
                System.out.println("2. Add port");
                System.out.println("3. Remove port");
                System.out.println("4. Transportation");
                System.out.println("5. Register");
                System.out.println("6. Statistics");
                System.out.println("7. Go back");
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
                        choosePort(portIDs);
                        break;
                    //Add port
                    case 2:
                        addPort(portIDs);
                        break;
                    //Remove port
                    case 3:
                        removePort(portIDs);
                        break;

                    case 4:
                        boolean running3 = true;
                        choice = -1;
                        do {
                            System.out.println("1. Ship");
                            System.out.println("2. Truck");
                            System.out.println("3. Reefer Truck");
                            System.out.println("4. Tanker Truck");
                            System.out.println("5. Go back");
                            try {
                                System.out.println("Please choose the type vehicle that you want to do the transportation:");
                                choice = Integer.parseInt(scanner.nextLine());
                            } catch (Exception e) {
                                System.out.println("Invalid option");
                            }

                            switch (choice) {
                                case 1:
                                    for (Ship ship : shipList) {
                                        System.out.println(ship.getVehicleID() + " " + ship.getPort());
                                    }
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    running3 = false;
                                    break;
                                default:
                                    System.out.println("Please choose from 1-5");
                                    break;
                            }
                        } while (running3);
                        break;
                    //Go back
                    case 6:
                        boolean running4 = true;
                        choice = -1;
                        do {
                            decorativeLine();
                            System.out.println();
                            System.out.println("1. Display all ports");
                            System.out.println("2. Display all containers");
                            System.out.println("3. Display all vehicles");
                            System.out.println("4. Go back");
                            try {
                                System.out.print("Your option: ");
                                choice = Integer.parseInt(scanner.nextLine());
                            } catch (Exception e) {
                                System.out.println("Please choose a valid option: ");
                            }
                            switch (choice){
                                case 1:
                                    for (Port port : portList){
                                        decorativeLine();
                                        System.out.println();
                                        System.out.println(port);
                                    }
                                    break;
                                case 2:
                                    for (Container container : containerList){
                                        decorativeLine();
                                        System.out.println();
                                        System.out.println(container);
                                    }
                                    break;
                                case 3:
                                    for (Vehicle vehicle : shipList){
                                        decorativeLine();
                                        System.out.println();
                                        System.out.println(vehicle);
                                    }

                                    break;
                                case 4:
                                    running4 = false;
                                    break;
                                default:
                                    System.out.println("Please choose from 1-4");
                                    break;
                            }
                        } while (running4);

                        break;
                    case 7:
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
                } else {
                    return "manager";
                }
            }
        }

        // If no matching username or invalid password, return "invalid" as a flag.
        return "invalid";
    }
}