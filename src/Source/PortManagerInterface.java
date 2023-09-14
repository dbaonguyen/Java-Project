package Source;

import Entities.*;
import Users.Admin;
import Users.PortManager;
import Users.User;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PortManagerInterface {
    //data load
    public static List<Container> containerList = new ArrayList<>();
    public static List<Ship> shipList = new ArrayList<>();
    public static List<Truck> truckList = new ArrayList<>();
    public static List<ReeferTruck> reeferTruckList = new ArrayList<>();
    public static List<TankerTruck> tankerTruckList = new ArrayList<>();
    public static List<Type> typeList = new ArrayList<>();
    public static List<String> notificationList = new ArrayList<>();
    public static List<String> containerIDs = new ArrayList<>();
    private static final String DEFAULT_DIRECTORY = "Data";
    private static final Scanner scanner = new Scanner(System.in);

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
    public static void allTrip(Port portManaged){
        for (Trip trip : portManaged.getTrips()){
            decorativeLine();
            System.out.println(trip);
        }
    }
    public static void allTripInGivenDay (Port portManaged) {
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
                        for (Trip trip : portManaged.getTrips()) {
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
    public static void allTripFromDayAtoB(Port portManaged) {

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
                        for (Trip trip : portManaged.getTrips()) {
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
    public static void statisticsMenu(Port portManaged) {
        boolean running = true;
        do {
            int choice = -1;
            System.out.println("1. Display all containers");
            System.out.println("2. Display all vehicles");
            System.out.println("3. Display all trips");
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
                case 1 -> displayAllContainers(portManaged);
                case 2 -> displayAllVehicles(portManaged);
                case 3 -> allTrip(portManaged);
                case 4 -> allTripInGivenDay(portManaged);
                case 5 -> allTripFromDayAtoB(portManaged);
                case 6 -> choosePortToCalculateFuelUsedInADay(portManaged);
                case 7 -> displayWeightOfEachContainerType(portManaged);
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
                //Search vehicle
                case 4 -> Port.searchVehicle(port);
                //Search container
                case 5 -> port.searchContainer();
                //Load
                case 6 -> Port.loadContainer(port, containerIDs);
                //Unload container
                case 7 -> Port.unloadContainer(port);
                //Display vehicles
                case 8 -> {
                    port.displayShips();
                    port.displayTrucks();
                }
                //Display containers
                case 9 -> port.displayContainers();
                //Display trips
                case 10 -> System.out.println(port.getTrips());
                case 11 -> chooseVehicleToRefuel(port);
                //Go back
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 1-11");
            }
        } while (running3);
    }
    public static void displayWeightOfEachContainerType(Port portManaged){
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
                    total += portManaged.getWeightOfContainerType(typeList.get(0));
                    System.out.println("The total weight of this type of container is: " + total + " kg");
                }
                case 2 -> {
                    total += portManaged.getWeightOfContainerType(typeList.get(1));
                    System.out.println("The total weight of this type of container is: " + total + " kg");
                }
                case 3 -> {
                    total += portManaged.getWeightOfContainerType(typeList.get(2));
                    System.out.println("The total weight of this type of container is: " + total + " kg");
                }
                case 4 -> {
                    total += portManaged.getWeightOfContainerType(typeList.get(3));
                    System.out.println("The total weight of this type of container is: " + total + " kg");
                }

                case 5 -> {
                    total += portManaged.getWeightOfContainerType(typeList.get(4));
                    System.out.println("The total weight of this type of container is: " + total + " kg");
                }
                case 0 -> running2 = false;

                default ->
                        System.out.println("Invalid option");
            }
        } while (running2);
    }
    public static void displayAllVehicles(Port portManaged){
        for (Vehicle vehicle : shipList) {
            if (portManaged.getVehicles().contains(vehicle)){
                decorativeLine();
                System.out.println(vehicle);
            }
        }
        for (Vehicle vehicle : truckList) {
            if (portManaged.getVehicles().contains(vehicle)){
                decorativeLine();
                System.out.println(vehicle);
            }
        }
        for (Vehicle vehicle : reeferTruckList) {
            if (portManaged.getVehicles().contains(vehicle)){
                decorativeLine();
                System.out.println(vehicle);
            }
        }
        for (Vehicle vehicle : tankerTruckList) {
            if (portManaged.getVehicles().contains(vehicle)){
                decorativeLine();
                System.out.println(vehicle);
            }
        }
    }
    public static void displayAllContainers(Port portManaged){
        for (Container container : containerList) {
            if (portManaged.getContainers().contains(container)){
                decorativeLine();
                System.out.println(container);
            }
        }
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
    public static void choosePortToCalculateFuelUsedInADay(Port portManaged){
        chooseDateToDisplayFuelUsed(portManaged);
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
    public static void loginMainMenu(User user) {
        PortManager manager = (PortManager) user;
        System.out.println("Welcome " + manager.getPortManaged().getName() + " manager");
        //Admin
        boolean running = true;
        do {
            int choice = -1;
            //Menu
            decorativeLine();
            System.out.println("1. Port management");
            System.out.println("2. Transportation");
            System.out.println("3. Statistics");
            System.out.println("4. Notification");
            System.out.println("0. Go back");
            try {
                System.out.print("Your option: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Please choose a valid option: ");
            }
            decorativeLine();
            switch (choice) {
                case 1 -> portOptions(manager.getPortManaged());
                case 2 -> transportationMenu();
                case 3 -> statisticsMenu(manager.getPortManaged());
                case 4 -> displayNotification();
                case 0 -> running = false;
                default -> System.out.println("Please choose from 1-4");
            }
        } while (running);


    }
    public static void displayNotification( ){
        for (String notification: notificationList) {
            System.out.println(notification);
        }
    }
}