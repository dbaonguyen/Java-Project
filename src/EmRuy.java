import Entities.*;
import Users.Admin;
import Users.PortManager;
import Users.User;
import java.io.*;
import java.util.*;

public class EmRuy {
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
    public static void emruy() {

        //data initialization
        userList.add(new Admin("1","1"));
        userList.add(new User("2", "2"));
        userList.add(new PortManager("3", "3"));

        typeList.add(new Type("Dry storage", 3.5, 4.6));
        typeList.add(new Type("Open top", 2.8, 3.2));
        typeList.add(new Type("Open side", 2.7, 3.2));
        typeList.add(new Type("Refrigerated", 4.5, 5.4));
        typeList.add(new Type("Liquid", 4.8, 5.3));


        containerList.add(new Container("c-1", 5.0, typeList.get(0)));  // Dry storage
        containerList.add(new Container("c-2", 7.2, typeList.get(1)));  // Open top
        containerList.add(new Container("c-3", 4.3, typeList.get(2)));  // Open side
        containerList.add(new Container("c-4", 6.1, typeList.get(3)));  // Refrigerated
        containerList.add(new Container("c-5", 3.9, typeList.get(4)));  // Liquid

        //Ports
        portList.add(new Port("p-1", "Harbor Bay", 34.0522, -118.2437, 1500.0,500.0 , true));
        portList.add(new Port("p-2", "Marine City", 40.7128, -74.0060, 1800.0,700.0 , false));
        portList.add(new Port("p-3", "Ocean View", -33.8688, 151.2093, 2000.0,800.0 , true));
        portList.add(new Port("p-4", "Seaside Haven", 37.7749, -122.4194, 1200.0,400.0 , true));
        portList.add(new Port("p-5", "Island Port", 21.3069, -157.8583, 1600.0,600.0 , false));

        portList.get(0).addContainer(new Container("c-1", 5.0, typeList.get(0)));
        portList.get(1).addContainer(new Container("c-2", 7.2, typeList.get(1)));
        portList.get(2).addContainer(new Container("c-3", 4.3, typeList.get(2)));
        portList.get(3).addContainer(new Container("c-4", 6.1, typeList.get(3)));
        portList.get(4).addContainer(new Container("c-5", 3.9, typeList.get(4)));

        portList.get(0).addVehicle(new Ship("S1", "Ship 1", 10000.0, 2000.0, 20000.0, 4000.0, portList.get(0)));
        portList.get(1).addVehicle(new Ship("S2", "Ship 2", 15000.0, 3000.0, 25000.0, 5000.0, portList.get(1)));
        portList.get(2).addVehicle(new Ship("S3", "Ship 3", 12000.0, 2500.0, 22000.0, 4500.0, portList.get(2)));

        portList.get(0).addVehicle(new Truck("T1", "Truck 1", 5000.0, 500.0, 10000.0, 1000.0, portList.get(0)));
        portList.get(1).addVehicle(new Truck("T2", "Truck 2", 6000.0, 600.0, 11000.0, 1100.0, portList.get(1)));
        portList.get(2).addVehicle(new Truck("T3", "Truck 3", 7000.0, 700.0, 12000.0, 1200.0, portList.get(2)));

        portList.get(0).addVehicle(new ReeferTruck("RT1", "Reefer Truck 1", 6000.0, 600.0, 12000.0, 1200.0, portList.get(0)));
        portList.get(1).addVehicle(new ReeferTruck("RT2", "Reefer Truck 2", 7000.0, 700.0, 13000.0, 1300.0, portList.get(1)));
        portList.get(2).addVehicle(new ReeferTruck("RT3", "Reefer Truck 3", 8000.0, 800.0, 14000.0, 1400.0, portList.get(2)));

        portList.get(0).addVehicle(new TankerTruck("TT1", "Tanker Truck 1", 7000.0, 700.0, 14000.0, 1400.0, portList.get(0)));
        portList.get(1).addVehicle(new TankerTruck("TT2", "Tanker Truck 2", 8000.0, 800.0, 15000.0, 1500.0, portList.get(1)));
        portList.get(2).addVehicle(new TankerTruck("TT3", "Tanker Truck 3", 9000.0, 900.0, 16000.0, 1600.0, portList.get(2)));

        writeListToFile(portList, "portList.ser");
        writeListToFile(userList, "userList.ser");
        writeListToFile(containerList, "containerList.ser");
        writeListToFile(shipList, "shipList.ser");
        writeListToFile(truckList, "truckList.ser");
        writeListToFile(reeferTruckList, "reeferTruckList.ser");
        writeListToFile(tankerTruckList, "tankerTruckList.ser");
        writeListToFile(typeList, "typeList.ser");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        //Login
        do {
            int choice = -1;
            System.out.println("1. Login");
            System.out.println("2. Exit");
            try {
                System.out.print("Your option: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Please choose a valid option");
            }

            switch (choice) {
                //Username and Password
                case 1:
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();

                    String indicator = loginValidation(username, password);
                    if (!indicator.equals("invalid")) {
                        System.out.println("Welcome " + indicator);
                        //Admin
                        boolean running2 = true;
                        do {
                            //Menu
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
                                                                List<String> portIDs2 = new ArrayList<>();
                                                                do {
                                                                    for (Port portFrom : portList) {
                                                                        System.out.println(portFrom.getPortID() + ". " + portFrom.getName());
                                                                        portIDs2.add(portFrom.getPortID());
                                                                    }
                                                                    System.out.println("0. Go back");
                                                                    System.out.print("Enter the ID of the port above that you want to calculate distance between: ");
                                                                    String portOption2 = scanner.nextLine();

                                                                    //Method
                                                                    if (portIDs2.contains(portOption2)) {
                                                                        System.out.println("The distance between 2 ports is: " + portList.get(portIDs.indexOf(portOption)).calculateDistance(portList.get(portIDs2.indexOf(portOption2))) + "km");
                                                                        break;
                                                                    } else {
                                                                        System.out.println("Please choose a valid option");
                                                                    }

                                                                    if (portOption2.equals("0")) {
                                                                        break;
                                                                    }
                                                                } while (true);
                                                                break;

                                                            //Add container
                                                            case 2:
                                                                String containerID;
                                                                do {
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
                                                                        System.out.println("Please enter a valid value");
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
                                                                        System.out.println("Please enter a valid value");
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
                                                                            System.out.println("Please enter a valid value (0-4)");
                                                                        }
                                                                    } catch (Exception e) {
                                                                        System.out.println("Please enter a valid value");
                                                                    }
                                                                } while (true);

                                                                port.addContainer(new Container(containerID, containerWeight, typeList.get(typeID)));
                                                                System.out.println(containerIDs);
                                                                break;

                                                            //Remove container
                                                            case 3:
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
                                                                break;

                                                            //Add vehicles
                                                            case 4:
                                                                do {
                                                                    try {
                                                                        System.out.println("Choose the vehicle you want to add");
                                                                        System.out.println("1. Ship");
                                                                        System.out.println("2. Truck");
                                                                        System.out.println("3. Reefer Truck");
                                                                        System.out.println("4. Tanker Truck");
                                                                        System.out.println("5. Go back");
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
                                                                                System.out.println("Please enter a valid value");
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
                                                                                System.out.println("Please enter a valid value");
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
                                                                                System.out.println("Please enter a valid value");
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
                                                                                System.out.println("Please enter a valid value");
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
                                                                                System.out.println("Please enter a valid value");
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
                                                                                System.out.println("Please enter a valid value");
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
                                                                                System.out.println("Please enter a valid value");
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
                                                                                System.out.println("Please enter a valid value");
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
                                                                                System.out.println("Please enter a valid value");
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
                                                                                System.out.println("Please enter a valid value");
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
                                                                                System.out.println("Please enter a valid value");
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
                                                                                System.out.println("Please enter a valid value");
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
                                                                break;

                                                            //Remove Vehicle
                                                            case 5:
                                                                boolean running6 = true;
                                                                Iterator<Vehicle> vehicleIterator = port.getVehicles().iterator();
                                                                do {
                                                                    for (Vehicle vehicle : port.getVehicles()) {
                                                                        System.out.println(vehicle.getVehicleID() + ". " + vehicle.getName());
                                                                    }
                                                                    System.out.println("0. Go back");
                                                                    System.out.println("Enter the vehicle ID that you want to remove");
                                                                    String vehicleRemoved = scanner.nextLine();

                                                                    if (vehicleRemoved.equals("0")) {
                                                                        break;
                                                                    }

                                                                    boolean vehicleFound = false;
                                                                    while (vehicleIterator.hasNext()) {
                                                                        Vehicle vehicle = vehicleIterator.next();
                                                                        if (vehicleRemoved.equals(vehicle.getVehicleID())) {
                                                                            vehicleIterator.remove();
                                                                            vehicleFound = true;
                                                                            running6 = false;
                                                                            System.out.println("Vehicle is removed");
                                                                            break;
                                                                        }
                                                                    }

                                                                    if (!vehicleFound) {
                                                                        System.out.println("The vehicle does not exist");
                                                                    }
                                                                } while (running6);
                                                                break;

                                                            //Search vehicle
                                                            case 6:
                                                                choice = -1;
                                                                do {
                                                                    try {
                                                                        System.out.println("1. By ID");
                                                                        System.out.println("2. By Name");
                                                                        System.out.println("3. Go back");
                                                                        choice = Integer.parseInt(scanner.nextLine());
                                                                    } catch (Exception e) {
                                                                        System.out.println("Please choose a valid option");
                                                                    }
                                                                    if (choice == 1) {
                                                                        System.out.println("Please enter the ID of the vehicle that you want to find");
                                                                        String vehicleID = scanner.nextLine();
                                                                        port.searchVehicleById(vehicleID);
                                                                        break;
                                                                    } else if (choice == 2) {
                                                                        System.out.println("Please enter the name of the vehicle that you want to find");
                                                                        String vehicleName = scanner.nextLine();
                                                                        port.searchVehicleByName(vehicleName);
                                                                        break;
                                                                    } else if (choice == 3) {
                                                                        break;
                                                                    } else {
                                                                        System.out.println("Please enter from 1-3");
                                                                    }
                                                                } while (true);
                                                                break;

                                                            //Add Trip
                                                            case 7:
                                                                break;

                                                            //Remove Trip
                                                            case 8:

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
                                            System.out.println("Please enter a valid value");
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
                                    break;

                                //Remove port
                                case 3:
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
                                    break;

                                //Go back
                                case 4:
                                    running2 = false;
                                    break;

                                default:
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

    public static String loginValidation (String enteredUsername, String enteredPassword) {
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