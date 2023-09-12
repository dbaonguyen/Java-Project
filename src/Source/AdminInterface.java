package Source;

import Entities.*;
import Source.Transportation;
import Users.Admin;
import Users.PortManager;
import Users.User;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AdminInterface {
    //data load
    public static List<User> userList = new ArrayList<>();
    public static List<Port> portList = new ArrayList<>();
    public static List<Container> containerList = new ArrayList<>();
    public static List<Ship> shipList = new ArrayList<>();
    public static List<Truck> truckList = new ArrayList<>();
    public static List<ReeferTruck> reeferTruckList = new ArrayList<>();
    public static List<TankerTruck> tankerTruckList = new ArrayList<>();
    public static List<Type> typeList = new ArrayList<>();
    public static List<String> usedUsername = new ArrayList<>();
    public static List<String> usedPortID = new ArrayList<>();
    public static List<Trip> tripList = new ArrayList<>();
    public static List<String> notificationList = new ArrayList<>();
    public static List<String> containerIDs = new ArrayList<>();
    public static List<String> vehicleIDs = new ArrayList<>();
    public static List<String> portIDs = new ArrayList<>();
    private static final String DEFAULT_DIRECTORY = "Data";
    private static Scanner scanner = new Scanner(System.in);
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
        Truck truck1 = new Truck("tr-001", "Truck 1", 10000, 200, port1);
        Truck truck2 = new Truck("tr-002", "Truck 2", 12000, 220, port2);
        Truck truck3 = new Truck("tr-003", "Truck 3", 11000, 210, port3);
        Truck truck4 = new Truck("tr-004", "Truck 4", 10500, 205, port3);
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
        Ship ship4 = new Ship("s-004", "Ship 4", 53000, 11200, port4);
        Ship ship5 = new Ship("s-005", "Ship 5", 51000, 10800, port4);
        shipList.add(ship1);
        shipList.add(ship2);
        shipList.add(ship3);
        shipList.add(ship4);
        shipList.add(ship5);
        // Create ReeferTrucks
        ReeferTruck reeferTruck1 = new ReeferTruck("rf-001", "ReeferTruck 1", 14000, 250, port2);
        ReeferTruck reeferTruck2 = new ReeferTruck("rf-002", "ReeferTruck 2", 15000, 270, port1);
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
        TankerTruck tankerTruck2 = new TankerTruck("tt-002", "TankerTruck 2", 17000, 320, port2);
        TankerTruck tankerTruck3 = new TankerTruck("tt-003", "TankerTruck 3", 16500, 310, port2);
        TankerTruck tankerTruck4 = new TankerTruck("tt-004", "TankerTruck 4", 16300, 305, port1);
        TankerTruck tankerTruck5 = new TankerTruck("tt-005", "TankerTruck 5", 16700, 315, port5);
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
    public static boolean isValidDate(String input) {
        // Define a regular expression pattern to match a valid date format
        String dateFormatPattern = "\\d{1,2}/\\d{1,2}/\\d{4} \\d{2}:\\d{2}:\\d{2}";

        // Check if the input matches the pattern
        if (!input.matches(dateFormatPattern)) {
            return false;
        }

        // If it matches the pattern, try to parse it as a date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setLenient(false); // Disable leniency to enforce strict date parsing

        try {
            Date parsedDate = sdf.parse(input);
            // If parsing succeeds without exceptions, it's a valid date
            return true;
        } catch (ParseException e) {
            // Parsing failed, so it's an invalid date
            return false;
        }
    }
    public static boolean isValidDay(String input) {
        // Define a regular expression pattern to match a valid date format
        String dateFormatPattern = "\\d{1,2}/\\d{1,2}/\\d{4}";

        // Check if the input matches the pattern
        if (!input.matches(dateFormatPattern)) {
            return false;
        }

        // If it matches the pattern, try to parse it as a date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Disable leniency to enforce strict date parsing

        try {
            Date parsedDate = sdf.parse(input);
            // If parsing succeeds without exceptions, it's a valid date
            return true;
        } catch (ParseException e) {
            // Parsing failed, so it's an invalid date
            return false;
        }
    }
    public static int login() {
        int choice = -1;
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
    public static int portOptionsMenu(int choice) {
        //Menu
        decorativeLine();
        System.out.println();
        System.out.println("1. Calculate Distance \t|\t\t2. Add Container \t\t|\t\t3. Remove Container");
        System.out.println("4. Add Vehicle \t\t\t|\t\t5. Remove Vehicle \t\t|\t\t6. Search Vehicle");
        System.out.println("7. Search Container\t\t|\t\t8. Load Container \t\t|\t\t9. Unload Container");
        System.out.println("10. Display Vehicles\t|\t\t11. Display Containers \t|\t\t12. Display Trips");
        System.out.println("0. Go Back");

        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option: ");
        }
        return choice;
    }
    public static void allTripInGivenDay () {
        for (int i = 0; i < portList.size(); i++) {
            tripList.addAll(portList.get(i).getTrips());
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
                    if (isValidDay(givenDateStr)) {
                        Date givenDate = dateFormat.parse(givenDateStr);
                        long givenDateTime = givenDate.getTime();
                        for (int i = 0; i < tripList.size(); i++) {
                            if (tripList.get(i).getDepartDate().getTime() > givenDateTime && tripList.get(i).getDepartDate().getTime() < givenDateTime + 86399999) {
                                System.out.println(tripList.get(i));
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
        do {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                for (int i = 0; i < portList.size(); i++) {
                    tripList.addAll(portList.get(i).getTrips());
                }
                System.out.println("0. Go back");
                System.out.println("Please enter the date that you want to display all the trip (dd/MM/yyyy)");
                String dayAStr = scanner.nextLine();

                if (dayAStr.equals("0")) {
                    break;
                } else {
                    System.out.println("Please enter the date that you want to display all the trip (dd/MM/yyyy)");
                    String dayBStr = scanner.nextLine();
                    if (isValidDay(dayAStr) && isValidDay(dayBStr)) {
                        Date dayA = dateFormat.parse(dayAStr);
                        Date dayB = dateFormat.parse(dayBStr);
                        long dayATime = dayA.getTime();
                        long dayBTime = dayB.getTime();
                        for (int i = 0; i < tripList.size(); i++) {
                            if (tripList.get(i).getDepartDate().getTime() > dayATime && tripList.get(i).getDepartDate().getTime() < dayBTime) {
                                System.out.println(tripList.get(i));
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
    public static void statisticsMenu() {
        boolean running = true;
        do {
            int choice = -1;
            System.out.println();
            System.out.println("1. Display all ports");
            System.out.println("2. Display all containers");
            System.out.println("3. Display all vehicles");
            System.out.println("4. Display all the trips in a given day");
            System.out.println("5. Display all the trips from day A to day B");
            System.out.println("6. Fuel consumption within a day");
            System.out.println("7. Calculate how much weight of each type of all containers");
            System.out.println("0. Go back");
            try {
                System.out.print("Your option: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Please choose a valid option: ");
            }
            switch (choice) {
                case 1 -> {
                    for (Port port : portList) {
                        decorativeLine();
                        System.out.println();
                        System.out.println(port);
                    }
                }
                case 2 -> {
                    for (Container container : containerList) {
                        decorativeLine();
                        System.out.println();
                        System.out.println(container);
                    }
                }
                case 3 -> {
                    for (Vehicle vehicle : shipList) {
                        decorativeLine();
                        System.out.println();
                        System.out.println(vehicle);
                    }
                    for (Vehicle vehicle : truckList) {
                        decorativeLine();
                        System.out.println();
                        System.out.println(vehicle);
                    }
                    for (Vehicle vehicle : reeferTruckList) {
                        decorativeLine();
                        System.out.println();
                        System.out.println(vehicle);
                    }
                    for (Vehicle vehicle : tankerTruckList) {
                        decorativeLine();
                        System.out.println();
                        System.out.println(vehicle);
                    }
                }
                case 4 -> allTripInGivenDay();
                case 5 -> allTripFromDayAtoB();
                case 6 -> {
                }
                case 7 -> {
                }
                case 0 -> running = false;
                default -> System.out.println("Please choose from 1-4");
            }
        } while (running);
    }
    public static void portOptions(String portOption, Port port) {
        boolean running3 = true;
        int choice = 0;
        do {
            choice = portOptionsMenu(choice);
            switch (choice) {
                //Calculate distance
                case 1 -> Port.calculateDistance(port, portOption);
                //Add container
                case 2 -> Port.addContainer(port);
                //Remove container
                case 3 -> {
                    Port.removeContainer(port);
                }
                //Add vehicles
                case 4 -> Port.addVehicle(vehicleIDs, port);
                //Remove Vehicle
                case 5 -> {
                    Port.removeVehicle(port);
                }
                //Search vehicle
                case 6 -> Port.searchVehicle(port);
                //Search container
                case 7 -> {
                    System.out.println("Please enter the id of the container that you want to search:");
                    String searchContainerID = scanner.nextLine();
                    port.searchContainer(searchContainerID);
                }
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
                case 11 -> System.out.println(port.getContainers());
                //Display trips
                case 12 -> System.out.println(port.getTrips());
                //Go back
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 1-13");
            }
        } while (running3);
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
                            portOptions(portOption, port);
                        }
                    }
                }
            }
        } while (true);
    }
    public static void transportationShip() {
        do {
            List<String> shipIDs = new ArrayList<>();
            for (Ship ship : shipList) {
                if (ship.getPort() == null) {
                    System.out.println(ship.getVehicleID() +". " + ship.getPort());
                } else {
                    System.out.println(ship.getVehicleID() +". "+ ship.getPort().getName());
                }
                shipIDs.add(ship.getVehicleID());
            }
            System.out.println("0. Go back");
            System.out.println("Please enter the ID of the ship that you want to do the transportation:");
            String shipID = scanner.nextLine();

            if (shipID.equals("0")) {
                break;
            } else {
                if (shipIDs.contains(shipID)) {
                    for (Ship ship : shipList) {
                        if (shipID.equals(ship.getVehicleID())) {
                            do {
                                List<String> portCanMoveToIDs = new ArrayList<>();
                                for (Port port : portList) {
                                    if (port != ship.getPort()) {
                                        System.out.println(port.getPortID() + ". " + port.getName());
                                        portCanMoveToIDs.add(port.getPortID());
                                    }
                                }
                                System.out.println("0. Go back");
                                System.out.println("Please enter the ID of the port that you want the ship to move to:");
                                String portCanMoveToID = scanner.nextLine();

                                if (portCanMoveToID.equals("0")) {
                                    break;
                                } else {
                                    if (portCanMoveToIDs.contains(portCanMoveToID)) {
                                        for (Port port : portList) {
                                            if (portCanMoveToID.equals(port.getPortID())) {
                                                do {
                                                    try {
                                                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                                        Date currentTime = new Date(); // Get the current time

                                                        System.out.println("Please enter the departure date (dd/MM/yyyy HH:mm:ss)");
                                                        String departureDateStr = scanner.nextLine();

                                                        System.out.println("Please enter the arrival date (dd/MM/yyyy HH:mm:ss)");
                                                        String arrivalDateStr = scanner.nextLine();

                                                        if (isValidDate(departureDateStr) && isValidDate(arrivalDateStr)) {
                                                            Date departureDate = dateFormat.parse(departureDateStr);
                                                            Date arrivalDate = dateFormat.parse(arrivalDateStr);

                                                            if (departureDate.compareTo(currentTime) >= 0 && arrivalDate.compareTo(departureDate) > 0) {
                                                                long departureDelayMillis = departureDate.getTime() - System.currentTimeMillis();
                                                                long arrivalDelayMillis = arrivalDate.getTime() - System.currentTimeMillis();

                                                                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                                                                //make a new trip without adding to any port
                                                                CompletableFuture<Trip> tripFuture = new CompletableFuture<>();
                                                                // Schedule departure action
                                                                Transportation departureTask = new Transportation(ship, port, departureDate, arrivalDate);
                                                                ScheduledFuture<?> departureFuture = scheduler.schedule(() -> {
                                                                    Trip trip = departureTask.run();
                                                                    tripFuture.complete(trip); // Store the Trip object in the CompletableFuture
                                                                }, departureDelayMillis, TimeUnit.MILLISECONDS);
                                                                ScheduledFuture<?> arrivalFuture = scheduler.schedule(() -> {
                                                                    try {
                                                                        // Wait for the departure task to complete and retrieve the Trip object
                                                                        Trip trip = tripFuture.get();
                                                                        // Now you have the Trip object, you can use it in the arrival task
                                                                        Transportation arrivalTask = new Transportation(ship, port, departureDate, arrivalDate);
                                                                        arrivalTask.run2(trip); // Pass the Trip object as a parameter to run2
                                                                    } catch (InterruptedException | ExecutionException e) {
                                                                        // Handle exceptions if needed
                                                                    }
                                                                }, arrivalDelayMillis, TimeUnit.MILLISECONDS);

                                                                break;
                                                            } else {
                                                                System.out.println("Invalid dates. Departure date must be >= current time, and arrival date must be > departure date.");
                                                            }
                                                        } else {
                                                            System.out.println("Invalid date format. Try again.");
                                                        }
                                                    } catch (ParseException e) {
                                                        System.out.println("Invalid date format. Try again.");
                                                    }
                                                } while (true);
                                                System.out.println("The transportation procedure is completed!");
                                            }
                                        }
                                        break;
                                    } else {
                                        System.out.println("Port does not exist");
                                    }
                                }
                            } while (true);
                        }
                    }
                } else {
                    System.out.println("The ship does not exist");
                }
            }
        } while (true);
    }
    public static void transportationTruck() {
        do {
            List<String> truckIDs = new ArrayList<>();
            for (Truck truck : truckList) {
                if (truck.getPort() == null) {
                    System.out.println(truck.getVehicleID() +". " + truck.getPort());
                } else {
                    System.out.println(truck.getVehicleID() +". "+ truck.getPort().getName());
                }
                truckIDs.add(truck.getVehicleID());
            }
            System.out.println("0. Go back");
            System.out.println("Please enter the ID of the truck that you want to do the transportation:");
            String truckID = scanner.nextLine();

            if (truckID.equals("0")) {
                break;
            } else {
                if (truckIDs.contains(truckID)) {
                    for (Truck truck : truckList) {
                        if (truckID.equals(truck.getVehicleID())) {
                            do {
                                List<String> portCanMoveToIDs = new ArrayList<>();
                                for (Port port : portList) {
                                    if (port != truck.getPort()) {
                                        System.out.println(port.getPortID() + ". " + port.getName());
                                        portCanMoveToIDs.add(port.getPortID());
                                    }
                                }
                                System.out.println("0. Go back");
                                System.out.println("Please enter the ID of the port that you want the truck to move to:");
                                String portCanMoveToID = scanner.nextLine();

                                if (portCanMoveToID.equals("0")) {
                                    break;
                                } else {
                                    if (portCanMoveToIDs.contains(portCanMoveToID)) {
                                        for (Port port : portList) {
                                            if (portCanMoveToID.equals(port.getPortID())) {
                                                do {
                                                    try {
                                                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                                        Date currentTime = new Date(); // Get the current time

                                                        System.out.println("Please enter the departure date (dd/MM/yyyy HH:mm:ss)");
                                                        String departureDateStr = scanner.nextLine();

                                                        System.out.println("Please enter the arrival date (dd/MM/yyyy HH:mm:ss)");
                                                        String arrivalDateStr = scanner.nextLine();

                                                        if (isValidDate(departureDateStr) && isValidDate(arrivalDateStr)) {
                                                            Date departureDate = dateFormat.parse(departureDateStr);
                                                            Date arrivalDate = dateFormat.parse(arrivalDateStr);

                                                            if (departureDate.compareTo(currentTime) >= 0 && arrivalDate.compareTo(departureDate) > 0) {
                                                                long departureDelayMillis = departureDate.getTime() - System.currentTimeMillis();
                                                                long arrivalDelayMillis = arrivalDate.getTime() - System.currentTimeMillis();

                                                                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                                                                //make a new trip without adding to any port
                                                                CompletableFuture<Trip> tripFuture = new CompletableFuture<>();
                                                                // Schedule departure action
                                                                Transportation departureTask = new Transportation(truck, port, departureDate, arrivalDate);
                                                                ScheduledFuture<?> departureFuture = scheduler.schedule(() -> {
                                                                    Trip trip = departureTask.run();
                                                                    tripFuture.complete(trip); // Store the Trip object in the CompletableFuture
                                                                }, departureDelayMillis, TimeUnit.MILLISECONDS);
                                                                ScheduledFuture<?> arrivalFuture = scheduler.schedule(() -> {
                                                                    try {
                                                                        // Wait for the departure task to complete and retrieve the Trip object
                                                                        Trip trip = tripFuture.get();
                                                                        // Now you have the Trip object, you can use it in the arrival task
                                                                        Transportation arrivalTask = new Transportation(truck, port, departureDate, arrivalDate);
                                                                        arrivalTask.run2(trip); // Pass the Trip object as a parameter to run2
                                                                    } catch (InterruptedException | ExecutionException e) {
                                                                        // Handle exceptions if needed
                                                                    }
                                                                }, arrivalDelayMillis, TimeUnit.MILLISECONDS);

                                                                break;
                                                            } else {
                                                                System.out.println("Invalid dates. Departure date must be >= current time, and arrival date must be > departure date.");
                                                            }
                                                        } else {
                                                            System.out.println("Invalid date format. Try again.");
                                                        }
                                                    } catch (ParseException e) {
                                                        System.out.println("Invalid date format. Try again.");
                                                    }
                                                } while (true);
                                                System.out.println("The transportation procedure is completed!");
                                            }
                                        }
                                        break;
                                    } else {
                                        System.out.println("Port does not exist");
                                    }
                                }
                            } while (true);
                        }
                    }
                } else {
                    System.out.println("The truck does not exist");
                }
            }
        } while (true);
    }
    public static void transportationReeferTruck() {
        do {
            List<String> reeferTruckIDs = new ArrayList<>();
            for (ReeferTruck reeferTruck : reeferTruckList) {
                if (reeferTruck.getPort() == null) {
                    System.out.println(reeferTruck.getVehicleID() +". " + reeferTruck.getPort());
                } else {
                    System.out.println(reeferTruck.getVehicleID() +". "+ reeferTruck.getPort().getName());
                }
                reeferTruckIDs.add(reeferTruck.getVehicleID());
            }
            System.out.println("0. Go back");
            System.out.println("Please enter the ID of the reefer truck that you want to do the transportation:");
            String reeferTruckID = scanner.nextLine();

            if (reeferTruckID.equals("0")) {
                break;
            } else {
                if (reeferTruckIDs.contains(reeferTruckID)) {
                    for (ReeferTruck reeferTruck : reeferTruckList) {
                        if (reeferTruckID.equals(reeferTruck.getVehicleID())) {
                            do {
                                List<String> portCanMoveToIDs = new ArrayList<>();
                                for (Port port : portList) {
                                    if (port != reeferTruck.getPort()) {
                                        System.out.println(port.getPortID() + ". " + port.getName());
                                        portCanMoveToIDs.add(port.getPortID());
                                    }
                                }
                                System.out.println("0. Go back");
                                System.out.println("Please enter the ID of the port that you want the reefer truck to move to:");
                                String portCanMoveToID = scanner.nextLine();

                                if (portCanMoveToID.equals("0")) {
                                    break;
                                } else {
                                    if (portCanMoveToIDs.contains(portCanMoveToID)) {
                                        for (Port port : portList) {
                                            if (portCanMoveToID.equals(port.getPortID())) {
                                                do {
                                                    try {
                                                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                                        Date currentTime = new Date(); // Get the current time

                                                        System.out.println("Please enter the departure date (dd/MM/yyyy HH:mm:ss)");
                                                        String departureDateStr = scanner.nextLine();

                                                        System.out.println("Please enter the arrival date (dd/MM/yyyy HH:mm:ss)");
                                                        String arrivalDateStr = scanner.nextLine();

                                                        if (isValidDate(departureDateStr) && isValidDate(arrivalDateStr)) {
                                                            Date departureDate = dateFormat.parse(departureDateStr);
                                                            Date arrivalDate = dateFormat.parse(arrivalDateStr);

                                                            if (departureDate.compareTo(currentTime) >= 0 && arrivalDate.compareTo(departureDate) > 0) {
                                                                long departureDelayMillis = departureDate.getTime() - System.currentTimeMillis();
                                                                long arrivalDelayMillis = arrivalDate.getTime() - System.currentTimeMillis();

                                                                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                                                                //make a new trip without adding to any port
                                                                CompletableFuture<Trip> tripFuture = new CompletableFuture<>();
                                                                // Schedule departure action
                                                                Transportation departureTask = new Transportation(reeferTruck, port, departureDate, arrivalDate);
                                                                ScheduledFuture<?> departureFuture = scheduler.schedule(() -> {
                                                                    Trip trip = departureTask.run();
                                                                    tripFuture.complete(trip); // Store the Trip object in the CompletableFuture
                                                                }, departureDelayMillis, TimeUnit.MILLISECONDS);
                                                                ScheduledFuture<?> arrivalFuture = scheduler.schedule(() -> {
                                                                    try {
                                                                        // Wait for the departure task to complete and retrieve the Trip object
                                                                        Trip trip = tripFuture.get();
                                                                        // Now you have the Trip object, you can use it in the arrival task
                                                                        Transportation arrivalTask = new Transportation(reeferTruck, port, departureDate, arrivalDate);
                                                                        arrivalTask.run2(trip); // Pass the Trip object as a parameter to run2
                                                                    } catch (InterruptedException | ExecutionException e) {
                                                                        // Handle exceptions if needed
                                                                    }
                                                                }, arrivalDelayMillis, TimeUnit.MILLISECONDS);

                                                                break;
                                                            } else {
                                                                System.out.println("Invalid dates. Departure date must be >= current time, and arrival date must be > departure date.");
                                                            }
                                                        } else {
                                                            System.out.println("Invalid date format. Try again.");
                                                        }
                                                    } catch (ParseException e) {
                                                        System.out.println("Invalid date format. Try again.");
                                                    }
                                                } while (true);
                                                System.out.println("The transportation procedure is completed!");
                                            }
                                        }
                                        break;
                                    } else {
                                        System.out.println("Port does not exist");
                                    }
                                }
                            } while (true);
                        }
                    }
                } else {
                    System.out.println("The reefer truck does not exist");
                }
            }
        } while (true);
    }
    public static void transportationTankerTruck() {
        do {
            List<String> tankerTruckIDs = new ArrayList<>();
            for (TankerTruck tankerTruck : tankerTruckList) {
                if (tankerTruck.getPort() == null) {
                    System.out.println(tankerTruck.getVehicleID() +". " + tankerTruck.getPort());
                } else {
                    System.out.println(tankerTruck.getVehicleID() +". "+ tankerTruck.getPort().getName());
                }
                tankerTruckIDs.add(tankerTruck.getVehicleID());
            }
            System.out.println("0. Go back");
            System.out.println("Please enter the ID of the tanker truck that you want to do the transportation:");
            String tankerTruckID = scanner.nextLine();

            if (tankerTruckID.equals("0")) {
                break;
            } else {
                if (tankerTruckIDs.contains(tankerTruckID)) {
                    for (TankerTruck tankerTruck : tankerTruckList) {
                        if (tankerTruckID.equals(tankerTruck.getVehicleID())) {
                            do {
                                List<String> portCanMoveToIDs = new ArrayList<>();
                                for (Port port : portList) {
                                    if (port != tankerTruck.getPort()) {
                                        System.out.println(port.getPortID() + ". " + port.getName());
                                        portCanMoveToIDs.add(port.getPortID());
                                    }
                                }
                                System.out.println("0. Go back");
                                System.out.println("Please enter the ID of the port that you want the reefer truck to move to:");
                                String portCanMoveToID = scanner.nextLine();

                                if (portCanMoveToID.equals("0")) {
                                    break;
                                } else {
                                    if (portCanMoveToIDs.contains(portCanMoveToID)) {
                                        for (Port port : portList) {
                                            if (portCanMoveToID.equals(port.getPortID())) {
                                                do {
                                                    try {
                                                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                                        Date currentTime = new Date(); // Get the current time

                                                        System.out.println("Please enter the departure date (dd/MM/yyyy HH:mm:ss)");
                                                        String departureDateStr = scanner.nextLine();

                                                        System.out.println("Please enter the arrival date (dd/MM/yyyy HH:mm:ss)");
                                                        String arrivalDateStr = scanner.nextLine();

                                                        if (isValidDate(departureDateStr) && isValidDate(arrivalDateStr)) {
                                                            Date departureDate = dateFormat.parse(departureDateStr);
                                                            Date arrivalDate = dateFormat.parse(arrivalDateStr);

                                                            if (departureDate.compareTo(currentTime) >= 0 && arrivalDate.compareTo(departureDate) > 0) {
                                                                long departureDelayMillis = departureDate.getTime() - System.currentTimeMillis();
                                                                long arrivalDelayMillis = arrivalDate.getTime() - System.currentTimeMillis();

                                                                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                                                                //make a new trip without adding to any port
                                                                CompletableFuture<Trip> tripFuture = new CompletableFuture<>();
                                                                // Schedule departure action
                                                                Transportation departureTask = new Transportation(tankerTruck, port, departureDate, arrivalDate);
                                                                ScheduledFuture<?> departureFuture = scheduler.schedule(() -> {
                                                                    Trip trip = departureTask.run();
                                                                    tripFuture.complete(trip); // Store the Trip object in the CompletableFuture
                                                                }, departureDelayMillis, TimeUnit.MILLISECONDS);
                                                                ScheduledFuture<?> arrivalFuture = scheduler.schedule(() -> {
                                                                    try {
                                                                        // Wait for the departure task to complete and retrieve the Trip object
                                                                        Trip trip = tripFuture.get();
                                                                        // Now you have the Trip object, you can use it in the arrival task
                                                                        Transportation arrivalTask = new Transportation(tankerTruck, port, departureDate, arrivalDate);
                                                                        arrivalTask.run2(trip); // Pass the Trip object as a parameter to run2
                                                                    } catch (InterruptedException | ExecutionException e) {
                                                                        // Handle exceptions if needed
                                                                    }
                                                                }, arrivalDelayMillis, TimeUnit.MILLISECONDS);

                                                                break;
                                                            } else {
                                                                System.out.println("Invalid dates. Departure date must be >= current time, and arrival date must be > departure date.");
                                                            }
                                                        } else {
                                                            System.out.println("Invalid date format. Try again.");
                                                        }
                                                    } catch (ParseException e) {
                                                        System.out.println("Invalid date format. Try again.");
                                                    }
                                                } while (true);
                                                System.out.println("The transportation procedure is completed!");
                                            }
                                        }
                                        break;
                                    } else {
                                        System.out.println("Port does not exist");
                                    }
                                }
                            } while (true);
                        }
                    }
                } else {
                    System.out.println("The reefer truck does not exist");
                }
            }
        } while (true);
    }
    public static void transportationMenu() {
        boolean running3 = true;
        int choice = -1;
        do {
            System.out.println("1. Ship");
            System.out.println("2. Truck");
            System.out.println("3. Reefer Truck");
            System.out.println("4. Tanker Truck");
            System.out.println("0. Go back");
            try {
                System.out.println("Please choose the type vehicle that you want to do the transportation:");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid option");
            }

            switch (choice) {
                case 1 -> transportationShip();
                case 2 -> transportationTruck();
                case 3 -> transportationReeferTruck();
                case 4 -> transportationTankerTruck();
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 1-5");
            }
        } while (running3);
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
                System.out.println("7. Modification");
                System.out.println("0. Go back");
                try {
                    System.out.print("Your option: ");
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Please choose a valid option: ");
                }
                decorativeLine();
                System.out.println();
                switch (choice) {
                    //Choose port
                    case 1 -> choosePort();
                    //Add port
                    case 2 -> Port.addPort();
                    //Remove port
                    case 3 -> Port.removePort();
                    case 4 -> transportationMenu();
                    case 5 -> User.addUser(usedUsername, usedPortID);
                    case 6 -> statisticsMenu();
                    case 7 ->{
                        boolean running = true;
                        do {
                            choice = updateMenu(choice);
                            switch (choice){
                                case 1 -> {
                                    choosePortToUpdate(portIDs);
                                }
                                case 2 ->{
                                    choosePortToUpdateVehicle(portIDs);
                                }
                                case 3 -> choosePortToUpdateContainer(portIDs);

                                case 4->{
                                    chooseUserToUpdate(usedUsername);
                                }
                                case 0 -> running = false;
                                default -> System.out.println("Please choose from 1-4");
                            }
                        }while (running);
                    }
                    case 0 -> running2 = false;
                    default -> System.out.println("Please choose from 1-4");
                }
            } while (running2);

        } else {
            System.out.println("Incorrect username or password!");
        }
    }
    public static int updateMenu(int choice){

        System.out.println("1. Update Port");
        System.out.println("2. Update Vehicle");
        System.out.println("3. Update Container");
        System.out.println("4. Update Port Manager account");
        System.out.println("0. Go back");
        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option: ");
        }

        return choice;
    }
    public static int portOptionsMenuToUpdate(int choice) {
        //Menu
        decorativeLine();
        System.out.println();
        System.out.println("1. Update Port ID \t|\t\t2. Update Port name \t\t|\t\t3. Update Port latitude");
        System.out.println("4. Update Port longitude \t\t\t|\t\t5. Update Port capacity \t\t|\t\t6. Update Port landing ability");
        System.out.println("0. Go back\t\t|\t\t");

        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option: ");
        }
        return choice;
    }
    public static void portOptionsToUpdate(List<String> portIDs, Port port) {
        boolean running3 = true;
        int choice = 0;
        do {
            choice = portOptionsMenuToUpdate(choice);
            switch (choice) {
                //Update portID
                case 1 -> {
                    String portID;
                    String tempID;

                    do {
                        try {
                            System.out.println("Please enter the port ID by the format 'p-portID': ");
                            portID = scanner.nextLine();

                            if (!portID.matches("p-\\d+")) {
                                System.out.println("Invalid ID. The ID must be in the format 'p-<integer>'.");
                            } else {
                                if (!portIDs.contains(portID)) {
                                    tempID = port.getPortID();
                                    portIDs.add(portID);
                                    portIDs.remove(tempID);
                                    portIDs.remove(tempID);
                                    break;
                                } else {
                                    System.out.println("The ID is already existed!");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid value");
                        }
                    } while (true);
                    port.setPortID(portID);
                    System.out.println("Updated successfully");
                }

                //Update port name
                case 2 -> {
                    System.out.println("Please enter your port name:");
                    String portName = scanner.nextLine();
                    port.setName(portName);
                    System.out.println("Updated successfully");
                }

                //Update port latitude
                case 3 -> {
                    double portLatitude;
                    do {
                        try {
                            System.out.println("Please enter your port latitude:");
                            portLatitude = Double.parseDouble(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid value");
                        }
                    } while (true);
                    port.setLatitude(portLatitude);
                    System.out.println("Updated successfully");

                }

                //Update port longitude
                case 4 -> {
                    double portLongtitude;
                    do {
                        try {
                            System.out.println("Please enter your port longtitude:");
                            portLongtitude = Double.parseDouble(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid value");
                        }
                    } while (true);
                    port.setLongitude(portLongtitude);
                    System.out.println("Updated successfully");
                }

                //Update port capacity
                case 5 -> {
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
                    port.setCapacity(portCapacity);
                    System.out.println("Updated successfully");
                }

                //Update port landing ability
                case 6 -> {
                    boolean portLandingAbility;
                    boolean found = false;
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

                    for (Vehicle vehicle : port.getVehicles()){
                        if (vehicle instanceof Truck || vehicle instanceof ReeferTruck || vehicle instanceof TankerTruck){
                            found = true;
                            System.out.println("The port is containing trucks, please remove all trucks to modify");
                            break;
                        }
                    }

                    if (!found){
                        port.setLandingAbility(portLandingAbility);
                        System.out.println("Updated successfully");
                    }
                }

                //Exit
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 1-12");
            }
        } while (running3);
    }
    public static void choosePortToUpdate(List<String> portIDs) {

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
                            portOptionsToUpdate(portIDs, port);
                        }
                    }
                }
            }
        } while (true);
    }
    public static void vehicleOptions(Port port){
        decorativeLine();
        System.out.println();
        boolean running4 = true;

        do {

            for (Vehicle vehicle : port.getVehicles()) {
                System.out.println(vehicle.getVehicleID() + ". " + vehicle.getName());
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
                portOptionsToUpdateVehicle(port, selectedVehicle);
            } else {
                System.out.println("The vehicle does not exist");
            }
        } while (running4);
    }
    public static int portOptionsMenuToUpdateVehicle(int choice) {
        //Menu

        decorativeLine();
        System.out.println();
        System.out.println("1. Update Vehicle ID \t|\t\t2. Update Vehicle name \t\t|\t\t3. Update Vehicle capacity");
        System.out.println("4. Update Vehicle fuel capacity \t|\t\t0. Go back");


        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option: ");
        }
        return choice;
    }
    public static void portOptionsToUpdateVehicle(Port port, Vehicle vehicle) {
        boolean running3 = true;
        int choice = -1;
        do {
            choice = portOptionsMenuToUpdateVehicle(choice);
            switch (choice) {
                //Update vehicleID
                case 1 -> {
                    String vehicleID;

                    do {
                        try {
                            if (vehicle instanceof Ship) {
                                System.out.println("Please enter the vehicle ID by the format 's-vehicleID': ");
                                vehicleID = scanner.nextLine();

                                if (!vehicleID.matches("s-\\d+")) {
                                    System.out.println("Invalid ID. The ID must be in the format 's-<integer>'.");
                                    continue; // Restart the loop to get a valid ID
                                }
                            } else if (vehicle instanceof Truck || vehicle instanceof ReeferTruck || vehicle instanceof TankerTruck) {
                                System.out.println("Please enter the vehicle ID by the format 'tr-vehicleID': ");
                                vehicleID = scanner.nextLine();

                                if (!vehicleID.matches("tr-\\d+")) {
                                    System.out.println("Invalid ID. The ID must be in the format 'tr-<integer>'.");
                                    continue; // Restart the loop to get a valid ID
                                }
                            } else {

                                break;
                            }

                            boolean idExists = false;
                            for (Vehicle vehicleChoice : port.getVehicles()) {
                                if (vehicleChoice != vehicle && vehicleChoice.getVehicleID().equals(vehicleID)) {
                                    idExists = true;
                                    System.out.println("The ID is already in use!");
                                    break; // No need to check further, the ID already exists
                                }
                            }

                            if (!idExists) {
                                vehicle.setVehicleID(vehicleID);
                                System.out.println("Updated successfully");
                                break; // Exit the loop since a valid, unique ID has been set
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid value");
                        }
                    } while (true);

                }

                //Update vehicle name
                case 2 -> {
                    System.out.println("Please enter the new vehicle name:");
                    String vehicleName = scanner.nextLine();
                    vehicle.setName(vehicleName);
                    // Update the vehicle in the port's list
                    for (Vehicle v : port.getVehicles()) {
                        if (v.getVehicleID().equals(vehicle.getVehicleID())) {
                            v.setName(vehicleName);
                            System.out.println("Updated successfully");
                            break;
                        }
                    }

                }

                //Update vehicle capacity
                case 3 -> {
                    int capacity;
                    do {
                        try {
                            System.out.println("Please enter your vehicle capacity:");
                            capacity = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid value");
                        }
                    } while (true);
                    vehicle.setCapacity(capacity);
                    System.out.println("Updated successfully");

                }

                //Update vehicle fuel capacity
                case 4 -> {
                    double fuelCapacity;
                    do {
                        try {
                            System.out.println("Please enter your vehicle capacity:");
                            fuelCapacity = Double.parseDouble(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid value");
                        }
                    } while (true);
                    vehicle.setFuelCapacity(fuelCapacity);
                    System.out.println("Updated successfully");
                }


                //Exit
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 1-4");
            }
        } while (running3);
    }
    public static void choosePortToUpdateVehicle(List<String> portIDs) {
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
                            vehicleOptions(port);
                        }
                    }
                }
            }
        } while (true);
    }
    public static void containerOptions(Port port){
        decorativeLine();
        System.out.println();
        boolean running4 = true;

        do {

            for (Container container : port.getContainers()) {
                System.out.println(container.getContainerID() + ".");
            }
            System.out.println("0. Go back");
            System.out.println("Enter the container ID that you want to modify");
            String containerChoice = scanner.nextLine();

            if (containerChoice.equals("0")) {
                break;
            }


            Container selectedContainer = null;
            for (Container container : port.getContainers()) {
                if (containerChoice.equals(container.getContainerID())) {
                    selectedContainer = container;
                    break;
                }
            }

            if (selectedContainer != null) {
                portOptionsToUpdateContainer(port, selectedContainer);
            } else {
                System.out.println("The container does not exist");
            }
        } while (running4);
    }
    public static void choosePortToUpdateContainer(List<String> portIDs) {

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
                            containerOptions(port);
                        }
                    }
                }
            }
        } while (true);
    }
    public static void portOptionsToUpdateContainer(Port port, Container container)     {
        boolean running3 = true;
        int choice = -1;
        do {
            choice = portOptionsMenuToUpdateContainer(choice);
            switch (choice) {
                //Update ContainerID
                case 1 -> {
                    String containerID;

                    do {
                        try {
                                System.out.println("Please enter the vehicle ID by the format 'c-containerID': ");
                                containerID = scanner.nextLine();

                                if (!containerID.matches("c-\\d+")) {
                                    System.out.println("Invalid ID. The ID must be in the format 'c-<integer>'.");
                                    continue; // Restart the loop to get a valid ID
                                }

                            boolean idExists = false;
                            for (Container containerChoice : port.getContainers()) {
                                if (containerChoice != container && containerChoice.getContainerID().equals(containerID)) {
                                    idExists = true;
                                    System.out.println("The ID is already in use!");
                                    break; // No need to check further, the ID already exists
                                }
                            }

                            if (!idExists) {
                                container.setContainerID(containerID);
                                System.out.println("Updated successfully");
                                break; // Exit the loop since a valid, unique ID has been set
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid value");
                        }
                    } while (true);

                }

                //Update container weight
                case 2 -> {
                    int containerWeight;
                    do {
                        try {
                            System.out.println("Please enter your vehicle capacity:");
                            containerWeight = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid value");
                        }
                    } while (true);
                    container.setWeight(containerWeight);
                    System.out.println("Updated successfully");

                }



                //Exit
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 1-2");
            }
        } while (running3);
    }
    public static int portOptionsMenuToUpdateContainer(int choice) {
        //Menu

        decorativeLine();
        System.out.println();
        System.out.println("1. Update Container ID \t|\t\t2. Update Container weight \t\t|\t\t0. Go back");


        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option: ");
        }
        return choice;
    }
    public static void chooseUserToUpdate(List<String> usedUsername) {
        decorativeLine();
        System.out.println();
        boolean running4 = true;

        do {

            for (User user : userList) {
                System.out.println(user.getUsername());
            }
            System.out.println("0. Go back");
            System.out.println("Enter the username of the port manager that you want to modify: ");
            String userChoice = scanner.nextLine();

            if (userChoice.equals("0")) {
                break;
            }


            User selectedUser = null;
            for (User user : userList) {
                if (userChoice.equals(user.getUsername())) {
                    selectedUser = user;
                    break;
                }
            }

            if (selectedUser != null) {
                userOptionsToUpdate(usedUsername, selectedUser);
            } else {
                System.out.println("The container does not exist");
            }
        } while (running4);

    }

    public static void userOptionsToUpdate(List<String> usedUsername, User user){
        boolean running3 = true;
        int choice = -1;
        do {
            choice = userOptionsMenuToUpdate(choice);
            switch (choice) {
                //Update username
                case 1 -> {
                    do{
                        System.out.println("Enter the new username: ");
                        String newUsername = scanner.nextLine();

                            if (!usedUsername.contains(newUsername)){
                                usedUsername.remove(user.getUsername());
                                user.setUsername(newUsername);
                                break;
                            } else{
                                System.out.println("This user name is already existed!");
                        }
                    }while (true);
                }

                //Update password
                case 2 -> {
                    String confirmationPassword;
                    System.out.println("Enter the new password: ");
                    String newPassword = scanner.nextLine();

                    do{
                        System.out.println("Confirm password: ");
                        confirmationPassword = scanner.nextLine();
                        if (confirmationPassword.equals(newPassword)){
                            user.setPassword(newPassword);
                            break;
                        } else{
                            System.out.println("Please reconfirm your password:");
                        }

                    } while (true);
                }

                //Update port managed
                case 3 -> {
                    choosePortToAssign();
                }


                //Exit
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 1-12");
            }
        } while (running3);
    }

    public static int userOptionsMenuToUpdate(int choice) {
        //Menu
        decorativeLine();
        System.out.println();
        System.out.println("1. Update username \t|\t\t2. Update password \t\t|\t\t3. Update Port managed");
        System.out.println("0. Go back");

        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option: ");
        }
        return choice;
    }

    public static void choosePortToAssign(){
        do {
            // Port IDs
            for (Port port : portList) {
                System.out.println(port.getPortID() + ". " + port.getName());
            }

            System.out.println("0. Go back");
            System.out.print("Enter the ID of the port above that you want to assign: ");
            String portOption = scanner.nextLine();

            if (portOption.equals("0")) {
                break;
            } else {
                if (!portIDs.contains(portOption)) {
                    System.out.println("Port does not exist");
                } else {
                    for (Port port : portList) {
                        if (portOption.equals(port.getPortID())) {
                            boolean portAssigned = false; // Track if the port has been assigned to any PortManager
                            for (User user : userList) {
                                if (user instanceof PortManager) {
                                    PortManager portManager = (PortManager) user;
                                    if (portManager.getPortManaged() == null) {
                                        portManager.setPortManaged(port);
                                        portAssigned = true;
                                        break; // Assign the port to the first available PortManager and break
                                    }
                                }
                            }

                            if (!portAssigned) {
                                System.out.println("All PortManagers are already managing a port.");
                            }
                        }
                    }
                }
            }
        } while (true);
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