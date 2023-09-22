package Entities;

import Interface.IPort;
import Source.AdminInterface;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Port implements IPort, Serializable {
    public static void decorativeLine() {
        for (int i = 0;i < 100;i++){
            System.out.print("*");
        }
    }
    @Serial
    private static final long serialVersionUID = 1030224541977093439L;
    private String portID;
    private String name;
    private double latitude;
    private double longitude;
    private double currentWeight;
    private double capacity;
    private boolean landingAbility;
    private List<Container> containers = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Trip> trips = new ArrayList<>();
    public String currentDate;
    private double usedFuel;
    private static final Scanner scanner = new Scanner(System.in);
    private HashMap<String, Double> fuelHistory = new HashMap<>();
    public Port(String portID, String name, double latitude, double longitude, double capacity, double currentWeight,boolean landingAbility) {
        this.portID = String.valueOf(portID);
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacity = capacity;
        this.landingAbility = landingAbility;
        this.currentWeight = currentWeight;
    }

    public String getPortID() {
        return portID;
    }

    public void setPortID(String portID) {
        this.portID = portID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }


    public List<Container> getContainers() {
        return containers;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    public boolean isLandingAbility() {
        return landingAbility;
    }


    public void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }
    public void calculateDistanceInterface() {
        do {
            //Port IDs
            for (Port port : AdminInterface.portList) {
                if (port != this){
                    System.out.println(port.getPortID() + ". " + port.getName());
                }
            }

            System.out.println("0. Go back");
            System.out.print("Enter the ID of the port above that you want to calculate distance to: ");
            String portOption = scanner.nextLine();

            if (portOption.equals("0")) {
                break;
            } else {
                if (!AdminInterface.portIDs.contains(portOption)) {
                    System.out.println("Port does not exist");
                } else {
                    for (Port port : AdminInterface.portList) {
                        if (portOption.equals(port.getPortID())) {
                            System.out.printf("The distance from this port to port %s is %.2fkm\n" , portOption, this.calculateDistance(port));
                        }
                    }
                }
            }
        } while (true);
    }
    public static void addContainer(Port port) {
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
                    if (!AdminInterface.containerIDs.contains(containerID)) {
                        break;
                    } else {
                        System.out.println("The ID is already existed!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        } while (true);

        double containerWeight;
        do {
            try {
                System.out.println("Please enter your container weight:");
                containerWeight = Double.parseDouble(scanner.nextLine());
                if (AdminInterface.containerIDs.contains(containerID) && containerWeight > port.getCurrentWeight()) {
                    AdminInterface.containerIDs.remove(containerID);
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        } while (true);

        int typeID;
        do {
            for (int i = 0; i < AdminInterface.typeList.size(); i++) {
                System.out.println(i + ". " + AdminInterface.typeList.get(i).getType());
            }
            try {
                System.out.println("Please choose the container type number 0-4");
                typeID = Integer.parseInt(scanner.nextLine());
                if (typeID >= 0 && typeID < AdminInterface.typeList.size()) {
                    break;
                } else {
                    System.out.println("Please enter a valid option (0-4)");
                }
            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        } while (true);
        do {
            System.out.print("Do you want to create this container? (y/n): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equals("y")) {
                Container newContainer = new Container(containerID, containerWeight, AdminInterface.typeList.get(typeID));
                AdminInterface.containerList.add(newContainer);
                port.addContainer(newContainer);
                AdminInterface.containerIDs.add(containerID);
                break;
            } else if (confirmation.equals("n")) {
                AdminInterface.containerIDs.remove(containerID);
                System.out.println("The process is cancelled");
                break;
            } else {
                System.out.println("Please enter y or n for confirmation!");
            }
        } while (true);
    }
    public static void removeContainer(Port port) {
        decorativeLine();
        System.out.println();
        boolean running4 = true;
        Iterator<Container> containerIterator = port.getContainers().iterator();
        do {
            for (Container container : port.getContainers()) {
                System.out.println(container.getContainerID() + ". " + container.getType().getType());
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
                    AdminInterface.portList.remove(container);
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
        do {
            int choice = -1;
            try {
                System.out.println("Choose the vehicle you want to add");
                System.out.println("1. Ship");
                System.out.println("2. Truck");
                System.out.println("3. Reefer Truck");
                System.out.println("4. Tanker Truck");
                System.out.println("0. Go back");
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
                        System.out.println("Please enter the vehicle ID by the format 'sh-vehicleID': ");
                        vehicleID = scanner.nextLine();

                        if (!vehicleID.matches("sh-\\d+")) {
                            System.out.println("Invalid ID. The ID must be in the format 'sh-<integer>'.");
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

                double capacity;
                do {
                    try {
                        System.out.println("Please enter your vehicle capacity:");
                        capacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);


                double fuelCapacity;
                do {
                    try {
                        System.out.println("Please enter your vehicle fuel capacity:");
                        fuelCapacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                do {
                    System.out.print("Do you want to create this ship? (y/n): ");
                    String confirmation = scanner.nextLine();
                    if (confirmation.equals("y")) {
                        AdminInterface.shipList.add(new Ship(vehicleID, vehicleName, capacity, fuelCapacity, port,true));
                        System.out.println("New ship has been added");
                        break;
                    } else if (confirmation.equals("n")) {
                        System.out.println("The process is cancelled");
                        break;
                    } else {
                        System.out.println("Please enter y or n for confirmation!");
                    }
                } while (true);
                break;
            }

            //Truck
            else if (choice == 2) {
                String vehicleID;
                do {
                    try {
                        System.out.println("Please enter the vehicle ID by the format 'tr-vehicleID': ");
                        vehicleID = scanner.nextLine();

                        if (!vehicleID.matches("tr-\\d+")) {
                            System.out.println("Invalid ID. The ID must be in the format 'tr-<integer>'.");
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

                double capacity;
                do {
                    try {
                        System.out.println("Please enter your vehicle capacity:");
                        capacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                double fuelCapacity;
                do {
                    try {
                        System.out.println("Please enter your vehicle fuel capacity:");
                        fuelCapacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                do {
                    System.out.print("Do you want to create this truck? (y/n): ");
                    String confirmation = scanner.nextLine();
                    if (confirmation.equals("y")) {
                        AdminInterface.truckList.add(new Truck(vehicleID, vehicleName, capacity, fuelCapacity, port,true));
                        System.out.println("New truck has been added");
                        break;
                    } else if (confirmation.equals("n")) {
                        vehicleIDs.remove(vehicleID);
                        System.out.println("The process is cancelled");
                        break;
                    } else {
                        System.out.println("Please enter y or n for confirmation!");
                    }
                } while (true);
                break;
            }

            //Reefer Truck
            else if (choice == 3) {
                String vehicleID;
                do {
                    try {
                        System.out.println("Please enter the vehicle ID by the format 'tr-vehicleID': ");
                        vehicleID = scanner.nextLine();

                        if (!vehicleID.matches("tr-\\d+")) {
                            System.out.println("Invalid ID. The ID must be in the format 'tr-<integer>'.");
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

                double capacity;
                do {
                    try {
                        System.out.println("Please enter your vehicle capacity:");
                        capacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                double fuelCapacity;
                do {
                    try {
                        System.out.println("Please enter your vehicle fuel capacity:");
                        fuelCapacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                do {
                    System.out.print("Do you want to create this truck? (y/n): ");
                    String confirmation = scanner.nextLine();
                    if (confirmation.equals("y")) {
                        AdminInterface.reeferTruckList.add(new ReeferTruck(vehicleID, vehicleName, capacity, fuelCapacity, port,true));
                        System.out.println("New vehicle has been added");
                        break;
                    } else if (confirmation.equals("n")) {
                        vehicleIDs.remove(vehicleID);
                        System.out.println("The process is cancelled");
                        break;
                    } else {
                        System.out.println("Please enter y or n for confirmation!");
                    }
                } while (true);
                break;
            }

            //Tanker Truck
            else if (choice == 4) {
                String vehicleID;
                do {
                    try {
                        System.out.println("Please enter the vehicle ID by the format 'tr-vehicleID': ");
                        vehicleID = scanner.nextLine();

                        if (!vehicleID.matches("tr-\\d+")) {
                            System.out.println("Invalid ID. The ID must be in the format 'tr-<integer>'.");
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

                double capacity;
                do {
                    try {
                        System.out.println("Please enter your vehicle capacity:");
                        capacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Please enter a valid value");
                    }
                } while (true);

                double fuelCapacity;
                do {
                    try {
                        System.out.println("Please enter your vehicle fuel capacity:");
                        fuelCapacity = Double.parseDouble(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                    }
                } while (true);

                do {
                    System.out.print("Do you want to create this truck? (y/n): ");
                    String confirmation = scanner.nextLine();
                    if (confirmation.equals("y")) {
                        AdminInterface.tankerTruckList.add(new TankerTruck(vehicleID, vehicleName, capacity, fuelCapacity, port,true));
                        System.out.println("New vehicle has been added");
                        break;
                    } else if (confirmation.equals("n")) {
                        vehicleIDs.remove(vehicleID);
                        System.out.println("The process is cancelled");
                        break;
                    } else {
                        System.out.println("Please enter y or n for confirmation!");
                    }
                } while (true);
                break;
            } else if (choice == 0) {
                break;
            } else {
                System.out.println("Please enter from 1-5");
            }
        } while (true);
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

                    if (vehicle instanceof Ship){
                        AdminInterface.shipList.remove(vehicle);
                    } else if (vehicle instanceof Truck) {
                        AdminInterface.truckList.remove(vehicle);
                    } else if (vehicle instanceof TankerTruck) {
                        AdminInterface.tankerTruckList.remove(vehicle);
                    } else {
                        AdminInterface.reeferTruckList.remove(vehicle);
                    }

                    System.out.println("Vehicle is removed");
                    break;
                }
            }

            if (!vehicleFound) {
                System.out.println("The vehicle does not exist");
            }
        } while (running4);
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
    public static void loadContainer(Port port, List<String> containerIDs) {
        do {
            for (Vehicle vehicle : port.getVehicles()) {
                System.out.println(vehicle.getVehicleID() + " | "  + vehicle.getName() + " (" + vehicle.getCurrentWeight() + "/" + vehicle.getCapacity() + "kg) | ");
            }
            System.out.println("0. Go back");
            System.out.println("Please choose the vehicle ID that you want to load containers to:");
            String vehicleID = scanner.nextLine();

            if (vehicleID.equals("0")) {
                break;
            } else {
                for (Vehicle vehicle : port.getVehicles()) {
                    if (vehicleID.equals(vehicle.getVehicleID())) {
                        boolean running4 = true;
                        do {
                            for (Container container : port.getContainers()) {
                                System.out.println(container.getContainerID() + ". " + container.getType().getType());
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
                                                System.out.println("This vehicle can not load this container!");
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
    }
    public static void unloadContainer(Port port) {
        do {
            for (Vehicle vehicle : port.getVehicles()) {
                System.out.print(vehicle.getVehicleID() + " | "  + vehicle.getName() + " (" + vehicle.getCurrentWeight() + "/" + vehicle.getCapacity() + "kg) | " + " | containers: ");
                vehicle.containerIDs();
                System.out.println();
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
                        List<String> vehicleContainersIDs = new ArrayList<>();
                        do {
                            for (Container container : vehicle.getContainers()) {
                                System.out.println(container.getContainerID() + ". " + container.getType().getType());
                                vehicleContainersIDs.add(container.getContainerID());
                            }
                            System.out.println("0. Go back");
                            System.out.println("Enter the container ID you want to unload from the vehicle:");
                            String containerID = scanner.nextLine();
                            if (containerID.equals("0")) {
                                break;
                            } else {
                                if (!vehicleContainersIDs.contains(containerID)) {
                                    System.out.println("The container does not exist");
                                } else {
                                    for (Container container : vehicle.getContainers()) {
                                        if (containerID.equals(container.getContainerID())) {
                                            if (vehicle.unloadContainer(container)) {
                                                System.out.println("The container is unloaded from the vehicle");
                                                running4 = false;
                                                break;
                                            } else {
                                                System.out.println("This container can not be unloaded from this vehicle!");
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
    }
    public static void addPort() {
        String portID;
        do {
            try {
                System.out.println("Please enter the port ID by the format 'p-portID': ");
                portID = scanner.nextLine();

                if (!portID.matches("p-\\d+")) {
                    System.out.println("Invalid ID. The ID must be in the format 'p-<integer>'.");
                } else {
                    if (!AdminInterface.portIDs.contains(portID)) {
                        AdminInterface.portIDs.add(portID);
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

        double portLongitude;
        do {
            try {
                System.out.println("Please enter your port Longitude:");
                portLongitude = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid value");
            }
        } while (true);

        double portCapacity;
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


        do {
            System.out.print("Do you want to create this port? (y/n): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equals("y")) {
                AdminInterface.portList.add(new Port(portID,portName,portLatitude,portLongitude,portCapacity,portCurrentWeight,portLandingAbility));
                System.out.println("New port has been added");
                break;
            } else if (confirmation.equals("n")) {
                System.out.println("The process is cancelled");
                AdminInterface.portIDs.remove(portID);
                break;
            } else {
                System.out.println("Please enter y or n for confirmation!");
            }
        } while (true);
    }
    public static void removePort() {
        boolean running5 = true;
        do {
            Iterator<Port> iterator = AdminInterface.portList.iterator();
            for (Port port : AdminInterface.portList) {
                System.out.println(port.getPortID() + ". " + port.getName());
            }
            System.out.println("0. Go back");
            System.out.println("Enter the port ID that you want to remove:");
            String portRemoved = scanner.nextLine();
            if (portRemoved.equals("0")) {
                running5 = false;
            } else {
                boolean found = false;
                for (Port port : AdminInterface.portList) {
                    if (portRemoved.equals(port.getPortID())) {
                        for (Vehicle vehicle : port.getVehicles()) {
                            if (!vehicle.isStatus()) {
                                System.out.println("Cannot remove this port as there are vehicles with schedules!");
                                found = true;
                                break;
                            }
                        }
                    }
                    break;
                }
                // Check if the port is used as arriveTo in any trip
                for (Trip trip : AdminInterface.tripList) {
                    if (trip.getArriveTo().getPortID().equals(portRemoved) && !trip.getStatus()) {
                        System.out.println("Port cannot be removed as it is used as arriveTo in a trip.");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    while (iterator.hasNext()) {
                        Port port = iterator.next();
                        if (port.getPortID().equals(portRemoved)) {
                            iterator.remove();
                            AdminInterface.portIDs.remove(portRemoved);
                            System.out.println("Port is removed");
                            break;
                        }
                    }
                }
            }
        } while (running5);
    }

    @Override
    public double calculateDistance(Port port) {
        return Math.sqrt(Math.pow((port.getLongitude() - this.getLongitude()),2) + Math.pow((port.getLatitude() - this.getLatitude()),2));
    }

    @Override
    public void addContainer(Container container) {
        if (container.getWeight() > this.capacity){
            System.out.println("The container is heavier than the port capacity!");
        } else if ((this.currentWeight + container.getWeight()) > this.capacity){
            System.out.println("The container is heavier than the port capacity!");
        } else{
            this.containers.add(container);
            this.currentWeight += container.getWeight();
//            System.out.println("New container is added to " + this.getName());
        }
    }

    @Override
    public void removeContainer(Container container) {
        this.containers.remove(container);
        AdminInterface.containerList.remove(container);
        this.currentWeight -= container.getWeight();
    }


    public void searchContainer() {
        boolean found = false;
        System.out.println("Enter the ID you want to search");
        String id = scanner.nextLine();
        for (Container container : containers) {
            if (container.getContainerID().equals(id)){
                found = true;
                System.out.println(container);
            }
        }
        if (!found) {
            System.out.println("container not found");
        }
    }
    @Override
    public void addVehicle(Vehicle vehicle) {
        if (!this.isLandingAbility() && (vehicle instanceof Truck || vehicle instanceof ReeferTruck || vehicle instanceof TankerTruck)){
            System.out.println("Can not add this vehicle!");
        } else{
            this.vehicles.add(vehicle);
        }
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
    }

    @Override
    public void searchVehicleById(String id) {
        List<String> vehicleID = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
            vehicleID.add(vehicle.getVehicleID());
        }

        if (vehicleID.contains(id)) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getVehicleID().equals(id)){
                    System.out.println(vehicle);
                }
            }
        } else{
            System.out.println("Vehicle not found");
        }
    }

    @Override
    public void searchVehicleByName(String name) {
        List<String> vehicleName = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
            vehicleName.add(vehicle.getName());
        }

        if (vehicleName.contains(name)) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getName().equals(name)){
                    System.out.println(vehicle);
                }
            }
        } else{
            System.out.println("Vehicle not found");
        }
    }

    public List<Trip> getTrips() {
        return trips;
    }

    @Override
    public void addTrip(Trip trip) {
        trips.add(trip);
    }


    @Override
    public void displayShips() {
        for (Vehicle vehicle : vehicles){
            if (vehicle instanceof Ship){
                System.out.println(vehicle);
                decorativeLine();
                System.out.println();
            }
        }
    }

    public void displayTrucks() {
        for (Vehicle vehicle : vehicles){
            if (vehicle instanceof ReeferTruck || vehicle instanceof Truck || vehicle instanceof TankerTruck){
                System.out.println(vehicle);
                decorativeLine();
                System.out.println();
            }
        }
    }

    @Override
    public void displayContainers() {
        for (Container container : containers){
            decorativeLine();
            System.out.println();
            System.out.println(container);

        }
    }
    @Override
    public void displayTrips(){
        for (Trip trip : trips){
            decorativeLine();
            System.out.println();
            System.out.println(trip);
        }
    }

    public void addUsedFuel(double newFuel) {
        // Get local time
        LocalDate today = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String str = today.format(dtf);

        // In case it is still the same day
        if (str.equals(this.currentDate)) {
            fuelHistory.put(currentDate, this.usedFuel += newFuel);
        } else {
            // In case a new day has come
            this.currentDate = str;
            this.usedFuel = newFuel;
            fuelHistory.put(currentDate, usedFuel);

        }
    }

    public double calculateFuelUsedInADay(String date) {
        if (fuelHistory.containsKey(date)) {
            return fuelHistory.get(date);
        } else {
            return 0;
        }
    }
    public double getWeightOfContainerType(Type type){
        double totalWeight = 0;
        for(Container container : this.containers){
            if(container.getType() == type){
                totalWeight += container.getWeight();
            }
        }
        return totalWeight;
    }

    @Override
    public String toString() {
        return "Port Information:" +
                "\nPort ID: " + portID +
                "\nName: " + name +
                "\nLatitude: " + latitude +
                "\nLongitude: " + longitude +
                "\nCurrent Weight: " + currentWeight +
                "\nCapacity: " + capacity +
                "\nLanding Ability: " + landingAbility;
    }
}
