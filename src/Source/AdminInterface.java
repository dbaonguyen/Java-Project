package Source;

import Entities.*;
import Users.Admin;
import Users.PortManager;
import Users.User;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AdminInterface {
    //data load
    public static List<User> userList = readListFromFile("userList.ser");
    public static List<Port> portList = readListFromFile("portList.ser");
    public static List<Container> containerList = readListFromFile("containerList.ser");
    public static List<Ship> shipList = readListFromFile("shipList.ser");
    public static List<Truck> truckList = readListFromFile("truckList.ser");
    public static List<ReeferTruck> reeferTruckList = readListFromFile("reeferTruckList.ser");
    public static List<TankerTruck> tankerTruckList = readListFromFile("tankerTruckList.ser");
    public static List<Type> typeList = readListFromFile("typeList.ser");
    public static List<String> usedUsername = readListFromFile("usedUsername.ser");
    public static List<String> usedPortID = readListFromFile("usedPortID.ser");
    public static List<Trip> tripList = readListFromFile("tripList.ser");
    public static List<String> notificationList = readListFromFile("notificationList.ser");
    public static List<String> containerIDs = readListFromFile("containerIDs.ser");
    public static List<String> vehicleIDs = readListFromFile("vehicleIDs.ser");
    public static List<String> portIDs = readListFromFile("portIDs.ser");
//    public static List<User> userList = new ArrayList<>();
//    public static List<Port> portList = new ArrayList<>();
//    public static List<Container> containerList = new ArrayList<>();
//    public static List<Ship> shipList = new ArrayList<>();
//    public static List<Truck> truckList = new ArrayList<>();
//    public static List<ReeferTruck> reeferTruckList = new ArrayList<>();
//    public static List<TankerTruck> tankerTruckList = new ArrayList<>();
//    public static List<Type> typeList = new ArrayList<>();
//    public static List<String> usedUsername = new ArrayList<>();
//    public static List<String> usedPortID = new ArrayList<>();
//    public static List<Trip> tripList = new ArrayList<>();
//    public static List<String> notificationList = new ArrayList<>();
//    public static List<String> containerIDs = new ArrayList<>();
//    public static List<String> vehicleIDs = new ArrayList<>();
//    public static List<String> portIDs = new ArrayList<>();

    private static final String DEFAULT_DIRECTORY = "Data";
    private static final Scanner scanner = new Scanner(System.in);
    public static void run() {
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
        usedPortID.add(port1.getPortID());
        usedPortID.add(port2.getPortID());
        usedPortID.add(port3.getPortID());
        usedPortID.add(port4.getPortID());

        PortManager manager1 = new PortManager("5", "2", port1);
        PortManager manager2 = new PortManager("2", "2", port2);
        PortManager manager3 = new PortManager("3", "3", port3);
        PortManager manager4 = new PortManager("4", "4", port4);

        userList.add(new Admin());
        userList.add(manager1);
        userList.add(manager2);
        userList.add(manager3);
        userList.add(manager4);

        usedUsername.add(new Admin().getUsername());
        usedUsername.add(manager1.getUsername());
        usedUsername.add(manager2.getUsername());
        usedUsername.add(manager3.getUsername());
        usedUsername.add(manager4.getUsername());

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

        port1.addContainer(c1);
        port3.addContainer(c2);
        port2.addContainer(c3);
        port5.addContainer(c4);
        port4.addContainer(c5);

        port2.addContainer(c6);
        port1.addContainer(c7);
        port3.addContainer(c8);
        port5.addContainer(c9);
        port4.addContainer(c10);

        port3.addContainer(c11);
        port1.addContainer(c12);
        port2.addContainer(c13);
        port4.addContainer(c14);
        port5.addContainer(c15);

        port4.addContainer(c16);
        port5.addContainer(c17);
        port3.addContainer(c18);
        port2.addContainer(c19);
        port1.addContainer(c20);

        port5.addContainer(c21);
        port4.addContainer(c22);
        port3.addContainer(c23);
        port2.addContainer(c24);
        port1.addContainer(c25);

        port1.addContainer(c26);
        port3.addContainer(c27);
        port2.addContainer(c28);
        port4.addContainer(c29);
        port5.addContainer(c30);

        // Create Trucks
        Truck truck1 = new Truck("tr-1", "Truck 1", 10000, 200, port1,true);
        Truck truck2 = new Truck("tr-2", "Truck 2", 12000, 220, port2,true);
        Truck truck3 = new Truck("tr-3", "Truck 3", 11000, 210, port3,true);
        Truck truck4 = new Truck("tr-4", "Truck 4", 10500, 205, port3,true);
        Truck truck5 = new Truck("tr-5", "Truck 5", 9500, 190, port5,true);
        truckList.add(truck1);
        truckList.add(truck2);
        truckList.add(truck3);
        truckList.add(truck4);
        truckList.add(truck5);
        // Create Ships
        Ship ship1 = new Ship("s-1", "Ship 1", 50000, 10000, port1, true);
        Ship ship2 = new Ship("s-2", "Ship 2", 55000, 12000, port4, true);
        Ship ship3 = new Ship("s-3", "Ship 3", 52000, 11000, port5, true);
        Ship ship4 = new Ship("s-4", "Ship 4", 53000, 11200, port4, true);
        Ship ship5 = new Ship("s-5", "Ship 5", 51000, 10800, port4, true);
        shipList.add(ship1);
        shipList.add(ship2);
        shipList.add(ship3);
        shipList.add(ship4);
        shipList.add(ship5);
        // Create ReeferTrucks
        ReeferTruck reeferTruck1 = new ReeferTruck("tr-6", "ReeferTruck 1", 14000, 250, port2,true);
        ReeferTruck reeferTruck2 = new ReeferTruck("tr-7", "ReeferTruck 2", 15000, 270, port1,true);
        ReeferTruck reeferTruck3 = new ReeferTruck("tr-8", "ReeferTruck 3", 14500, 260, port5,true);
        ReeferTruck reeferTruck4 = new ReeferTruck("tr-9", "ReeferTruck 4", 14300, 255, port1,true);
        ReeferTruck reeferTruck5 = new ReeferTruck("tr-10", "ReeferTruck 5", 14700, 265, port3,true);
        reeferTruckList.add(reeferTruck1);
        reeferTruckList.add(reeferTruck2);
        reeferTruckList.add(reeferTruck3);
        reeferTruckList.add(reeferTruck4);
        reeferTruckList.add(reeferTruck5);
        // Create TankerTrucks
        TankerTruck tankerTruck1 = new TankerTruck("tr-11", "TankerTruck 1", 16000, 300, port3,true);
        TankerTruck tankerTruck2 = new TankerTruck("tr-12", "TankerTruck 2", 17000, 320, port2,true);
        TankerTruck tankerTruck3 = new TankerTruck("tr-13", "TankerTruck 3", 16500, 310, port2,true);
        TankerTruck tankerTruck4 = new TankerTruck("tr-14", "TankerTruck 4", 16300, 305, port1,true);
        TankerTruck tankerTruck5 = new TankerTruck("tr-15", "TankerTruck 5", 16700, 315, port5,true);
        tankerTruckList.add(tankerTruck1);
        tankerTruckList.add(tankerTruck2);
        tankerTruckList.add(tankerTruck3);
        tankerTruckList.add(tankerTruck4);
        tankerTruckList.add(tankerTruck5);
        for (Port port : portList) {
            portIDs.add(port.getPortID());
            for (Container container : port.getContainers()) {
                containerIDs.add(container.getContainerID());
            }
            for (Vehicle vehicle : port.getVehicles()) {
                vehicleIDs.add(vehicle.getVehicleID());
            }
        }
//        public static List<Port> portList = new ArrayList<>();
//        public static List<Container> containerList = new ArrayList<>();
//        public static List<Ship> shipList = new ArrayList<>();
//        public static List<Truck> truckList = new ArrayList<>();
//        public static List<ReeferTruck> reeferTruckList = new ArrayList<>();
//        public static List<TankerTruck> tankerTruckList = new ArrayList<>();
//        public static List<Type> typeList = new ArrayList<>();
//        public static List<String> usedUsername = new ArrayList<>();
//        public static List<String> usedPortID = new ArrayList<>();
//        public static List<Trip> tripList = new ArrayList<>();
//        public static List<String> notificationList = new ArrayList<>();
//        public static List<String> containerIDs = new ArrayList<>();
//        public static List<String> vehicleIDs = new ArrayList<>();
//        public static List<String> portIDs = new ArrayList<>();
//        writeListToFile(userList, "userList.ser");
//        writeListToFile(portList, "portList.ser");
//        writeListToFile(containerList, "containerList.ser");
//        writeListToFile(shipList, "shipList.ser");
//        writeListToFile(truckList, "truckList.ser");
//        writeListToFile(reeferTruckList, "reeferTruckList.ser");
//        writeListToFile(tankerTruckList, "tankerTruckList.ser");
//        writeListToFile(typeList, "typeList.ser");
//        writeListToFile(usedUsername, "usedUsername.ser");
//        writeListToFile(usedPortID, "usedPortID.ser");
//        writeListToFile(tripList, "tripList.ser");
//        writeListToFile(notificationList, "notificationList.ser");
//        writeListToFile(containerIDs, "containerIDs.ser");
//        writeListToFile(vehicleIDs, "vehicleIDs.ser");
//        writeListToFile(portIDs, "portIDs.ser");

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
        System.out.println();
    }
    public static int login() {
        int choice = -1;
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("CONTAINER PORT MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu & Dr. Phong Ngo");
        System.out.println("Group 15: ");
        System.out.println("s3978319 Nguyen Danh Bao");
        System.out.println("s3979411 Nguyen Huu Khoi");
        System.out.println("s3978690 Tran Duc Duy");
        System.out.println("s3978165 Tran Tuan Trung");
        System.out.println("1. Login");
        System.out.println("2. Save and Exit");
        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option");
        }
        return choice;
    }
    public static void displayAllPortManager() {
        for (User user : AdminInterface.userList){
            decorativeLine();
            if (user instanceof PortManager portManager){
                System.out.println(portManager);
            }
        }
    }
    public static int portOptionsMenu(int choice) {
        //Menu
        decorativeLine();
        System.out.println("1. Calculate Distance \t|\t\t2. Add Container \t\t|\t\t3. Remove Container");
        System.out.println("4. Add Vehicle \t\t\t|\t\t5. Remove Vehicle \t\t|\t\t6. Search Vehicle");
        System.out.println("7. Search Container\t\t|\t\t8. Load Container \t\t|\t\t9. Unload Container");
        System.out.println("10. Display Vehicles\t|\t\t11. Display Containers \t|\t\t12. Display Trips");
        System.out.println("13. Refuel Vehicles\t\t|\t\t0. Go back");

        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option: ");
        }
        return choice;
    }
    public static void weightOfEachTypeOfAllContainers() {
        boolean running2 = true;
        do {
            for (int i = 1; i < typeList.size() + 1; i++) {
                System.out.println(i + ". " + typeList.get(i-1).getType());
            }
            int choice = -1;
            try {
                System.out.println("0. Go back");
                System.out.println("Enter the type of container that you want to get weight of: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid option. Please choose again!");
            }
            double total = 0;
            switch (choice) {
                case 1 -> {
                    for (Port port : portList) {
                        total += port.getWeightOfContainerType(typeList.get(0));
                    }
                    System.out.println("The total weight of this type of container is: " + total + " kg");
                }
                case 2 -> {
                    for (Port port : portList) {
                        total += port.getWeightOfContainerType(typeList.get(1));
                    }
                    System.out.println("The total weight of this type of container is: " + total + " kg");
                }
                case 3 -> {
                    for (Port port : portList) {
                        total += port.getWeightOfContainerType(typeList.get(2));
                    }
                    System.out.println("The total weight of this type of container is: " + total + " kg");
                }
                case 4 -> {
                    for (Port port : portList) {
                        total += port.getWeightOfContainerType(typeList.get(3));
                    }
                    System.out.println("The total weight of this type of container is: " + total + " kg");
                }

                case 5 -> {
                    for (Port port : portList) {
                        total += port.getWeightOfContainerType(typeList.get(4));
                    }
                    System.out.println("The total weight of this type of container is: " + total + " kg");
                }
                case 0 -> running2 = false;

                default ->
                        System.out.println("Invalid option");
            }
        } while (running2);
    }
    public static void removeTripsAfterSevenDays() {
        for (Trip trip : tripList) {
            if (System.currentTimeMillis() - trip.getDepartDate().getTime() == 604800000) {
                tripList.remove(trip);
            }
        }
    }
    public static void allTripInGivenDay () {
        for (Port port : portList) {
            tripList.addAll(port.getTrips());
        }
        do {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("0. Go back");
                System.out.println("Please enter the date that you want to display all the trip (dd/MM/yyyy)");
                String givenDateStr = scanner.nextLine();

                if (givenDateStr.equals("0")) {
                    break;
                } else {
                    if (Transportation.isValidDay(givenDateStr)) {
                        Date givenDate = dateFormat.parse(givenDateStr);
                        long givenDateTime = givenDate.getTime();
                        for (Trip trip : tripList) {
                            if (trip.getDepartDate().getTime() > givenDateTime && trip.getDepartDate().getTime() < givenDateTime + 86399999) {
                                decorativeLine();
                                System.out.println(trip);
                            }
                        }
                    } else {
                        System.out.println("Invalid date format. Try again.");
                    }
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Try again.");
            }
        } while (true);
    }
    public static void allTripFromDayAtoB() {
        for (Port port : portList) {
            tripList.addAll(port.getTrips());
        }
        do {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("0. Go back");
                System.out.println("Please enter the date that you want to display all the trip (dd/MM/yyyy)");
                String dayAStr = scanner.nextLine();

                if (dayAStr.equals("0")) {
                    break;
                } else {
                    System.out.println("Please enter the date that you want to display all the trip (dd/MM/yyyy)");
                    String dayBStr = scanner.nextLine();
                    if (Transportation.isValidDay(dayAStr) && Transportation.isValidDay(dayBStr)) {
                        Date dayA = dateFormat.parse(dayAStr);
                        Date dayB = dateFormat.parse(dayBStr);
                        long dayATime = dayA.getTime();
                        long dayBTime = dayB.getTime();
                        for (Trip trip : tripList) {
                            if (trip.getDepartDate().getTime() > dayATime && trip.getDepartDate().getTime() < dayBTime) {
                                System.out.println(trip);
                            }
                        }
                    } else {
                        System.out.println("Invalid date format. Try again.");
                    }
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Try again.");
            }
        } while (true);
    }
    public static void displayAllPort() {
        for (Port port : portList) {
            decorativeLine();
            System.out.println(port);
        }
    }
    public static void displayAllContainer() {
        for (Container container : containerList) {
            decorativeLine();
            System.out.println(container);
        }
    }
    public static void displayAllVehicles() {
        for (Vehicle vehicle : shipList) {
            decorativeLine();
            System.out.println(vehicle);
        }
        for (Vehicle vehicle : truckList) {
            decorativeLine();
            System.out.println(vehicle);
        }
        for (Vehicle vehicle : reeferTruckList) {
            decorativeLine();
            System.out.println(vehicle);
        }
        for (Vehicle vehicle : tankerTruckList) {
            decorativeLine();
            System.out.println(vehicle);
        }
    }
    public static void statisticsMenu() {
        boolean running = true;
        do {
            int choice = -1;
            System.out.println("1. Display all ports");
            System.out.println("2. Display all containers");
            System.out.println("3. Display all vehicles");
            System.out.println("4. Display all port managers");
            System.out.println("5. Display all the trips in a given day");
            System.out.println("6. Display all the trips from day A to day B");
            System.out.println("7. Fuel consumption within a day");
            System.out.println("8. Calculate how much weight of each type of all containers");
            System.out.println("0. Go back");
            try {
                System.out.print("Your option: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Please choose a valid option: ");
            }
            switch (choice) {
                case 1 -> displayAllPort();
                case 2 -> displayAllContainer();
                case 3 -> displayAllVehicles();
                case 4 -> displayAllPortManager();
                case 5 -> allTripInGivenDay();
                case 6 -> allTripFromDayAtoB();
                case 7 -> choosePortToCalculateFuelUsedInADay();
                case 8 -> weightOfEachTypeOfAllContainers();
                case 0 -> running = false;
                default -> System.out.println("Please choose from 1-4");
            }
        } while (running);
    }
    public static void portOptions(Port port) {
        boolean running3 = true;
        do {
            int choice = -1;
            choice = portOptionsMenu(choice);
            switch (choice) {
                //Calculate distance
                case 1 -> port.calculateDistanceInterface();
                //Add container
                case 2 -> Port.addContainer(port);
                //Remove container
                case 3 -> Port.removeContainer(port);
                //Add vehicles
                case 4 -> Port.addVehicle(vehicleIDs, port);
                //Remove Vehicle
                case 5 -> Port.removeVehicle(port);
                //Search vehicle
                case 6 -> Port.searchVehicle(port);
                //Search container
                case 7 -> port.searchContainer();
                //Load
                case 8 -> Port.loadContainer(port, containerIDs);
                //Unload container
                case 9 -> Port.unloadContainer(port);
                //Display vehicles
                case 10 -> {
                    port.displayShips();
                    port.displayTrucks();
                }
                //Display containers
                case 11 -> port.displayContainers();
                //Display trips
                case 12 -> port.displayTrips();
                case 13 -> chooseVehicleToRefuel(port);
                //Go back
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 1-13");
            }
        } while (running3);
    }

    public static void chooseDateToDisplayFuelUsed(Port port){
        String date;
        do {
            System.out.println("Enter the date: (dd/MM/yyyy)");
            date = scanner.nextLine();
            if (date.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                double fuelUsed = port.calculateFuelUsedInADay(date);
                System.out.println("Fuel used on " + date + ": " + fuelUsed + " units");
                break;
            } else {
                System.out.println("Please enter the right date format!");
            }
        } while (true);
    }

    public static void choosePortToCalculateFuelUsedInADay(){
        do {
            //Port IDs
            for (Port port : AdminInterface.portList) {
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
                    for (Port port : AdminInterface.portList) {
                        if (portOption.equals(port.getPortID())) {
                            chooseDateToDisplayFuelUsed(port);
                        }
                    }
                }
            }
        } while (true);
    }


    public static void chooseVehicleToRefuel(Port port){
        decorativeLine();

        do {
            for (Vehicle vehicle : port.getVehicles()) {
                System.out.println(vehicle.getVehicleID() + ". " + vehicle.getName() + " (" + vehicle.getCurrentFuel() + "/" + vehicle.getFuelCapacity() + ")");
            }
            System.out.println("0. Go back");
            System.out.println("Enter the vehicle ID that you want to modify");
            String vehicleChoice = scanner.nextLine();

            if (vehicleChoice.equals("0")) {
                break;
            }


            Vehicle selectedVehicle = null;
            for (Vehicle vehicle : port.getVehicles()) {
                if (vehicleChoice.equals(vehicle.getVehicleID())) {
                    selectedVehicle = vehicle;
                    break;
                }
            }

            if (selectedVehicle != null) {
                selectedVehicle.refuel();
                System.out.println("Refuel successful");
            } else {
                System.out.println("The vehicle does not exist");
            }
        } while (true);
    }
    public static void choosePort() {
        do {
            for (Port port : portList) {
                System.out.println(port.getPortID() + ". " + port.getName());
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
                            portOptions(port);
                        }
                    }
                }
            }
        } while (true);
    }
    public static void modificationMenu() {
        boolean running = true;
        do {
            int choice = updateMethods.updateMenu();
            switch (choice){
                case 1 -> updateMethods.choosePortToUpdate(portIDs);
                case 2 -> updateMethods.choosePortToUpdateVehicle(portIDs);
                case 3 -> updateMethods.choosePortToUpdateContainer(portIDs);
                case 4-> updateMethods.chooseUserToUpdate(usedUsername);
                case 0 -> running = false;
                default -> System.out.println("Please choose from 1-4");
            }
        } while (running);
    }
    public static void transportationMenu() {
        boolean running3 = true;
        do {
            int choice = -1;
            System.out.println("1. Ship \t\t\t\t| \t\t\t2. Truck");
            System.out.println("3. Reefer Truck \t\t| \t\t4. Tanker Truck");
            System.out.println("--------------------0. Go back--------------------");
            try {
                System.out.println("Please choose the type vehicle that you want to do the transportation:");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid option");
            }

            switch (choice) {
                case 1 -> Transportation.transportationShip();
                case 2 -> Transportation.transportationTruck();
                case 3 -> Transportation.transportationReeferTruck();
                case 4 -> Transportation.transportationTankerTruck();
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 0-4");
            }
        } while (running3);
    }
    public static void loginMainMenu() {
        //validate login
        System.out.println("Welcome Admin");
        //Admin
        boolean running2 = true;
        do {
            int choice = -1;
            //Menu
            decorativeLine();
            System.out.println("1. Choose port");
            System.out.println("2. Add port");
            System.out.println("3. Remove port");
            System.out.println("4. Transportation");
            System.out.println("5. Register");
            System.out.println("6. Statistics");
            System.out.println("7. Modification");
            System.out.println("8. Notification");
            System.out.println("0. Go back");
            try {
                System.out.print("Your option: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Please choose a valid option: ");
            }
            decorativeLine();
            switch (choice) {
                //Choose port
                case 1 -> choosePort();
                //Add port
                case 2 -> Port.addPort();
                //Remove port
                case 3 -> Port.removePort();
                case 4 -> transportationMenu();
                case 5 -> User.addUser();
                case 6 -> statisticsMenu();
                case 7 -> modificationMenu();
                case 8 -> displayNotification();
                case 0 -> running2 = false;
                default -> System.out.println("Please choose from 1-4");
            }
        } while (running2);
    }


    public static void displayNotification( ){
        for (String notification: notificationList) {
            System.out.println(notification);
        }
    }
    public static User loginValidation () {
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
                    return new Admin();
                } else {
                    PortManager manager = (PortManager) currentLoginUser;
                    return manager;
                }
            }
        }
        // If no matching username or invalid password, return "invalid" as a flag.
        return null;
    }
}